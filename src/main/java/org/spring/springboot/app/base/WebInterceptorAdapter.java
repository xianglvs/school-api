package org.spring.springboot.app.base;

import lombok.extern.slf4j.Slf4j;
import org.spring.springboot.app.base.annotation.Token;
import org.spring.springboot.app.base.annotation.TokenType;
import org.spring.springboot.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Access Token拦截器
 */
@Component
@Slf4j
public class WebInterceptorAdapter extends HandlerInterceptorAdapter {


    @Autowired
    private RedisUtils redisUtils;

    private void tokenToUser(HttpServletRequest request) {
        String accessToken = getToken(request);
        if (accessToken == null || accessToken.trim().length() == 0) {
            return;
        }
        User user = null;
        if (accessToken != null && accessToken.trim().length() > 0) {
            user = redisUtils.get(accessToken);
        }
        if (user != null) {
            ThreadLocalUtil.put(Constants.TOKEN_SESSION_NAME, user);
            ThreadLocalUtil.put(Constants.TOKEN_PARAM_NAME, accessToken);
        }
    }

    private boolean hasMenuPermission(List<Menu> menus, String permission) {
        if (menus == null || menus.isEmpty()) {
            return false;
        }
        for (Menu m : menus) {
            if (m.getPermission() == null || m.getPermission().trim().length() == 0) {
                continue;
            }
            if (m.getPermission().equals(permission)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasPermissions(User user, String[] permissions) {
        for (String permission : permissions) {
            if (permission == null) {
                continue;
            }
            permission = permission.trim();
            if (permission.length() == 0) {
                continue;
            }
            if (hasMenuPermission(user.getMenus(), permission)) {
                return true;
            }
        }
        return false;
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getParameter(Constants.TOKEN_PARAM_NAME);
        if (token == null || token.length() == 0) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equalsIgnoreCase(Constants.TOKEN_PARAM_NAME)) {
                    token = cookie.getValue();
                }
            }
        }
        return token;
    }

    private boolean validateAccessToken(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Token annotation = handlerMethod.getMethodAnnotation(Token.class);
        HandlerMethod method = (HandlerMethod) handler;
        Class<?> clazz = handlerMethod.getMethod().getReturnType();
        if (annotation == null) { //方法上没有注解则取类上的注解
            annotation = method.getBeanType().getAnnotation(Token.class);
        }
        if (annotation == null || annotation.type() == TokenType.NONE) {
            return true;
        }
        if (annotation.type() == TokenType.TOKEN) {
            String accessToken = getToken(request);
            if (accessToken != null && accessToken.trim().length() > 0) {
                if (redisUtils.hasKey(accessToken)) {
                    if (annotation.RequiresPermissions().length == 0) {
                        return true;
                    }
                    User user = redisUtils.get(accessToken);
                    if (hasPermissions(user, annotation.RequiresPermissions())) {
                        return true;
                    }
                }
            }
            throw new BusinessException(Type.PERMISSIONS_VALIDATE_FAIL, ErrorTools.ErrorAsArrayList(new Error("token", "签名错误")));
        }
        return true;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        tokenToUser(request);
        boolean validateResult = this.validateAccessToken(request, response, handler);
        if (!validateResult) {
            return false;
        }
        return true;
    }
}

package org.spring.springboot.app.base;

import lombok.extern.slf4j.Slf4j;
import org.spring.springboot.app.base.annotation.Token;
import org.spring.springboot.app.base.annotation.TokenType;
import org.spring.springboot.app.domain.vo.SysMenuResVO;
import org.spring.springboot.app.domain.vo.UserTokenResVO;
import org.spring.springboot.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        ThreadLocalUtil.clear();
        String accessToken = getToken(request);
        if (accessToken == null || accessToken.trim().length() == 0) {
            return;
        }
        UserTokenResVO userSession = null;
        if (accessToken != null && accessToken.trim().length() > 0) {
            userSession = redisUtils.get(accessToken);
        }
        if (userSession != null) {
            ThreadLocalUtil.put(Constants.TOKEN_SESSION_NAME, userSession);
            ThreadLocalUtil.put(Constants.TOKEN_PARAM_NAME, accessToken);
        }
    }

    private boolean hasMenuPermission(List<SysMenuResVO> sysMenuResVOS, String permission) {
        if (sysMenuResVOS == null || sysMenuResVOS.isEmpty()) {
            return false;
        }
        for (SysMenuResVO m : sysMenuResVOS) {
            if (m.getPermission() == null || m.getPermission().trim().length() == 0) {
                continue;
            }
            if (m.getPermission().equals(permission)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasPermissions(UserTokenResVO userSession, String[] permissions) {
        for (String permission : permissions) {
            if (permission == null) {
                continue;
            }
            permission = permission.trim();
            if (permission.length() == 0) {
                continue;
            }
            if (hasMenuPermission(userSession.getMenus(), permission)) {
                return true;
            }
        }
        return false;
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getParameter(Constants.TOKEN_PARAM_NAME);
        if (token == null || token.length() == 0) {
            if(request.getCookies() == null){
                return null;
            }
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
                    UserTokenResVO userSession = redisUtils.get(accessToken);
                    if (hasPermissions(userSession, annotation.RequiresPermissions())) {
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

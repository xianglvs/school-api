package org.spring.springboot.app.base;

import org.spring.springboot.app.domain.vo.UserTokenResVO;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return (methodParameter.getParameterType().equals(UserTokenResVO.class) || methodParameter.getParameterName().equalsIgnoreCase(Constants.TOKEN_PARAM_NAME)) && ThreadLocalUtil.get(Constants.TOKEN_SESSION_NAME) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) {
        if (methodParameter.getParameterType().equals(UserTokenResVO.class)) {
            return ThreadLocalUtil.get(Constants.TOKEN_SESSION_NAME);
        }
        if (methodParameter.getParameterName().equalsIgnoreCase(Constants.TOKEN_PARAM_NAME)) {
            return ThreadLocalUtil.get(Constants.TOKEN_SESSION_NAME, UserTokenResVO.class).getToken();
        }
        return null;
    }
}

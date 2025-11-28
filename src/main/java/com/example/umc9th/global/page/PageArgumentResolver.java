package com.example.umc9th.global.page;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PageArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ValidPage.class)
                && (parameter.getParameterType().equals(Integer.class)
                || parameter.getParameterType().equals(int.class));
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {

        ValidPage annotation = parameter.getParameterAnnotation(ValidPage.class);
        String paramName = parameter.getParameterName(); // 보통 "page"

        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String value = request != null ? request.getParameter(paramName) : null;

        int page;
        if (value == null || value.isBlank()) {
            page = annotation.defaultValue(); // 기본 1
        } else {
            try {
                page = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                throw new InvalidPageException("page 파라미터는 숫자여야 합니다.");
            }
        }

        if (page < 1) {
            throw new InvalidPageException("page는 1 이상의 정수여야 합니다.");
        }

        return page;
    }
}

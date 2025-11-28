package com.example.umc9th.global.page;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidPage {

    // 값이 안 들어왔을 때 기본 페이지 (1페이지)
    int defaultValue() default 1;
}

package com.example.biz_framework.annotation;

import com.example.biz_framework.exception.BizDefaultExceptionHandler;
import com.example.biz_framework.exception.BizExceptionHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BizAnnotation {
    String name();

    Class<? extends BizExceptionHandler> exceptionHandler() default BizDefaultExceptionHandler.class;
}

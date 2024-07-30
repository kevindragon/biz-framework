package com.example.biz_framework.controller.custom;

import com.example.biz_framework.exception.BizException;
import com.example.biz_framework.exception.BizExceptionHandler;
import com.example.biz_framework.exception.BizRecoverableException;
import org.springframework.stereotype.Component;

@Component
public class CustomExceptionHandler implements BizExceptionHandler<String> {
    @Override
    public String handleDefault(BizException e) {
        System.out.println("处理BizException");
        return null;
    }

    @Override
    public String handleRecoverable(BizRecoverableException e) {
        System.out.println("处理BizRecoverableException: " + e.getData());
        return (String) e.getData();
    }
}

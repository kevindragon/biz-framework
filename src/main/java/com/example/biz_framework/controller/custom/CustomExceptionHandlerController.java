package com.example.biz_framework.controller.custom;

import com.example.biz_framework.annotation.BizAnnotation;
import com.example.biz_framework.controller.ResponseWrap;
import com.example.biz_framework.exception.BizException;
import com.example.biz_framework.exception.BizExceptionHandler;
import com.example.biz_framework.exception.BizRecoverableException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/exec")
@RestController
public class CustomExceptionHandlerController {
    @GetMapping
    @BizAnnotation(name = "ExceptionHandler", exceptionHandler = CustomExceptionHandler.class)
    public ResponseWrap<String> custom(@RequestParam(value = "name", required = false) String name) {
        if (ObjectUtils.isEmpty(name)) {
            throw new BizException(CustomResultCode.CUSTOM_ERROR, "empty message");
        }

        if ("fail".equals(name)) {
            throw new BizRecoverableException(CustomResultCode.CUSTOM_FAIL, name);
        }

        return ResponseWrap.success(name);
    }

}

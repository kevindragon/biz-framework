package com.example.biz_framework.controller;

import com.example.biz_framework.annotation.BizAnnotation;
import com.example.biz_framework.annotation.BizParam;
import com.example.biz_framework.controller.request.HelloRequest;
import com.example.biz_framework.controller.response.BaseResultEnum;
import com.example.biz_framework.exception.BizException;
import com.example.biz_framework.exception.BizRecoverableException;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController
public class HelloController {
    @GetMapping("/")
    @BizAnnotation(name = "Hello")
    public ResponseWrap<String> hello(@BizParam @RequestParam("name") String name,
                                      @BizParam @RequestParam("age") int age) {
        return ResponseWrap.success("Hello, " + name + ", age=" + age);
    }

    @GetMapping("/exception")
    @BizAnnotation(name = "Hello")
    public ResponseWrap<String> exception() throws BizException {
        throw new BizRecoverableException(BaseResultEnum.RECOVERABLE_ERROR, "可恢复异常消息内容");
    }

    @PostMapping("/post")
    @BizAnnotation(name = "Hello")
    public ResponseWrap<String> post(@Validated @RequestBody HelloRequest request) {
        return ResponseWrap.success("Hello, " + request.getName());
    }

    @GetMapping("/recoverable")
    @BizAnnotation(name = "Hello")
    public ResponseWrap<String> recoverable(@RequestParam(value = "name", required = false) String name) {
        if (ObjectUtils.isEmpty(name)) {
            throw new BizRecoverableException(BaseResultEnum.RECOVERABLE_ERROR, "Exception data");
        }

        return ResponseWrap.success("成功 " + name);
    }

}

package com.example.biz_framework.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public final class BizDefaultExceptionHandler implements BizExceptionHandler<Object> {
    @Override
    public Object handleDefault(BizException e) {
        return e.getData();
    }

}

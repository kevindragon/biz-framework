package com.example.biz_framework.exception;

import org.springframework.stereotype.Component;

@Component
public interface BizExceptionHandler<T> {
    default T handle(BizException e) {
        if (e instanceof BizRecoverableException) {
            return (T) handleRecoverable((BizRecoverableException) e);
        }
        return (T) handleDefault(e);
    }

    T handleDefault(BizException e);

    default T handleRecoverable(BizRecoverableException e) {
        return (T) e.getData();
    }
}

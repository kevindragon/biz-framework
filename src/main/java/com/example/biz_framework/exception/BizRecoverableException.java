package com.example.biz_framework.exception;

import com.example.biz_framework.controller.response.ResultCode;

public final class BizRecoverableException extends BizException {
    public BizRecoverableException(ResultCode resultCode) {
        super(resultCode);
    }

    public BizRecoverableException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }
}

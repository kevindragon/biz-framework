package com.example.biz_framework.exception;

import com.example.biz_framework.controller.response.ResultCode;
import lombok.Getter;
import lombok.Setter;

public class BizException extends RuntimeException {
    @Getter
    @Setter
    protected String bizName;

    @Getter
    protected ResultCode resultCode;

    @Getter
    protected Object data;

    public BizException(ResultCode resultCode) {
        this(resultCode, null);
    }

    public BizException(ResultCode resultCode, Object data) {
        this.resultCode = resultCode;
        this.data = data;
    }

}

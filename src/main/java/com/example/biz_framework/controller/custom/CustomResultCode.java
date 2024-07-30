package com.example.biz_framework.controller.custom;

import com.example.biz_framework.controller.response.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter@AllArgsConstructor
public enum CustomResultCode implements ResultCode {
    CUSTOM_FAIL("3000", "业务失败"),
    CUSTOM_ERROR("3500", "业务错误");

    private String code;

    private String desc;
}

package com.example.biz_framework.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseResultEnum implements ResultCode {
    SUCCESS("200", "操作成功"),
    ERROR("500", "操作失败"),

    REQUEST_PARAM_ERROR("400", "请求参数校验错误"),

    RECOVERABLE_ERROR("600", "可恢复错误")
    ;

    private String code;

    private String desc;
}

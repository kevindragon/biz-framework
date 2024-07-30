package com.example.biz_framework.controller;

import lombok.Data;

@Data
public class ResponseWrap<T> {
    private Boolean success;

    private String code;

    private String message;

    private T data;

    private ResponseWrap(Boolean success, String code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <S> ResponseWrap<S> success(S data) {
        return new ResponseWrap<>(true, "", "", data);
    }

    public static <S> ResponseWrap<S> success(String code, String message, S data) {
        return new ResponseWrap<>(true, code, message, data);
    }

    public static <E> ResponseWrap<E> fail(String code, String message, E data) {
        return new ResponseWrap<E>(false, code, message, data);
    }
}

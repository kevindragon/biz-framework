package com.example.biz_framework.controller;

import lombok.Data;

@Data
public class ResponseWrap<T> {
    private Boolean success;

    private String code;

    private String message;

    private T data;

    private T error;

    private ResponseWrap(Boolean success, String code, String message, T data, T error) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
        this.error = error;
    }

    public static <S> ResponseWrap<S> success(S data) {
        return new ResponseWrap<>(true, "", "", data, null);
    }

    public static <S> ResponseWrap<S> success(String code, String message, S data) {
        return new ResponseWrap<>(true, code, message, data, null);
    }

    public static <E> ResponseWrap<E> fail(String code, String message, E data) {
        return new ResponseWrap<E>(false, code, message, null, data);
    }
}

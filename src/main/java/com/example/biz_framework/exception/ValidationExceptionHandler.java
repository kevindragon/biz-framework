package com.example.biz_framework.exception;

import com.example.biz_framework.controller.ResponseWrap;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.biz_framework.controller.response.BaseResultEnum.REQUEST_PARAM_ERROR;

@RestControllerAdvice
public class ValidationExceptionHandler {
    /**
     * 处理请求json验证错误信息
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseWrap<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        Map<String, String> fieldErrorMessages = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            fieldErrorMessages.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseWrap.fail(REQUEST_PARAM_ERROR.getCode(), REQUEST_PARAM_ERROR.getDesc(), fieldErrorMessages);
    }
}

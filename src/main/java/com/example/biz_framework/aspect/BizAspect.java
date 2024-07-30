package com.example.biz_framework.aspect;

import com.example.biz_framework.annotation.BizAnnotation;
import com.example.biz_framework.annotation.BizParam;
import com.example.biz_framework.controller.ResponseWrap;
import com.example.biz_framework.controller.response.ResultCode;
import com.example.biz_framework.exception.BizException;
import com.example.biz_framework.exception.BizExceptionHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.MessageFormat;

@Aspect
@Component
@Slf4j
public class BizAspect {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ApplicationContext context;

    public BizAspect(ApplicationContext context) {
        this.context = context;
    }

    @Around("@annotation(com.example.biz_framework.annotation.BizAnnotation)")
    public Object bizAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        BizAnnotation annotation = method.getAnnotation(BizAnnotation.class);
        String bizName = annotation.name();

        String paramLog = getParamLog(joinPoint, method);
        System.out.println("spent: " + (System.currentTimeMillis()-start));

        try {
            Object proceed = joinPoint.proceed();

            log.info("{}业务开始,{}", bizName, paramLog);
            long end = System.currentTimeMillis();
            log.info("[{}]成功,SUCCESS,{}ms,{},response={}", bizName, end-start, paramLog, toJsonString(proceed));

            return proceed;
        } catch (BizException e) {
            e.setBizName(bizName);

            Class<? extends BizExceptionHandler> exceptionHandlerClazz = annotation.exceptionHandler();
            BizExceptionHandler bizExceptionHandler = context.getBean(exceptionHandlerClazz);

            long endTime = System.currentTimeMillis();
            ResultCode resultCode = e.getResultCode();
            log.warn("[{}]业务异常,FAIL,{}ms,code={},message={},data={}", e.getBizName(), endTime-start,
                    resultCode.getCode(), resultCode.getDesc(), e.getData());

            return ResponseWrap.fail(resultCode.getCode(), resultCode.getDesc(), bizExceptionHandler.handle(e));
        } catch (Exception e) {
            log.error("[{}]系统异常,message={}", bizName, e.getMessage());
        }

        return null;
    }

    private String getParamLog(ProceedingJoinPoint joinPoint, Method method) {
        StringBuilder paramLogBuilder = new StringBuilder();
        Object[] args = joinPoint.getArgs();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < args.length; i++) {
            Parameter parameter = parameters[i];
            if (i > 0) {
                paramLogBuilder.append(" ");
            }
            BizParam bizParam = parameter.getAnnotation(BizParam.class);
            if (null != bizParam) {
                paramLogBuilder.append(MessageFormat.format("{0}={1}", parameter.getName(), toJsonString(args[i])));
            }
        }
        return paramLogBuilder.toString();
    }

    private String toJsonString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

}

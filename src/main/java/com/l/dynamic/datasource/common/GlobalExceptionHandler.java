package com.l.dynamic.datasource.common;



import com.l.dynamic.datasource.config.InvalidArgumentException;
import com.l.dynamic.datasource.entity.Res;
import lombok.extern.slf4j.Slf4j;


import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    Res<String> handleException(HttpServletRequest req, Exception e) {

        log.error("处理请求时发生错误。url: {}", req.getRequestURI(), e);

        if (!StringUtils.isEmpty(e.getMessage()) && e.getMessage().length() < 64) {
            return Res.failed(e.getMessage());
        }
        return Res.failed("操作失败,请稍后再试！");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Res<String> handleMethodArgumentNotValid(HttpServletRequest req, Exception e) {
        log.error("处理请求时发生错误。url: {}", req.getRequestURI(), e);

        MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
        BindingResult bindingResult = ex.getBindingResult();

        StringBuilder stringBuilder = new StringBuilder();
        for (FieldError error : bindingResult.getFieldErrors()) {
            String field = error.getField();
            Object value = error.getRejectedValue();
            String msg = error.getDefaultMessage();
            String message = String.format("错误字段：%s，错误值：%s，原因：%s；", field, value, msg);
            stringBuilder.append(message).append("\r\n");
        }
        return Res.failed(stringBuilder.toString());
    }

    @ExceptionHandler(InvalidArgumentException.class)
    public Res<String> handleValid(HttpServletRequest req, InvalidArgumentException e) {
        log.error("处理请求时发生错误。url: {}", req.getRequestURI(), e);
        return Res.failed(e.getMessage());
    }




}

package org.example.springboot01.aspect;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception e, HttpServletRequest request) {
        String message = "内部错误:" + e.getMessage();
        return message;
    }
}

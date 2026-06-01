package org.example.springboot.aspect;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 * 使用 @ControllerAdvice 统一捕获所有 Controller 层抛出的异常
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有未捕获的 Exception 类型异常
     * 返回错误信息字符串，避免直接暴露堆栈给客户端
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception e, HttpServletRequest request) {
        String message = "内部错误:" + e.getMessage();
        return message;
    }
}

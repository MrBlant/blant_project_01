package org.example.springboot.unit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 日志记录切面
 * 拦截所有 @PostMapping 方法，打印请求参数
 * 用于调试和审计 POST 请求的入参
 */
@Aspect
@Component
public class LoggingAspect {

    /** 切入点：匹配 org.example.springboot 包下所有标注 @PostMapping 的方法 */
    @Pointcut("execution(* org.example.springboot..*.*(..)) && @annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void requestMethods() {
    }

    /** 前置通知：在方法执行前打印方法名和参数 */
    @Before("requestMethods()")
    public void logRequestParameters(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println("Method: " + joinPoint.getSignature().getName());
        System.out.println("Arguments: " + java.util.Arrays.toString(args));
    }
}

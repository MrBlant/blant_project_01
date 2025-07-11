package org.example.springboot01.unit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // 定义切入点表达式，匹配所有带有@RequestMapping的方法
    @Pointcut("execution(* org.example.springboot01..*.*(..)) && @annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void requestMethods() {
    }

    // 在方法执行前执行的方法，用于获取参数
    @Before("requestMethods()")
    public void logRequestParameters(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println("Method: " + joinPoint.getSignature().getName());
        System.out.println("Arguments: " + java.util.Arrays.toString(args));
        // 可以进一步处理args，比如转换为具体的类型等。
    }
}

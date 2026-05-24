package org.example.springboot.aspect;

import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 限流aop
 */
@Aspect
@Component
public class CurrentLimitAop {
    /**
     * 每秒生成1.0个令牌 每s产生10 token
     */
//    private RateLimiter rateLimiter = RateLimiter.create(1);
    private ConcurrentHashMap<String, RateLimiter> rateLimiters = new ConcurrentHashMap();

    @Around(value = "@annotation(org.example.springboot.aspect.MayiktCurrentLimit)")
    public Object around(ProceedingJoinPoint joinPoint) {
        try {
            //获取拦截的方法名
            Signature sig = joinPoint.getSignature();
            //获取拦截的方法名
            MethodSignature methodSignature = (MethodSignature) sig;
            // 判断方法上是否有加上该注解，如果有加上注解则限流
            MayiktCurrentLimit mayiktCurrentLimit =
                    methodSignature.getMethod().getDeclaredAnnotation(MayiktCurrentLimit.class);
            if (mayiktCurrentLimit == null) {
                // 执行目标方法
                return joinPoint.proceed();
            }
            // 获取注解上的name
            String name = mayiktCurrentLimit.name();
            // 获取注解上的token
            double token = mayiktCurrentLimit.token();
            RateLimiter rateLimiter = rateLimiters.get(name);
            if (rateLimiter == null) {
                rateLimiter = RateLimiter.create(token);
                rateLimiters.put(name, rateLimiter);
            }
            // 开始限流
            boolean result = rateLimiter.tryAcquire();
            if (!result) {
                return "当前访问人数过多，请稍后重试!";
            }
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            return "系统出现了错误!";
        }
    }
}

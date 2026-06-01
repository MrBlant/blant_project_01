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
 * 限流切面实现
 * 基于 Google Guava RateLimiter（令牌桶算法），
 * 通过 @MayiktCurrentLimit 注解对接口方法进行限流控制
 */
@Aspect
@Component
public class CurrentLimitAop {

    /** 限流器缓存，key 为注解中的 name，value 为对应的 RateLimiter 实例 */
    private ConcurrentHashMap<String, RateLimiter> rateLimiters = new ConcurrentHashMap();

    /**
     * 环绕通知，拦截所有标注了 @MayiktCurrentLimit 的方法
     * 先获取令牌，获取成功则执行原方法，否则直接返回限流提示
     */
    @Around(value = "@annotation(org.example.springboot.aspect.MayiktCurrentLimit)")
    public Object around(ProceedingJoinPoint joinPoint) {
        try {
            // 获取方法签名，并从中解析 @MayiktCurrentLimit 注解
            Signature sig = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) sig;
            MayiktCurrentLimit mayiktCurrentLimit =
                    methodSignature.getMethod().getDeclaredAnnotation(MayiktCurrentLimit.class);
            if (mayiktCurrentLimit == null) {
                return joinPoint.proceed();
            }

            // 从注解中获取限流器名称和每秒令牌数
            String name = mayiktCurrentLimit.name();
            double token = mayiktCurrentLimit.token();

            // 尝试从缓存获取或创建新的限流器
            RateLimiter rateLimiter = rateLimiters.get(name);
            if (rateLimiter == null) {
                rateLimiter = RateLimiter.create(token);
                rateLimiters.put(name, rateLimiter);
            }

            // 尝试获取令牌（非阻塞），获取失败说明超过限流阈值
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

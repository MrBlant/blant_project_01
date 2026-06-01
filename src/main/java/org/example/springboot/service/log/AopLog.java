package org.example.springboot.service.log;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * AOP 请求日志切面
 * 在 Service 层方法执行前，记录请求时间、URL、IP、类名、方法名和参数到日志文件
 */
@Aspect
@Component
@Slf4j
public class AopLog {
    private static final String START_TIME = "request-start";
    private SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");

    /**
     * 切入点：匹配 org.example.springboot.service 包下的所有方法
     */
    @Pointcut("execution(* org.example.springboot.service.*.*(..))")
    public void log() {
    }

    /**
     * 前置通知：在目标方法执行前记录请求相关信息
     */
    @Before("log()")
    public void beforeLog(JoinPoint point) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        LogManage.addLog("【请求时间】：" + sdf4.format(new Date()));
        LogManage.addLog("【请求 URL】：" + request.getRequestURL());
        LogManage.addLog("【请求 IP】：" + request.getRemoteAddr());
        LogManage.addLog("【类名 Class】：" + point.getSignature().getDeclaringTypeName());
        LogManage.addLog("【方法名 Method】：" + point.getSignature().getName());
        LogManage.addLog("【请求参数 Args】：" + JSON.toJSONString(point.getArgs()));
    }
}

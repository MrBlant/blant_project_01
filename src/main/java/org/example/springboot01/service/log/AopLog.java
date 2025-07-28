package org.example.springboot01.service.log;

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

@Aspect
@Component
@Slf4j
public class AopLog {
    private static final String START_TIME = "request-start";
    private SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");

    /**
     * 切入点
     */
    @Pointcut("execution(* org.example.springboot01.service.*.*(..))")
    public void log() {
    }

    /**
     * 前置操作
     *
     * @param point 切入点
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
        LogManage.addLog("【请求参数 Args】：" + JSON.toJSONString(point.getArgs())
        );
//        log.info("【请求时间】：{}", System.currentTimeMillis());
//        log.info("【请求 URL】：{}", request.getRequestURL());
//        log.info("【请求 IP】：{}", request.getRemoteAddr());
//        log.info("【类名 Class】：{}，【方法名 Method】：{}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName());
//        log.info("【请求参数 Args】：{}，", point.getArgs());
    }
}

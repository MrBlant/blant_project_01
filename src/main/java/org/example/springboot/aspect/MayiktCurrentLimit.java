package org.example.springboot.aspect;

import java.lang.annotation.*;

/**
 * 自定义限流注解
 * 标注在方法上，配合 CurrentLimitAop 实现接口限流
 */
@Target(ElementType.METHOD) // 注解可标注在方法上
@Retention(RetentionPolicy.RUNTIME) // 注解保留到运行时，可通过反射读取
@Inherited // 父类注解可被子类继承
public @interface MayiktCurrentLimit {
    /** 限流器名称（用于区分不同接口的限流器） */
    public String name();
    /** 每秒允许通过的请求数（令牌桶容量） */
    public int  token();
}

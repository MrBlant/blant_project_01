package org.example.springboot01.aspect;

import java.lang.annotation.*;

/**
 * 限流注解
 */
@Target(ElementType.METHOD) // 指定新注解可以标注在方法上
@Retention(RetentionPolicy.RUNTIME) // 指定新注解保留到程序运行时期
@Inherited // 指定新注解标注在父类上时可被子类继承
public @interface MayiktCurrentLimit {
    //方法名
    public String name();
    //限流次数
    public int  token();
}

package org.example.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 应用启动入口
 * 通过 @MapperScan 指定 MyBatis Mapper 接口扫描路径
 */
@SpringBootApplication
@MapperScan("org.example.springboot.mapper")
public class SpringbootApplication {

    public static void main(String[] args) {
        try {
            // 启动 Spring Boot 应用
            SpringApplication.run(SpringbootApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package org.example.springboot01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 */
@SpringBootApplication
@MapperScan("org.example.springboot01.mapper")
public class Springboot01Application {

    public static void main(String[] args) {
        try {
            SpringApplication.run(Springboot01Application.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

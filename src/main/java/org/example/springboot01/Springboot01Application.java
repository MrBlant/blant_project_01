package org.example.springboot01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 */
@SpringBootApplication
public class Springboot01Application {

    public static void main(String[] args) {
        try {
            SpringApplication.run(Springboot01Application.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

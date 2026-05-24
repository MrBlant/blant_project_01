package org.example.springboot01.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    private Long id;
    
    private String username;
    
    private String password;
    
    private String email;
    
    private String phone;
    
    private Integer status; // 0:禁用 1:启用
    
    private LocalDateTime createTime;
    
    private LocalDateTime updateTime;
}

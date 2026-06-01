package org.example.springboot.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * 用户实体类，对应数据库用户表
 * 使用 Lombok @Data 自动生成 getter/setter/toString 等方法
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /** 用户ID（主键） */
    private Long id;

    /** 用户名（唯一） */
    private String username;

    /** 密码 */
    private String password;

    /** 邮箱 */
    private String email;

    /** 手机号 */
    private String phone;

    /** 状态：0-禁用 1-启用 */
    private Integer status;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新时间 */
    private LocalDateTime updateTime;
}

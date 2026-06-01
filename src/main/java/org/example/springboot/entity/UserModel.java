package org.example.springboot.entity;

/**
 * 用户模型类（简单 POJO，用于演示）
 * 包含用户名和年龄两个基本字段
 */
public class UserModel {
    String userName;
    int age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

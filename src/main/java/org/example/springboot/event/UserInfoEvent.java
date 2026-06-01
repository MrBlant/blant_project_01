package org.example.springboot.event;

import org.springframework.context.ApplicationEvent;

/**
 * 用户信息变更事件
 * 继承 ApplicationEvent，用于在用户信息变更时触发异步通知（如邮件、短信）
 */
public class UserInfoEvent extends ApplicationEvent {
    public UserInfoEvent(Object source) {
        super(source);
    }
}

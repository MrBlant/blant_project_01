package org.example.springboot.event;

import org.springframework.context.ApplicationEvent;

public class UserInfoEvent extends ApplicationEvent {
    public UserInfoEvent(Object source) {
        super(source);
    }
}

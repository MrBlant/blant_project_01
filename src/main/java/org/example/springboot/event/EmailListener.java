package org.example.springboot.event;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

/**
 * 邮件通知监听器
 * 监听 UserInfoEvent 事件，异步发送邮件通知
 */
public class EmailListener {
    private static final Logger log = (Logger) LoggerFactory.getLogger(EmailListener.class);

    @EventListener
    public void emailListener(UserInfoEvent userInfoEvent) {
        log.debug("userInfoEvent:{}", userInfoEvent);
    }
}

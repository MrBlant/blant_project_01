package org.example.springboot01.event;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

public class EmailListener {
    private static final Logger log = (Logger) LoggerFactory.getLogger(EmailListener.class);

    @EventListener
    public void emailListener(UserInfoEvent userInfoEvent) {
        log.debug("userInfoEvent:{}", userInfoEvent);
    }
}

package org.example.springboot.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;


public class PhoneListener {
    private static final Logger log = LoggerFactory.getLogger(PhoneListener.class);

    @EventListener
    public void emailListener(UserInfoEvent userInfoEvent) {
        log.debug("userInfoEvent:{}", userInfoEvent);
    }
}

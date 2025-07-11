package org.example.springboot01.internation;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageUtils {
    private static MessageSource messageSource;

    public MessageUtils(MessageSource messageSource) {
        MessageUtils.messageSource = messageSource;
    }

    /**
     * 获取单个国际化翻译值
     */
    public static String get(String msgKey) {
        try {
            Locale locale= LocaleContextHolder.getLocale();
            return messageSource.getMessage(msgKey, null, locale);
        } catch (Exception e) {
            return msgKey;
        }
    }

    /**
     * 有传参
     * @param key
     * @param params
     * @return
     */
    public static String getMessage(String key, String[] params) {
        return messageSource.getMessage(key, params, LocaleContextHolder.getLocale());
    }

    /**
     * 有传参有默认值。或者没有参数会设置为默认值
     * @param key
     * @param params
     * @param defaultValue
     * @return
     */
    public static String getMessage(String key, String[] params, String defaultValue) {
        return messageSource.getMessage(key, params, defaultValue, LocaleContextHolder.getLocale());
    }




}

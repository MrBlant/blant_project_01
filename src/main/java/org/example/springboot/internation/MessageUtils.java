package org.example.springboot.internation;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * 国际化消息工具类
 * 封装 MessageSource，提供静态方法方便获取国际化翻译文本
 */
@Component
public class MessageUtils {
    private static MessageSource messageSource;

    public MessageUtils(MessageSource messageSource) {
        MessageUtils.messageSource = messageSource;
    }

    /**
     * 获取单个国际化翻译值（根据当前请求的语言环境）
     */
    public static String get(String msgKey) {
        try {
            Locale locale = LocaleContextHolder.getLocale();
            return messageSource.getMessage(msgKey, null, locale);
        } catch (Exception e) {
            return msgKey;
        }
    }

    /**
     * 获取带参数的国际化消息
     *
     * @param key    消息 key
     * @param params 格式化参数
     * @return 格式化后的翻译文本
     */
    public static String getMessage(String key, String[] params) {
        return messageSource.getMessage(key, params, LocaleContextHolder.getLocale());
    }

    /**
     * 获取带参数的国际化消息，key 不存在时返回默认值
     *
     * @param key          消息 key
     * @param params       格式化参数
     * @param defaultValue 默认值
     * @return 翻译文本或默认值
     */
    public static String getMessage(String key, String[] params, String defaultValue) {
        return messageSource.getMessage(key, params, defaultValue, LocaleContextHolder.getLocale());
    }




}

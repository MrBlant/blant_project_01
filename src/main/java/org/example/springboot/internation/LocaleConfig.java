package org.example.springboot.internation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * 国际化（i18n）配置
 * 配置消息源读取路径、默认语言环境以及语种切换拦截器
 */
@Configuration
public class LocaleConfig {

    /**
     * 配置国际化消息源，读取 classpath:international/message 前缀的 properties 文件
     * 如 messages_zh_CN.properties、messages_en_US.properties
     */
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("international/message");
        source.setDefaultEncoding("UTF-8");
        return source;
    }

    /**
     * 默认语言解析器，基于 Session 存储当前用户的语言偏好
     * 默认使用英文（Locale.US）
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    /**
     * 注册语种切换拦截器
     * 通过请求参数 "lang" 切换语言（例如 ?lang=zh_CN）
     */
    @Bean
    public WebMvcConfigurer localeInterceptor() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
                localeChangeInterceptor.setParamName("lang");
                registry.addInterceptor(localeChangeInterceptor);
            }
        };
    }
}

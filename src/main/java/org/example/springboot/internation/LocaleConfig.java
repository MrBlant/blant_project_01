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

@Configuration
public class LocaleConfig {

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        //设置基础名
        source.setBasenames("international/message");
        //设置编码
        source.setDefaultEncoding("UTF-8");
        return source;
    }

    /**
     * 默认解析器 其中locale表示默认语言 LocaleResolver 用于设置当前会话的默认的国际化语言。
     */
    @Bean
    public LocaleResolver localeResolver() {
        // 基于Session的解析器
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);//指定默认语言，,英文可指定Locale.US
        return slr;

        // 或者使用基于Cookie的解析器
        // CookieLocaleResolver clr = new CookieLocaleResolver();
        // clr.setDefaultLocale(Locale.US);
        // clr.setCookieName("lang");
        // return clr;
    }

//    @Autowired
//    private LanguageChangeInterceptor languageChangeInterceptor;
//    /**
//     * 使用拦截器实现语种切换
//     * @return
//     */
//    @Bean
//    public WebMvcConfigurer localeInterceptor() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
//                // TODO Auto-generated method stub
//                registry.addInterceptor(languageChangeInterceptor);
//            }
//
//        };
//    }

    /**
     * 使用拦截器实现语种切换
     *
     * @return
     */
    @Bean
    public WebMvcConfigurer localeInterceptor() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                // TODO Auto-generated method stub
                LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
                localeChangeInterceptor.setParamName("lang");
                registry.addInterceptor(localeChangeInterceptor);
            }

        };
    }

}

package org.example.springboot.internation;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 语种切换拦截器（当前未启用）
 * 用于在请求到达时根据参数切换语言环境
 * 目前 LocaleConfig 中已通过 LocaleChangeInterceptor 实现了相同功能
 */
@Component
public class LanguageChangeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String language = request.getParameter("lang");
//        String newLocale = request.getParameter(language);
//        if (newLocale != null && this.checkHttpMethod(request.getMethod())) {
//            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
//            if (localeResolver == null) {
//                throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
//            }
//            try {
//                localeResolver.setLocale(request, response, this.parseLocaleValue(newLocale));
//            } catch (IllegalArgumentException ex) {
//                if (!this.isIgnoreInvalidLocale()) {
//                    throw ex;
//                }
//                if (this.logger.isDebugEnabled()) {
//                    this.logger.debug("Ignoring invalid locale value [" + newLocale + "]: " + ex.getMessage());
//                }
//            }
//        }
        return true;
    }
}

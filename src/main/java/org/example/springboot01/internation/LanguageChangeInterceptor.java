package org.example.springboot01.internation;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Component
public class LanguageChangeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String language = request.getParameter("lang"); // 或者其他方式获取语言代码
//        String newLocale = request.getParameter(language);
//        if (newLocale != null && this.checkHttpMethod(request.getMethod())) {
//            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
//            if (localeResolver == null) {
//                throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
//            }
//
//            try {
//                localeResolver.setLocale(request, response, this.parseLocaleValue(newLocale));
//            } catch (IllegalArgumentException ex) {
//                if (!this.isIgnoreInvalidLocale()) {
//                    throw ex;
//                }
//
//                if (this.logger.isDebugEnabled()) {
//                    this.logger.debug("Ignoring invalid locale value [" + newLocale + "]: " + ex.getMessage());
//                }
//            }
//        }

        return true;
    }
}

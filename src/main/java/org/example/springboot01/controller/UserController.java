package org.example.springboot01.controller;

import com.blant.data.init.ext.MayiktErrorLog;
import com.blant.data.init.wx.mp.config.WxMpProperties;
import org.example.springboot01.entity.UserModel;
import org.example.springboot01.internation.MessageUtils;
import org.example.springboot01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@MayiktErrorLog
public class UserController {

    @Autowired
    public UserService userService;
    @Resource
    public LocaleResolver localeResolver;

    /**
     * @param lang http://localhost:8080/index
     * @return
     */
    @GetMapping("/index")
    @ResponseBody
    public String list(String lang) {
//        List<UserModel> list = userService.getUser();
        String msg = MessageUtils.get("greeting");
        int q = 0 / 0;
//        String[] params = {"zhm", "China"};
//        String msg =MessageUtils.getMessage("testParam",params);
        WxMpProperties aa = new WxMpProperties();
        aa.setConfigs(null);
        return msg;
    }

    @GetMapping("/index2")
    @ResponseBody
    public String list2(String lang) {

        List<UserModel> list = userService.getUser();
        String msg = MessageUtils.get("greeting");
//        String[] params = {"zhm", "China"};
//        String msg =MessageUtils.getMessage("testParam",params);
        return msg;
    }

    @PostMapping("/update")
    @ResponseBody
    public String update(@RequestBody Map<String, String> request) {
//        Locale currentLocale = new Locale("en", "US"); // 或者从用户设置中获取Locale
//        ResourceBundle messages = ResourceBundle.getBundle("Messages", currentLocale);
//        if (lang.equals("zh_CN")) {
//            LocaleContextHolder.setLocale(Locale.CHINA);
//        } else {
//            //en_US
//            LocaleContextHolder.setLocale(Locale.US);
//        }
        return "success";

    }


//    public void LanguageSwitchController(LocaleResolver localeResolver) {
//        this.localeResolver = localeResolver;
//    }
//
//    @GetMapping("/switchLang")
//    public String switchLanguage(HttpServletRequest request, HttpServletResponse response, @RequestParam String lang) {
//        localeResolver.setLocale(request, response, new Locale(lang)); // 设置新的语言环境到请求中
//        return "redirect:/"; // 重定向到首页或其他页面，根据需要调整
//    }
}

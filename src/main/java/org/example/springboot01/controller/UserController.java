package org.example.springboot01.controller;

//import com.blant.data.init.ext.MayiktErrorLog;
//import com.blant.data.init.wx.mp.config.WxMpProperties;
import org.example.springboot01.aspect.MayiktCurrentLimit;
import org.example.springboot01.entity.User;
import org.example.springboot01.internation.MessageUtils;
import org.example.springboot01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
//@MayiktErrorLog
public class UserController {

    @Autowired
    public UserService userService;

    @Resource
    public LocaleResolver localeResolver;

    /**
     * 查询所有用户
     * http://localhost:8080/api/users
     * @return 用户列表
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> list() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<User> users = userService.findAll();
            result.put("code", 200);
            result.put("message", "查询成功");
            result.put("data", users);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "查询失败: " + e.getMessage());
            return ResponseEntity.status(500).body(result);
        }
    }

    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户信息
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            return userService.findById(id)
                .map(user -> {
                    result.put("code", 200);
                    result.put("message", "查询成功");
                    result.put("data", user);
                    return ResponseEntity.ok(result);
                })
                .orElseGet(() -> {
                    result.put("code", 404);
                    result.put("message", "用户不存在");
                    return ResponseEntity.status(404).body(result);
                });
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "查询失败: " + e.getMessage());
            return ResponseEntity.status(500).body(result);
        }
    }

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户信息
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<Map<String, Object>> getByUsername(@PathVariable String username) {
        Map<String, Object> result = new HashMap<>();
        try {
            return userService.findByUsername(username)
                .map(user -> {
                    result.put("code", 200);
                    result.put("message", "查询成功");
                    result.put("data", user);
                    return ResponseEntity.ok(result);
                })
                .orElseGet(() -> {
                    result.put("code", 404);
                    result.put("message", "用户不存在");
                    return ResponseEntity.status(404).body(result);
                });
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "查询失败: " + e.getMessage());
            return ResponseEntity.status(500).body(result);
        }
    }

    /**
     * 创建用户
     * @param user 用户信息
     * @return 创建结果
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();
        try {
            // 检查用户名是否已存在
            if (userService.existsByUsername(user.getUsername())) {
                result.put("code", 400);
                result.put("message", "用户名已存在");
                return ResponseEntity.badRequest().body(result);
            }
            
            User savedUser = userService.save(user);
            result.put("code", 200);
            result.put("message", "创建成功");
            result.put("data", savedUser);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "创建失败: " + e.getMessage());
            return ResponseEntity.status(500).body(result);
        }
    }

    /**
     * 更新用户
     * @param id 用户ID
     * @param user 用户信息
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @RequestBody User user) {
        Map<String, Object> result = new HashMap<>();
        try {
            user.setId(id);
            User updatedUser = userService.update(user);
            result.put("code", 200);
            result.put("message", "更新成功");
            result.put("data", updatedUser);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "更新失败: " + e.getMessage());
            return ResponseEntity.status(500).body(result);
        }
    }

    /**
     * 删除用户
     * @param id 用户ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            userService.deleteById(id);
            result.put("code", 200);
            result.put("message", "删除成功");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "删除失败: " + e.getMessage());
            return ResponseEntity.status(500).body(result);
        }
    }

    /**
     * 限流自定义注解test
     * @return
     */
    @GetMapping("/add")
    @MayiktCurrentLimit(name = "add", token = 1)
    public String add() {
        return "my is add";
    }


    public void LanguageSwitchController(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    @GetMapping("/switchLang")
    public String switchLanguage(HttpServletRequest request, HttpServletResponse response, @RequestParam String lang) {
        localeResolver.setLocale(request, response, new Locale(lang)); // 设置新的语言环境到请求中
        return "redirect:/"; // 重定向到首页或其他页面，根据需要调整
    }

    /**
     * 国际化测试接口
     * @param lang 语言参数
     * @return 国际化消息
     */
    @GetMapping("/index")
    public String index(String lang) {
        String msg = MessageUtils.get("greeting");
        return msg;
    }
    
    /**
     * 获取用户信息（旧接口，保留以兼容）
     * @return 成功消息
     */
    @GetMapping("/getUserInfo")
    public String getUserInfo() {
        return "success";
    }
}

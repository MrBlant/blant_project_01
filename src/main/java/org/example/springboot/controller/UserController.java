package org.example.springboot.controller;

//import com.blant.data.init.ext.MayiktErrorLog;
//import com.blant.data.init.wx.mp.config.WxMpProperties;
import org.example.springboot.aspect.MayiktCurrentLimit;
import org.example.springboot.entity.User;
import org.example.springboot.internation.MessageUtils;
import org.example.springboot.service.UserService;
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

/**
 * 用户管理 REST 控制器
 * 提供用户的 CRUD 操作接口，以及国际化切换、限流测试等接口
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    public UserService userService;

    @Resource
    public LocaleResolver localeResolver;

    /**
     * 查询所有用户
     * GET /api/users
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
     * 根据 ID 查询用户
     * GET /api/users/{id}
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
     * GET /api/users/username/{username}
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
     * POST /api/users
     * 创建前会检查用户名是否已存在
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
     * 更新用户信息
     * PUT /api/users/{id}
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
     * DELETE /api/users/{id}
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
     * 限流测试接口（演示 @MayiktCurrentLimit 注解）
     * 配置为每秒仅允许 1 个请求通过
     */
    @GetMapping("/add")
    @MayiktCurrentLimit(name = "add", token = 1)
    public String add() {
        return "my is add";
    }

    public void LanguageSwitchController(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    /**
     * 手动切换语言
     * GET /api/users/switchLang?lang=zh_CN
     */
    @GetMapping("/switchLang")
    public String switchLanguage(HttpServletRequest request, HttpServletResponse response, @RequestParam String lang) {
        localeResolver.setLocale(request, response, new Locale(lang));
        return "redirect:/";
    }

    /**
     * 国际化测试接口
     * GET /api/users/index
     * 返回当前语言环境下的 greeting 翻译文本
     */
    @GetMapping("/index")
    public String index(String lang) {
        return MessageUtils.get("greeting");
    }

    /**
     * 旧接口，保留以兼容
     * GET /api/users/getUserInfo
     */
    @GetMapping("/getUserInfo")
    public String getUserInfo() {
        return "success";
    }
}

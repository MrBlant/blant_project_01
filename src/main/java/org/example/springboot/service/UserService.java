package org.example.springboot.service;

import org.example.springboot.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    
    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户信息
     */
    Optional<User> findById(Long id);
    
    /**
     * 查询所有用户
     * @return 用户列表
     */
    List<User> findAll();
    
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户信息
     */
    Optional<User> findByUsername(String username);
    
    /**
     * 保存用户
     * @param user 用户信息
     * @return 保存后的用户信息
     */
    User save(User user);
    
    /**
     * 更新用户
     * @param user 用户信息
     * @return 更新后的用户信息
     */
    User update(User user);
    
    /**
     * 删除用户
     * @param id 用户ID
     */
    void deleteById(Long id);
    
    /**
     * 根据状态查询用户列表
     * @param status 状态
     * @return 用户列表
     */
    List<User> findByStatus(Integer status);
    
    /**
     * 检查用户名是否存在
     * @param username 用户名
     * @return 是否存在
     */
    boolean existsByUsername(String username);
}

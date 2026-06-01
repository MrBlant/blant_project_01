package org.example.springboot.service.impl;

import org.example.springboot.entity.User;
import org.example.springboot.mapper.UserMapper;
import org.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 用户服务实现类
 * 实现 UserService 接口，调用 UserMapper 操作数据库
 * 使用 @Transactional 确保数据操作的事务一致性
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Optional<User> findById(Long id) {
        User user = userMapper.selectById(id);
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        User user = userMapper.selectByUsername(username);
        return Optional.ofNullable(user);
    }

    @Override
    public User save(User user) {
        // 设置默认状态为启用
        if (user.getStatus() == null) {
            user.setStatus(1);
        }
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
        return user;
    }

    @Override
    public User update(User user) {
        if (user.getId() == null) {
            throw new RuntimeException("用户ID不能为空");
        }
        // 检查用户是否存在
        int count = userMapper.countById(user.getId());
        if (count == 0) {
            throw new RuntimeException("用户不存在");
        }
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
        return user;
    }

    @Override
    public void deleteById(Long id) {
        int count = userMapper.countById(id);
        if (count == 0) {
            throw new RuntimeException("用户不存在");
        }
        userMapper.deleteById(id);
    }

    @Override
    public List<User> findByStatus(Integer status) {
        return userMapper.selectByStatus(status);
    }

    @Override
    public boolean existsByUsername(String username) {
        int count = userMapper.countByUsername(username);
        return count > 0;
    }
}

package org.example.springboot01.service;

import org.example.springboot01.entity.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<UserModel> getUser();
}

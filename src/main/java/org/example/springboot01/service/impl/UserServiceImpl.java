package org.example.springboot01.service.impl;

import org.example.springboot01.entity.UserModel;
import org.example.springboot01.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public List<UserModel> getUser() {
        List<UserModel> userModels=new ArrayList<>();
        UserModel userModel=new UserModel();
        userModel.setUserName("test");
        userModel.setAge(11);
        userModels.add(userModel);
        return userModels;
    }
}

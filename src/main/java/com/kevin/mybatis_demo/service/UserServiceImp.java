package com.kevin.mybatis_demo.service;

import com.kevin.mybatis_demo.dao.UserDao;
import com.kevin.mybatis_demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="UserService")
public class UserServiceImp implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User getUser(User user) {
        User app_user=userDao.getUser(user);
        return app_user;
    }
}

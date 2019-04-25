package com.kevin.mybatis_demo.controller;

import com.kevin.mybatis_demo.model.User;
import com.kevin.mybatis_demo.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/getUser")
    private User findUser(User user) {
        return userService.getUser(user);
    }
}

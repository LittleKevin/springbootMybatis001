package com.kevin.mybatis_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VelocityController {
    @RequestMapping("/vm")
    public String  HelloVelocity() {
        return "index";
    }
}

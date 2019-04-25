package com.kevin.mybatis_demo.controller;

import com.kevin.mybatis_demo.model.Appconfig;
import com.kevin.mybatis_demo.service.AppconfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/appConfig")
public class AppConfigController {
    @Autowired
    private AppconfigService appconfigService;

    @ResponseBody
    @GetMapping("/getAppConfig")
    private Appconfig getAppConfigMethod(){
        return appconfigService.getAppConfig();
    }
}

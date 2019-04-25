package com.kevin.mybatis_demo.controller;

import com.kevin.mybatis_demo.model.Hero;
import com.kevin.mybatis_demo.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/hero")
public class HeroController {
    @Autowired
    private HeroService heroService;

    @ResponseBody
    @PostMapping("/add")
    public int addUser(Hero user) {
        return heroService.addUser(user);
    }

    @ResponseBody
    @GetMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize
    ) {
        return heroService.findAllUser(pageNum, pageSize);
    }

    @ResponseBody
    @PostMapping("/update")
    public int updateUser(Hero user){
        return heroService.updateUser(user);
    }

    @ResponseBody
    @GetMapping("/delete")
    public int deleteUser(Hero user){
        return heroService.deleteUser(user);
    }
}

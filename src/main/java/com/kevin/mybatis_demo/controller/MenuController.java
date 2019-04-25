package com.kevin.mybatis_demo.controller;

import com.kevin.mybatis_demo.model.Menu;
import com.kevin.mybatis_demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/menuList")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @ResponseBody
    @GetMapping("/getMenuList")
    private List<Menu> getTotalListOfMenu(){
        return menuService.getMenuLists();
    }
}

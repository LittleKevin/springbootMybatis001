package com.kevin.mybatis_demo.service;

import com.kevin.mybatis_demo.dao.MenuDao;
import com.kevin.mybatis_demo.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="MenuService")
public class MenuServiceImp  implements MenuService{
    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> getMenuLists() {
        return menuDao.getMenuLists();
    }
}

package com.kevin.mybatis_demo.service;

import com.kevin.mybatis_demo.dao.AppconfigDao;
import com.kevin.mybatis_demo.model.Appconfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="Appconfig")
public class AppconfigServiceImp implements AppconfigService {
    @Autowired
    private AppconfigDao appconfigDao;
    @Override
    public Appconfig getAppConfig() {
        return appconfigDao.getAppConfig();
    }
}

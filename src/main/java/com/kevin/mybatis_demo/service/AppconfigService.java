package com.kevin.mybatis_demo.service;

import com.kevin.mybatis_demo.model.Appconfig;

import java.io.InputStream;
import java.util.List;

public interface AppconfigService {
    Appconfig getAppConfig();
    List getExcelList(InputStream in, String fileName) throws Exception;
}

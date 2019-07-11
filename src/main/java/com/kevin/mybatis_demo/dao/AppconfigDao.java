package com.kevin.mybatis_demo.dao;

import com.kevin.mybatis_demo.model.Appconfig;

import java.util.List;
import java.util.Map;

public interface AppconfigDao {
    Appconfig getAppConfig();

    List<Map<String, String>> getExcelColumnsList();
}

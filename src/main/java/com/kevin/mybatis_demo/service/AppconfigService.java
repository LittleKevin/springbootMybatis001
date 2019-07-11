package com.kevin.mybatis_demo.service;

import com.kevin.mybatis_demo.model.Appconfig;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

public interface AppconfigService {
    Appconfig getAppConfig();
    List getExcelList(InputStream in, String fileName) throws Exception;

    void exportExcel(HttpServletResponse response)throws Exception;
}

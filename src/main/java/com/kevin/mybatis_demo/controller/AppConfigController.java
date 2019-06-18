package com.kevin.mybatis_demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kevin.mybatis_demo.model.Appconfig;
import com.kevin.mybatis_demo.model.CompanyInfo;
import com.kevin.mybatis_demo.service.AppconfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping(value = "/upload")
    @ResponseBody
    public String upLoadExcel(HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("filename");
        if (file.isEmpty()) {
            return "文件不能为空";
        }
        InputStream inputStream = file.getInputStream();
        List<List<Object>> list = appconfigService.getExcelList(inputStream, file.getOriginalFilename());
        inputStream.close();

        for (int i = 0; i < list.size(); i++) {
            List<Object> lo = list.get(i);
            //TODO 随意发挥
            System.out.println(lo);

        }
        return "上传成功";
    }
    @PostMapping(value = "/getList")
    @ResponseBody
    public String getList(@RequestBody List<CompanyInfo> list) throws Exception{
        List<CompanyInfo> companyInfos=new ArrayList<>();
        companyInfos=list;
       System.out.println("list"+companyInfos);
        System.out.println("listSize:"+companyInfos.size());
       for(CompanyInfo companyInfo:companyInfos){
           System.out.println("companyName："+companyInfo.getCompanyName());
       }
        return "接收成功";
    }

}

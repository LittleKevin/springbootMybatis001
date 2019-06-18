package com.kevin.mybatis_demo.service;

import com.kevin.mybatis_demo.dao.AppconfigDao;
import com.kevin.mybatis_demo.model.Appconfig;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service(value="Appconfig")
public class AppconfigServiceImp implements AppconfigService {
    @Autowired
    private AppconfigDao appconfigDao;
    @Override
    public Appconfig getAppConfig() {
        return appconfigDao.getAppConfig();
    }

    @Override
    public List getExcelList(InputStream in, String fileName) throws Exception {
        List list=new ArrayList();
        //创建Excel工作薄
        Workbook workbook=this.getWorkBook(in,fileName);
        Sheet sheet=null;
        Row row=null;
        Cell cell=null;
        for(int i=0;i<workbook.getNumberOfSheets();i++){
            sheet=workbook.getSheetAt(i);
            if(sheet==null){
               continue;
            }
            for(int j=sheet.getFirstRowNum();j<=sheet.getLastRowNum();j++){
                row =sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }
                List<Object> li = new ArrayList<>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    li.add(cell);
                }
                list.add(li);
            }
        }
        workbook.close();

        return list;
    }
    /**
     * 判断文件格式
     */
    public Workbook getWorkBook(InputStream inStr,String fileName) throws Exception{
        Workbook workbook = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (".xls".equals(fileType)) {
            workbook = new HSSFWorkbook(inStr);
        } else if (".xlsx".equals(fileType)) {
            workbook = new XSSFWorkbook(inStr);
        } else {
            throw new Exception("请上传excel文件！");
        }
        return workbook;
    }
}

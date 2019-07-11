package com.kevin.mybatis_demo.service;

import com.kevin.mybatis_demo.dao.AppconfigDao;
import com.kevin.mybatis_demo.model.Appconfig;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    @Override
    public void exportExcel(HttpServletResponse response) throws Exception{
       List<Map<String,String>>  list= appconfigDao.getExcelColumnsList();
     // 设置表头名称
        String[]  columnNames={"column01","column02","column03","column04","column05","column06","column07"};
        String[]  columns={"account","user_name","password","status","role_name","mobile","warehouse_addr"};
        SXSSFWorkbook wb= createWorkbook(list,columnNames,columns);

        //弹出下载框
        response.reset();
        String fileName=new String(("测试"+".xlsx").getBytes("gb2312"), "iso8859-1");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setContentType("application/vnd.ms-excel;charset=GBK");
        OutputStream os = response.getOutputStream();
        wb.write(os);
        os.close();
    }
    //创建一个Excel工作薄
    public SXSSFWorkbook createWorkbook(List<Map<String, String>> list,String [] columnNames,String[] columns){
        SXSSFWorkbook workbook=new SXSSFWorkbook();
        int maxLenth=3;
        int times=0;
        //数据量
        int count=list.size();
        if(count>=maxLenth){
            times=count / maxLenth;
                for(int i=0;i<times;i++){
                    List<Map<String, String>> tempList=new ArrayList<>();
                    for(int j=i*maxLenth;j<(i+1)*maxLenth;j++){
                        tempList.add(list.get(j));
                    }
                    createSheets(workbook,"测试"+i,columnNames,columns,tempList);
                }
            if(count % maxLenth!=0 ){
                times+=1;
                List<Map<String, String>> last=new ArrayList<>();
                for(int i=maxLenth*(times-1);i<count;i++){
                    last.add(list.get(i));
                }
                createSheets(workbook,"测试"+times,columnNames,columns,last);
            }
        }else{
            createSheets(workbook,"测试",columnNames,columns,list);
        }

        return workbook;
    }
    private void createSheets(SXSSFWorkbook workbook,String sheetName,String [] columnNames,String[] columns,List<Map<String, String>> list){
        //过滤隐藏列
        List<String> columnsList = new ArrayList<>(Arrays.asList(columns));
        List<String> columnNamesList = new ArrayList<>(Arrays.asList(columnNames));
        columns = columnsList.toArray(new String[columnsList.size()]);
        columnNames = columnNamesList.toArray(new String[columnNamesList.size()]);

        Sheet sheet=workbook.createSheet(sheetName);
        //设置表头、列名称
        Row row = sheet.createRow((int) 0);
        CellStyle headStyle=setHeaderStyle(workbook);
        CellStyle style=setCellStyle(workbook);
        //设置表名称
        for(int i=0;i<columnNames.length;i++){
            row.createCell(i).setCellValue(columnNames[i]);
            row.getCell(i).setCellStyle(headStyle);
            row.setHeightInPoints(20);
            if(!columnNames[i].isEmpty()){
                sheet.setColumnWidth(i, columnNames[i].getBytes().length*2*256);
            }
        }

        int rowIndex = 0;
        for (Map data : list) {
            rowIndex++;
            Row r = sheet.createRow((int) rowIndex);
            for (int j = 0; j < columns.length; j++) {
                String value = data.get(columns[j])==null?"":data.get(columns[j]).toString();
                r.createCell(j).setCellValue(value);
                r.getCell(j).setCellStyle(style);
                r.setHeightInPoints(20);
            }
        }
    }
    //设置表头样式
    private CellStyle setHeaderStyle(SXSSFWorkbook workbook){
        CellStyle headStyle = workbook.createCellStyle();
        Font headFont = workbook.createFont();
        headFont.setFontHeightInPoints((short) 12);//设置字体大小
        headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体粗细
        headStyle.setFont(headFont);
        headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("@"));
        return headStyle;
    }
    //设置表格样式
    private CellStyle setCellStyle(SXSSFWorkbook workbook){
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 10);//设置字体大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);//粗体粗细
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("@"));
        return style;
    }
}

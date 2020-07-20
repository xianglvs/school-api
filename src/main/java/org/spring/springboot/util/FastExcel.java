package org.spring.springboot.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class FastExcel<T> {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(FastExcel.class);

    //每页的通用名称
    private String sheetName;

    //excel名称
    private String excelName;

    public FastExcel(){

    }

    public FastExcel(String sheetName, String excelName) {
        this.sheetName = sheetName;
        this.excelName = excelName;
    }

    //将一个集合hashmap转化成excel文件写入到相应流中去
    public OutputStream ListHashMapToExcelStream(HttpServletResponse response, List<T> list, Class _class) {

        try {
            OutputStream outputStream = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String name = this.excelName == null ? "performanceIndex" : this.excelName;
            this.excelName = URLEncoder.encode(name+System.currentTimeMillis(), "UTF-8");
            response.addHeader("Content-Disposition","attachment;filename="+this.excelName + ".xls");
            // 这里 需要指定写用哪个class去写
            ExcelWriter excelWriter = EasyExcel.write(outputStream,_class)
                    .needHead(true)
                    .registerWriteHandler(new CustomHandler())
                    .build();
            WriteSheet writeSheet = EasyExcel.writerSheet(this.sheetName).build();
            excelWriter.write(list, writeSheet);
            /// 千万别忘记finish 会帮忙关闭流
            excelWriter.finish();
            return outputStream;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //用于文件上传
    public void upload(MultipartFile file, AnalysisEventListener<Map<Integer,String>> listener) throws IOException {
        InputStream inputStream = file.getInputStream();
        EasyExcel.read(inputStream,new AnalysisEventListener<Map<Integer,String>>(){
            @Override
            public void invoke(Map<Integer, String> data, AnalysisContext analysisContext) {
                listener.invoke(data,analysisContext);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                listener.doAfterAllAnalysed(analysisContext);
            }
        }).sheet().doRead();
    }


    //读取excel
    public static Workbook readExcel(String filePath){
        Workbook wb = null;
        if(filePath==null){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }
    public static Object getCellFormatValue(Cell cell){
        Object cellValue = null;
        if(cell!=null){
            //判断cell类型
            switch(cell.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:{
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case Cell.CELL_TYPE_FORMULA:{
                    //判断cell是否为日期格式
                    if(DateUtil.isCellDateFormatted(cell)){
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    }else{
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING:{
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }

    /**
     * @param inputStream   导入文件输入流
     * @param listener Excel实体映射类
     * @return
     */
    public static Boolean readExcel(InputStream inputStream,AnalysisEventListener listener){
        try {
            ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, null, listener);
            excelReader.read();
        } catch (Exception e) {

        } finally {
            try {
                inputStream.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}

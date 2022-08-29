package com.automatic.AutomaticControl.core.readExcel;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReadExcel {

    public static void readExcel (MultipartFile file) throws IOException, InvalidFormatException {
        // 获取上传的文件
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        // 获取工作表
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        //获取最后一行
        Integer lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i<lastRowNum; i++) {
            // 获取行
            XSSFRow row = sheet.getRow(i);
           if (row != null) {
               // 储存遍历出来的数据
               List<String> list = new ArrayList<>();
               // 获取单元格
               for (Cell cell : row) {
                   if (cell != null && !"".equals(cell)) {
                       // 把单元格转为String类型
                       cell.setCellType(CellType.STRING);
                       // 获取字符串类型的单元格的值
                       String cellValue = cell.getStringCellValue();
//                       System.out.println(cellValue);
                       list.add(cellValue);
                   }
               }
               for (String zgy : list) {
                   System.out.println(zgy);
               }
           }
        }
    }

}

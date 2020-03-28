package com.elphen.miniapp.api.test;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;

/*
 * @ClassName POTest
 * @Auth Elphen
 * @Description TODO
 **/
public class POITest {

    public static void main(String[] args) {

        try {
            //    创建示例文件对象
            File file = new File("../../xxxx.xlsx");
            //    创建工作簿对象
            Workbook wb = new SXSSFWorkbook(1);
            //    创建工作表
            Sheet sheet1 = wb.createSheet("Sheet1");

        } catch (Exception e) {

        } finally {

        }

    }
}

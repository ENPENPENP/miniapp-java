package com.elphen.miniapp.api.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/*
 * @ClassName POTest
 * @Auth Elphen
 * @Description TODO
 **/
public class POITest {

    public static void main(String[] args) throws IOException {

    }

    public void exampleMethod() {
        File file = null;
        Workbook workbook = null;
        OutputStream outputStream = null;
        try {
            //     模拟数据集合
            List<List<StringBuffer>> datas = new ArrayList<>();
            //     生成10行10列的数据集
            for (int row = 0; row < 10; row++) {
                List<StringBuffer> rowDatas = new ArrayList<>();
                for (int col = 0; col < 10; col++) {
                    rowDatas.add(new StringBuffer("[").append(row).append(",").append(col).append("]"));
                }
                datas.add(rowDatas);
            }
            //    创建示例文件对象
            file = new File("../../xxxx.xlsx");
            //    创建工作簿对象
            workbook = new SXSSFWorkbook(1);
            //    创建工作表
            Sheet sheet1 = workbook.createSheet("Sheet1");
            for (int rowNum = 0; rowNum < datas.size(); rowNum++) {
                //     生成行对象
                Row rowObj = sheet1.createRow(rowNum);
                List<StringBuffer> rowDatas = datas.get(rowNum);
                for (int colNum = 0; colNum < rowDatas.size(); colNum++) {
                    //      生成单元格对象
                    Cell cell = rowObj.createCell(colNum);
                    //      设置单元格的内容值
                    cell.setCellValue(rowDatas.get(colNum).toString());
                }
            }
            //      生成文件输出流对象
            outputStream = new FileOutputStream(file);
            //      将工作本写入到磁盘
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //      最后要关闭工作本和流对象
            try {
                workbook.close();
                outputStream.flush();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

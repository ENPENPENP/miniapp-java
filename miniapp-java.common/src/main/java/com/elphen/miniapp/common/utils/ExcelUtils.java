package com.elphen.miniapp.common.utils;

import com.elphen.miniapp.domain.entity.TFileData;
import com.elphen.miniapp.domain.entity.XLXSDataMap;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/*
 * @ClassName ExcelUtils
 * @Auth Elphen
 * @Description TODO
 **/
public class ExcelUtils {
    public static void exportDataToExcel(List<TFileData> fileDataList, String filePath) throws Exception {
        File file = new File(filePath);
        if (file.createNewFile()) {
            //内存中只创建1个对象
            Workbook wb = new SXSSFWorkbook(1);
            // 关键语句
//        获取所有数据行
            Map<Integer, List<String>> dataMap = XLXSDataMap.toMap(fileDataList);
//         创建一个工作簿
            Sheet sheet1 = wb.createSheet("Sheet1");
            //            开始插入列名行，索引由0开始
            for (int curRowNum = 0; curRowNum < dataMap.size(); curRowNum++) {
//           创建行
                Row curRow = sheet1.createRow(curRowNum);
//           获取当前行数据
                List<String> list = dataMap.get(curRowNum);
                for (int curColNum = 0; curColNum < list.size(); curColNum++) {
//               创建单元格，并设置单元格格式
                    Cell curCell = curRow.createCell(curColNum, CellType.STRING);
//               设置单元格的值
                    curCell.setCellValue(list.get(curColNum));
                    if (curRowNum == 0) {
                        //                    设置列的宽度
                        sheet1.setHorizontallyCenter(true);
                        sheet1.setColumnWidth(curColNum, list.get(curColNum).length() * 500 + 1000);
                        curRow.setHeightInPoints(25);
                    }
                }
            }
            OutputStream outputStream = new FileOutputStream(file);
            wb.write(outputStream);
            wb.close();
            outputStream.flush();
            outputStream.close();
        }

    }
}


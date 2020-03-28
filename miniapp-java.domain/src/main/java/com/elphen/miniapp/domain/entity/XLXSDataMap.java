package com.elphen.miniapp.domain.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @ClassName XLXSDataMap
 * @Auth Elphen
 * @Description TODO
 **/
@Data
public class XLXSDataMap {

    private List<TFileData> columnNameList;

    private List<TFileData> rowDataList;

    private List<TFileData> footList;

    private Integer totalRowCount;

    /**
     * @param fileDataList
     * @return
     * @throws Exception
     */
    public static Map<Integer, List<String>> toMap(List<TFileData> fileDataList) throws Exception {
        if (fileDataList.size() == 0) {
            throw new Exception("The size of list must be larger than zero");
        } else {
            XLXSDataMap xlxsDataMap = new XLXSDataMap(fileDataList);
            Map<Integer, List<String>> dataMap = new HashMap<>();
            dataMap.put(0, getRowDataFromJson(xlxsDataMap.getColumnNameList().get(0).getRowData()));
            for (int i = 1; i < xlxsDataMap.getRowDataList().size(); i++) {
                dataMap.put(i, getRowDataFromJson(xlxsDataMap.getRowDataList().get(i).getRowData()));
            }
            for (int i = dataMap.size(); i < xlxsDataMap.getFootList().size(); i++) {
                dataMap.put(i, getRowDataFromJson(xlxsDataMap.getFootList().get(i).getRowData()));
            }
            return dataMap;
        }
    }

    /**
     * @param fileDataList
     * @return
     * @throws Exception
     */
    public static Map<Integer, List<String>> toMap(XLXSDataMap xlxsDataMap) throws Exception {
        Map<Integer, List<String>> dataMap = new HashMap<>();
        dataMap.put(0, getRowDataFromJson(xlxsDataMap.getColumnNameList().get(0).getRowData()));
        for (int i = 1; i < xlxsDataMap.getRowDataList().size(); i++) {
            dataMap.put(i, getRowDataFromJson(xlxsDataMap.getRowDataList().get(i).getRowData()));
        }
        for (int i = dataMap.size(); i < xlxsDataMap.getFootList().size(); i++) {
            dataMap.put(i, getRowDataFromJson(xlxsDataMap.getFootList().get(i).getRowData()));
        }
        return dataMap;
    }

    public XLXSDataMap(List<TFileData> fileDataList) throws Exception {
        if (fileDataList.size() == 0) {
            throw new Exception("The size of list must be larger than zero");
        } else {
            columnNameList = new ArrayList<>();
            rowDataList = new ArrayList<>();
            footList = new ArrayList<>();
            totalRowCount = fileDataList.size();
            for (TFileData fileData : fileDataList) {
                switch (fileData.getRowType()) {
                    case TFileData.COLUMN_NAME_ROWTYPE: {
                        columnNameList.add(fileData);
                        break;
                    }
                    case TFileData.DATA_ROWTYPE: {
                        rowDataList.add(fileData);
                    }
                    case TFileData.FOOT_ROWTYPE: {
                        footList.add(fileData);
                    }
                }
            }
        }
    }

    public static List<String> getRowDataFromJson(Object rowDataObj) {
        List<String> dataList = new ArrayList<>();
        JSONArray rowDataArray = JSON.parseArray(rowDataObj.toString());
        for (int i = 0; i < rowDataArray.size(); i++) {
//                            解析成json对象
            JSONObject rowData = JSON.parseObject(rowDataArray.get(i).toString());
//                            获取data的值，并添加到list中
            dataList.add(rowData.get("data").toString());
        }
        return dataList;
    }
}

package com.elphen.miniapp.domain.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TFileData implements Serializable {
    public final static int COLUMN_NAME_ROWTYPE = 1;
    public final static int COLUMN_NAME_ROWINDEX = -1;
    public final static int DATA_ROWTYPE = 0;
    public final static int FOOT_ROWTYPE = -1;
    /**
     * 文件ID
     */
    private Integer fileId;

    /**
     * 当前行数据在格文件中的行索引
     */
    private Integer rowIndex;

    /**
     * 编辑用户的ID
     */
    private Integer userId;

    /**
     * 行类型，1 为表头， 0 为表数据， -1 为页脚
     */
    private Integer rowType;

    /**
     * 行数据，json格式
     */
    private Object rowData;

    private static final long serialVersionUID = 1L;

    /**
     * 生成表头数据行
     *
     * @param fileId  文件id
     * @param userId  生成当前行数据的用户id
     * @param rowData json格式的行数据
     * @return 数据行对象
     */
    public static com.elphen.miniapp.domain.entity.TFileData columnNameRow(Integer fileId, Integer userId, String rowData) {
        TFileData fileData = new TFileData();
        fileData.setFileId(fileId);
        fileData.setRowIndex(COLUMN_NAME_ROWINDEX);
        fileData.setUserId(userId);
        fileData.setRowType(COLUMN_NAME_ROWTYPE);
        fileData.setRowData(rowData);
        return fileData;
    }

    /**
     * 生成正常的数据行
     *
     * @param fileId   文件id
     * @param userId   生成当前行数据的用户id
     * @param rowData  json格式的行数据
     * @param rowIndex 当前行在文件中的行索引值
     * @return 数据行对象
     */
    public static com.elphen.miniapp.domain.entity.TFileData dataRow(Integer fileId, Integer userId, String rowData, Integer rowIndex) {
        TFileData fileData = new TFileData();
        fileData.setFileId(fileId);
        fileData.setRowIndex(rowIndex);
        fileData.setUserId(userId);
        fileData.setRowType(DATA_ROWTYPE);
        fileData.setRowData(rowData);
        return fileData;
    }

    /**
     * 生成页脚数据行
     *
     * @param fileId   文件id
     * @param userId   生成当前行数据的用户id
     * @param rowData  json格式的行数据
     * @param rowIndex 当前行在文件中的行索引值
     * @return 数据行对象
     */
    public static com.elphen.miniapp.domain.entity.TFileData footRow(Integer fileId, Integer userId, String rowData, Integer rowIndex) {
        TFileData fileData = new TFileData();
        fileData.setFileId(fileId);
        fileData.setRowIndex(rowIndex);
        fileData.setUserId(userId);
        fileData.setRowType(FOOT_ROWTYPE);
        fileData.setRowData(rowData);
        return fileData;
    }
}
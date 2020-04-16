package com.elphen.miniapp.api.mapper;

import com.elphen.miniapp.domain.entity.TFileData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TFileDataMapper {
    int deleteByPrimaryKey(@Param("fileId") Integer fileId, @Param("rowIndex") Integer rowIndex);

    int insert(TFileData record);

    int insertSelective(TFileData record);

    TFileData selectByPrimaryKey(@Param("fileId") Integer fileId, @Param("rowIndex") Integer rowIndex);

    int updateByPrimaryKeySelective(TFileData record);

    int updateByPrimaryKey(TFileData record);

    int deleteByFileId(Integer fileDataId);

    List<TFileData> selectAllByFileId(Integer fileId);

    TFileData selectColNameDataByFileId(Integer fileId);

    int updateRowData(@Param("fileId")Integer fileId, @Param("rowIndex") Integer rowIndex, @Param("rowData") String rowData);
}
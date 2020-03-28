package com.elphen.miniapp.api.service;

import com.elphen.miniapp.domain.entity.TFileData;

import java.util.List;

public interface TFileDataService {

    int deleteByPrimaryKey(Integer fileId, Integer rowIndex);

    int insert(TFileData record);

    int insertSelective(TFileData record);

    TFileData selectByPrimaryKey(Integer fileId, Integer rowIndex);

    int updateByPrimaryKeySelective(TFileData record);

    int updateByPrimaryKey(TFileData record);

    int deleteByFileId(Integer fileId);

    List<TFileData> selectAllByFileId(Integer fileId);

    TFileData getColumnName(Integer fileId);

}






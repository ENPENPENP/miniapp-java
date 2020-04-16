package com.elphen.miniapp.api.service;

import com.elphen.miniapp.domain.entity.TFileInfo;

import java.util.List;

public interface TFileInfoService {

    int insert(TFileInfo record);

    int insertSelective(TFileInfo record);

    TFileInfo selectByPrimaryKey(Integer fileId);

    int updateByPrimaryKeySelective(TFileInfo record);

    int updateByPrimaryKey(TFileInfo record);

    int deleteByFileId(Integer fileId);

    TFileInfo selectByFileId(Integer fileId);

    int deleteByPrimaryKey(Integer fileId);

    List<TFileInfo> getAllGeneratedFile();

    List<TFileInfo> getAllFileInfo();

}










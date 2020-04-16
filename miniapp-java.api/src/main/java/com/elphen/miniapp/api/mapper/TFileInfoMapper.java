package com.elphen.miniapp.api.mapper;

import com.elphen.miniapp.domain.entity.TFileInfo;

import java.util.List;

public interface TFileInfoMapper {
    int deleteByPrimaryKey(Integer fileId);

    int insert(TFileInfo record);

    int insertSelective(TFileInfo record);

    TFileInfo selectByPrimaryKey(Integer fileId);

    int updateByPrimaryKeySelective(TFileInfo record);

    int updateByPrimaryKey(TFileInfo record);

    List<TFileInfo> selectGeneratedFile();

    List<TFileInfo> selectAll();
}
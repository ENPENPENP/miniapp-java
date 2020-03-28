package com.elphen.miniapp.api.service.impl;

import com.elphen.miniapp.api.mapper.TFileInfoMapper;
import com.elphen.miniapp.api.service.TFileInfoService;
import com.elphen.miniapp.domain.entity.TFileInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TFileInfoServiceImpl implements TFileInfoService {

    @Resource
    private TFileInfoMapper tFileInfoMapper;

    @Override
    public int insert(TFileInfo record) {
        return tFileInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(TFileInfo record) {
        return tFileInfoMapper.insertSelective(record);
    }


    @Override
    public int updateByPrimaryKeySelective(TFileInfo record) {
        return tFileInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TFileInfo record) {
        return tFileInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByFileId(Integer fileId) {
        return tFileInfoMapper.deleteByPrimaryKey(fileId);
    }

    @Override
    public TFileInfo selectByFileId(Integer fileId) {
        return tFileInfoMapper.selectByPrimaryKey(fileId);
    }

    @Override
    public int deleteByPrimaryKey(Integer fileInfoId) {
        return tFileInfoMapper.deleteByPrimaryKey(fileInfoId);
    }

    @Override
    public TFileInfo selectByPrimaryKey(Integer fileInfoId) {
        return tFileInfoMapper.selectByPrimaryKey(fileInfoId);
    }
}










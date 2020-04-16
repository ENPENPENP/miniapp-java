package com.elphen.miniapp.api.service.impl;

import com.elphen.miniapp.api.mapper.TFileDataMapper;
import com.elphen.miniapp.api.service.TFileDataService;
import com.elphen.miniapp.domain.entity.TFileData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TFileDataServiceImpl implements TFileDataService {

    @Resource
    private TFileDataMapper tFileDataMapper;

    @Override
    public int deleteByPrimaryKey(Integer fileId, Integer rowIndex) {
        return tFileDataMapper.deleteByPrimaryKey(fileId, rowIndex);
    }

    @Override
    public int insert(TFileData record) {
        return tFileDataMapper.insert(record);
    }

    @Override
    public int insertSelective(TFileData record) {
        return tFileDataMapper.insertSelective(record);
    }

    @Override
    public TFileData selectByPrimaryKey(Integer fileId, Integer rowIndex) {
        return tFileDataMapper.selectByPrimaryKey(fileId, rowIndex);
    }

    @Override
    public int updateByPrimaryKeySelective(TFileData record) {
        return tFileDataMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TFileData record) {
        return tFileDataMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByFileId(Integer fileId) {
        return tFileDataMapper.deleteByFileId(fileId);
    }

    @Override
    public List<TFileData> selectAllByFileId(Integer fileId) {
        return tFileDataMapper.selectAllByFileId(fileId);
    }

    @Override
    public TFileData getColumnName(Integer fileId) {
        return tFileDataMapper.selectColNameDataByFileId(fileId);
    }

    @Override
    public void updateRowData(List<TFileData> tFileDataList) {
        for (TFileData tFileData : tFileDataList) {
            tFileDataMapper.updateRowData(tFileData.getFileId(),tFileData.getRowIndex(),tFileData.getRowData().toString());
        }
    }

}






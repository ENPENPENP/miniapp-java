package com.elphen.miniapp.api.service.impl;

import com.elphen.miniapp.api.mapper.TGroupMapper;
import com.elphen.miniapp.api.service.TGroupService;
import com.elphen.miniapp.domain.entity.TGroup;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TGroupServiceImpl implements TGroupService {

    @Resource
    private TGroupMapper tGroupMapper;

    @Override
    public int insert(TGroup record) {
        return tGroupMapper.insert(record);
    }

    @Override
    public int insertSelective(TGroup record) {
        return tGroupMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(TGroup record) {
        return tGroupMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TGroup record) {
        return tGroupMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByPrimaryKey(String groupId) {
        return tGroupMapper.deleteByPrimaryKey(groupId);
    }

    @Override
    public TGroup selectByPrimaryKey(String groupId) {
        return tGroupMapper.selectByPrimaryKey(groupId);
    }
}



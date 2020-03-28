package com.elphen.miniapp.api.service.impl;

import com.elphen.miniapp.api.mapper.TUserGroupMapper;
import com.elphen.miniapp.api.service.TUserGroupService;
import com.elphen.miniapp.domain.entity.TUserGroup;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TUserGroupServiceImpl implements TUserGroupService {

    @Resource
    private TUserGroupMapper tUserGroupMapper;

    @Override
    public int deleteByPrimaryKey(Integer id, Integer userId, String groupId) {
        return tUserGroupMapper.deleteByPrimaryKey(id, userId, groupId);
    }

    @Override
    public int insert(TUserGroup record) {
        return tUserGroupMapper.insert(record);
    }

    @Override
    public int insertSelective(TUserGroup record) {
        return tUserGroupMapper.insertSelective(record);
    }

    @Override
    public TUserGroup getByUserIdAndGroupId(Integer userId, String groupId) {
        return tUserGroupMapper.selectByUserIdAndGroupId(userId, groupId);
    }

}


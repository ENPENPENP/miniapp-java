package com.elphen.miniapp.api.service;

import com.elphen.miniapp.domain.entity.TUserGroup;

public interface TUserGroupService {


    int deleteByPrimaryKey(Integer id, Integer userId, String groupId);

    int insert(TUserGroup record);

    int insertSelective(TUserGroup record);

    TUserGroup getByUserIdAndGroupId(Integer userId, String groupId);

}


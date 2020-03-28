package com.elphen.miniapp.api.service;

import com.elphen.miniapp.domain.entity.TGroup;

public interface TGroupService {

    int deleteByPrimaryKey(String groupId);

    int insert(TGroup record);

    int insertSelective(TGroup record);

    TGroup selectByPrimaryKey(String groupId);

    int updateByPrimaryKeySelective(TGroup record);

    int updateByPrimaryKey(TGroup record);
}



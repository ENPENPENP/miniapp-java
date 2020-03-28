package com.elphen.miniapp.api.service;

import com.elphen.miniapp.domain.entity.TEventGroup;

public interface TEventGroupService {

    int deleteByPrimaryKey(Integer id, Integer eventId, String groupId);

    int insert(TEventGroup record);

    int insertSelective(TEventGroup record);

    TEventGroup selectByPrimaryKey(Integer id, Integer eventId, String groupId);

    int updateByPrimaryKeySelective(TEventGroup record);

    int updateByPrimaryKey(TEventGroup record);

    TEventGroup getByEventIdAndGroupId( Integer eventId, String groupId);

}



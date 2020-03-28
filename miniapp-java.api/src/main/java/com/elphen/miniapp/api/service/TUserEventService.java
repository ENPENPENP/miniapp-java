package com.elphen.miniapp.api.service;

import com.elphen.miniapp.domain.entity.TUserEvent;

import java.util.List;

public interface TUserEventService{

    int deleteByPrimaryKey(Integer id);

    int insert(TUserEvent record);

    int insertSelective(TUserEvent record);

    TUserEvent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUserEvent record);

    int updateByPrimaryKey(TUserEvent record);

    TUserEvent getByUserIdAndEventId(Integer userId, Integer eventId);

    int deleteByEventId(Integer eventId);

    List<TUserEvent> getAllByUserId(Integer userId);

    List<TUserEvent> getAllByEventId(Integer eventId);

}

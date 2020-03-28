package com.elphen.miniapp.api.service;

import com.elphen.miniapp.domain.entity.TEvent;
import com.elphen.miniapp.domain.entity.TUserEvent;

import java.util.List;

public interface TEventService {


    int deleteByPrimaryKey(Integer eventId, Integer fileId, Integer userId);

    int insert(TEvent record);

    int insertSelective(TEvent record);

    TEvent selectByPrimaryKey(Integer eventId, Integer fileId, Integer userId);

    int updateByPrimaryKeySelective(TEvent record);

    int updateByPrimaryKey(TEvent record);

    List<TEvent> selectByUserId(Integer ownerId);

    int deleteByFileId(Integer fileId);

    Integer selectUserIdByEventId(Integer eventId);

    int deleteByEventId(Integer eventId);

    TEvent selectByEventId(Integer eventId);

    TEvent getEventByFileId(Integer fileId);

    List<TEvent> getByUserEvent(List<TUserEvent> userEventList);
}







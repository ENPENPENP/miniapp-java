package com.elphen.miniapp.api.mapper;

import com.elphen.miniapp.domain.entity.TEvent;
import com.elphen.miniapp.domain.entity.TUserEvent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TEventMapper {
    int deleteByPrimaryKey(@Param("eventId") Integer eventId, @Param("fileId") Integer fileId, @Param("userId") Integer userId);

    int insert(TEvent record);

    int insertSelective(TEvent record);

    TEvent selectByPrimaryKey(@Param("eventId") Integer eventId, @Param("fileId") Integer fileId, @Param("userId") Integer userId);

    int updateByPrimaryKeySelective(TEvent record);

    int updateByPrimaryKey(TEvent record);

    List<TEvent> selectByUserId(Integer userId);

    int deleteByFileId(Integer fileId);

    int deleteByEventId(Integer eventId);

    Integer selectUserIdByEventId(Integer eventId);

    TEvent selectByEventId(Integer eventId);

    TEvent selectByFileId(Integer fileId);

    List<TEvent> selectByUserEvent(List<TUserEvent> userEventList);
}
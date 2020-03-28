package com.elphen.miniapp.api.mapper;

import com.elphen.miniapp.domain.entity.TUserEvent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserEventMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUserEvent record);

    int insertSelective(TUserEvent record);

    TUserEvent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUserEvent record);

    int updateByPrimaryKey(TUserEvent record);

    TUserEvent selectByUserIdAndEventId(@Param("userId") Integer userId, @Param("eventId")Integer eventId);

    int deleteByEventId(Integer eventId);

    List<TUserEvent> selectByUserId(Integer userId);

    List<TUserEvent> selectByEventId(Integer eventId);
}
package com.elphen.miniapp.api.mapper;

import com.elphen.miniapp.domain.entity.TUser;
import com.elphen.miniapp.domain.entity.TUserEvent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserMapper {
    int deleteByPrimaryKey(@Param("userId") Integer userId, @Param("openId") String openId);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(@Param("userId") Integer userId, @Param("openId") String openId);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    int deleteByPrimaryKey(Integer userId);

    TUser selectByPrimaryKey(Integer userId);

    TUser selectByOpenId(String openId);

    String selectUserNameByUerId(Integer userId);

    TUser selectByEmail(String email);

    List<TUser> selectByUserEvent(List<TUserEvent> userEventList);
}
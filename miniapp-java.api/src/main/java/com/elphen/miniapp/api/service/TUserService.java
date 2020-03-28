package com.elphen.miniapp.api.service;

import com.elphen.miniapp.domain.entity.TUser;
import com.elphen.miniapp.domain.entity.TUserEvent;

import java.util.List;

public interface TUserService{


    int deleteByPrimaryKey(Integer userId);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    TUser selectByOpenId(String openId);

    String getNameByUerId(Integer userId);

    TUser getByEmail(String email);

    List<TUser> getByUserEvent(List<TUserEvent> userEventList);
}

package com.elphen.miniapp.authserver.service;

import com.elphen.miniapp.domain.entity.TUser;
public interface TUserService{

    int deleteByPrimaryKey(Integer userId);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    TUser selectByUserName(String userName);
}

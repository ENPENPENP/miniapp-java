package com.elphen.miniapp.api.service;

import com.elphen.miniapp.domain.entity.TUserMsg;

public interface TUserMsgService{


    int deleteByPrimaryKey(Integer id, Integer userId, String message);

    int insert(TUserMsg record);

    int insertSelective(TUserMsg record);

    TUserMsg selectByPrimaryKey(Integer id, Integer userId, String message);

    int updateByPrimaryKeySelective(TUserMsg record);

    int updateByPrimaryKey(TUserMsg record);

}

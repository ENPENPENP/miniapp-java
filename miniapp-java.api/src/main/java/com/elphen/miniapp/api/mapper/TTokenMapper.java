package com.elphen.miniapp.api.mapper;

import com.elphen.miniapp.domain.entity.TToken;

public interface TTokenMapper {

    TToken selectByToken(String token);

    int deleteByPrimaryKey(Integer id);

    int insert(TToken record);

    int insertSelective(TToken record);

    TToken selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TToken record);

    int updateByPrimaryKey(TToken record);

}
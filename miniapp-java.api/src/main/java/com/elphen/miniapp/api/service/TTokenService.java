package com.elphen.miniapp.api.service;

import com.elphen.miniapp.common.dto.TokenDto;
import com.elphen.miniapp.domain.entity.TToken;

public interface TTokenService {

    TToken getByToken(String token);

    int deleteByPrimaryKey(Integer id);

    int insert(TToken record);

    int insertSelective(TToken record);

    TToken selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TToken record);

    int updateByPrimaryKey(TToken record);

    TokenDto generateTokenDto(TToken tToken);
}

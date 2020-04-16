package com.elphen.miniapp.api.service.impl;

import com.elphen.miniapp.api.mapper.TTokenMapper;
import com.elphen.miniapp.api.service.TTokenService;
import com.elphen.miniapp.common.dto.TokenDto;
import com.elphen.miniapp.domain.entity.TToken;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class TTokenServiceImpl implements TTokenService {

    @Resource
    private TTokenMapper tTokenMapper;

    @Override
    public TToken getByToken(String token) {
        return tTokenMapper.selectByToken(token);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tTokenMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TToken record) {
        return tTokenMapper.insert(record);
    }

    @Override
    public int insertSelective(TToken record) {
        return tTokenMapper.insertSelective(record);
    }

    @Override
    public TToken selectByPrimaryKey(Integer id) {
        return tTokenMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TToken record) {
        return tTokenMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TToken record) {
        return tTokenMapper.updateByPrimaryKey(record);
    }

    @Override
    public TokenDto generateTokenDto(TToken tToken) {
        TokenDto tokenDto = new TokenDto();
        tokenDto.setToken(tToken.getToken());
        tokenDto.setExpireTime(null);
        tokenDto.setErrMsg("ok");
        tokenDto.setStatus(TokenDto.STATUS_OK);
        return tokenDto;
    }

    @Override
    public boolean checkTokenExpire(TToken token) {
        return token.getExpireTime().after(new Date());
    }
}



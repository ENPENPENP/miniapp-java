package com.elphen.miniapp.api.service.impl;

import com.elphen.miniapp.api.mapper.TUserMsgMapper;
import com.elphen.miniapp.api.service.TUserMsgService;
import com.elphen.miniapp.domain.entity.TUserMsg;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TUserMsgServiceImpl implements TUserMsgService {

    @Resource
    private TUserMsgMapper tUserMsgMapper;

    @Override
    public int deleteByPrimaryKey(Integer id,Integer userId,String message) {
        return tUserMsgMapper.deleteByPrimaryKey(id,userId,message);
    }

    @Override
    public int insert(TUserMsg record) {
        return tUserMsgMapper.insert(record);
    }

    @Override
    public int insertSelective(TUserMsg record) {
        return tUserMsgMapper.insertSelective(record);
    }

    @Override
    public TUserMsg selectByPrimaryKey(Integer id,Integer userId,String message) {
        return tUserMsgMapper.selectByPrimaryKey(id,userId,message);
    }

    @Override
    public int updateByPrimaryKeySelective(TUserMsg record) {
        return tUserMsgMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TUserMsg record) {
        return tUserMsgMapper.updateByPrimaryKey(record);
    }

}

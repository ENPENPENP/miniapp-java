package com.elphen.miniapp.api.service.impl;

import com.elphen.miniapp.api.mapper.TUserMapper;
import com.elphen.miniapp.api.service.TUserService;
import com.elphen.miniapp.domain.entity.TUser;
import com.elphen.miniapp.domain.entity.TUserEvent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TUserServiceImpl implements TUserService {

    @Resource
    private TUserMapper tUserMapper;

    @Override
    public int deleteByPrimaryKey(Integer userId) {
        return tUserMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(TUser record) {
        return tUserMapper.insert(record);
    }

    @Override
    public int insertSelective(TUser record) {
        return tUserMapper.insertSelective(record);
    }

    @Override
    public TUser selectByPrimaryKey(Integer userId) {
        return tUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(TUser record) {
        return tUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TUser record) {
        return tUserMapper.updateByPrimaryKey(record);
    }

    @Override
    public TUser selectByOpenId(String openId) {
        return tUserMapper.selectByOpenId(openId);
    }

    @Override
    public String getNameByUerId(Integer userId) {
        return tUserMapper.selectUserNameByUerId(userId);
    }

    @Override
    public TUser getByEmail(String email) {
        return tUserMapper.selectByEmail(email);
    }

    @Override
    public List<TUser> getByUserEvent(List<TUserEvent> userEventList) {
        return tUserMapper.selectByUserEvent(userEventList);
    }

}


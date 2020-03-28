package com.elphen.miniapp.authserver.service.serviceImpl;

import com.elphen.miniapp.authserver.mapper.TUserMapper;
import com.elphen.miniapp.authserver.service.TUserService;
import com.elphen.miniapp.domain.entity.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TUserServiceImpl implements TUserService {

    @Autowired
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
    public TUser selectByUserName(String userName) {
        return tUserMapper.selectByUserName(userName);
    }

}

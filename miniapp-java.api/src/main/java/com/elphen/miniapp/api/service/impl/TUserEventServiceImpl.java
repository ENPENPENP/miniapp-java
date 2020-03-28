package com.elphen.miniapp.api.service.impl;

import com.elphen.miniapp.api.mapper.TUserEventMapper;
import com.elphen.miniapp.api.service.TUserEventService;
import com.elphen.miniapp.domain.entity.TUserEvent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TUserEventServiceImpl implements TUserEventService{

    @Resource
    private TUserEventMapper tUserEventMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tUserEventMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TUserEvent record) {
        return tUserEventMapper.insert(record);
    }

    @Override
    public int insertSelective(TUserEvent record) {
        return tUserEventMapper.insertSelective(record);
    }

    @Override
    public TUserEvent selectByPrimaryKey(Integer id) {
        return tUserEventMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TUserEvent record) {
        return tUserEventMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TUserEvent record) {
        return tUserEventMapper.updateByPrimaryKey(record);
    }

    @Override
    public TUserEvent getByUserIdAndEventId(Integer userId, Integer eventId) {
        return tUserEventMapper.selectByUserIdAndEventId(userId, eventId);
    }

    @Override
    public int deleteByEventId(Integer eventId) {
        return tUserEventMapper.deleteByEventId(eventId);
    }

    @Override
    public List<TUserEvent> getAllByUserId(Integer userId) {
        return tUserEventMapper.selectByUserId(userId);
    }

    @Override
    public List<TUserEvent> getAllByEventId(Integer eventId) {
        return tUserEventMapper.selectByEventId(eventId);
    }


}

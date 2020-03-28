package com.elphen.miniapp.api.service.impl;

import com.elphen.miniapp.api.mapper.TEventMapper;
import com.elphen.miniapp.api.service.TEventService;
import com.elphen.miniapp.domain.entity.TEvent;
import com.elphen.miniapp.domain.entity.TUserEvent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TEventServiceImpl implements TEventService {

    @Resource
    private TEventMapper tEventMapper;

    @Override
    public int deleteByPrimaryKey(Integer eventId, Integer fileId, Integer userId) {
        return tEventMapper.deleteByPrimaryKey(eventId, fileId, userId);
    }

    @Override
    public int insert(TEvent record) {
        return tEventMapper.insert(record);
    }

    @Override
    public int insertSelective(TEvent record) {
        return tEventMapper.insertSelective(record);
    }

    @Override
    public TEvent selectByPrimaryKey(Integer eventId, Integer fileId, Integer userId) {
        return tEventMapper.selectByPrimaryKey(eventId, fileId, userId);
    }

    @Override
    public int updateByPrimaryKeySelective(TEvent record) {
        return tEventMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TEvent record) {
        return tEventMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<TEvent> selectByUserId(Integer userId) {
        return tEventMapper.selectByUserId(userId);
    }

    @Override
    public int deleteByFileId(Integer fileId) {
        return tEventMapper.deleteByFileId(fileId);
    }

    @Override
    public Integer selectUserIdByEventId(Integer eventId) {
        return tEventMapper.selectUserIdByEventId(eventId);
    }

    @Override
    public int deleteByEventId(Integer eventId) {
        return tEventMapper.selectUserIdByEventId(eventId);
    }

    @Override
    public TEvent selectByEventId(Integer eventId) {
        return tEventMapper.selectByEventId(eventId);
    }

    @Override
    public TEvent getEventByFileId(Integer fileId) {
        return tEventMapper.selectByFileId(fileId);
    }

    @Override
    public List<TEvent> getByUserEvent(List<TUserEvent> userEventList) {
        return tEventMapper.selectByUserEvent(userEventList);
    }

}







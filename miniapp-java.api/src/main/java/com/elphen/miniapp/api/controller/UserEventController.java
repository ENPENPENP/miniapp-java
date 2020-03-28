package com.elphen.miniapp.api.controller;

import com.elphen.miniapp.api.service.TEventService;
import com.elphen.miniapp.api.service.TUserEventService;
import com.elphen.miniapp.api.service.TUserService;
import com.elphen.miniapp.common.dto.UserEventDto;
import com.elphen.miniapp.domain.entity.TEvent;
import com.elphen.miniapp.domain.entity.TUser;
import com.elphen.miniapp.domain.entity.TUserEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 * @ClassName EventUserControllser
 * @Auth Elphen
 * @Description TODO
 **/
@RestController
@RequestMapping("${api.miniapp.prefix}/user_event")
public class UserEventController {


    @Autowired
    private TUserEventService tUserEventService;

    @Autowired
    private TUserService tUserService;

    @Autowired
    private TEventService tEventService;

    @GetMapping("getEvent/{userId}")
    @Transactional(readOnly = true)
    public UserEventDto getUserEventListByUserId(@PathVariable("userId") Integer userId) {
        UserEventDto dto = null;
        if (null != userId) {
            List<TUserEvent> userEventList = tUserEventService.getAllByUserId(userId);
            List<TEvent> linkedEventList = tEventService.getByUserEvent(userEventList);
            dto = UserEventDto.ok(userEventList, linkedEventList, null);
        } else {
            dto = UserEventDto.fail("userId not exist");
        }
        return dto;
    }


    @GetMapping("getUser/{eventId}")
    @Transactional(readOnly = true)
    public UserEventDto getUserEventListByEnentId(@PathVariable("eventId") Integer eventId) {
        UserEventDto dto = null;
        if (null != eventId) {
            List<TUserEvent> userEventList = tUserEventService.getAllByUserId(eventId);
            List<TUser> linkedUserList = tUserService.getByUserEvent(userEventList);
            dto = UserEventDto.ok(userEventList, null, linkedUserList);
        } else {
            dto = UserEventDto.fail("userId not exist");
        }
        return dto;
    }

    @GetMapping("updateRecord")
    @Transactional(rollbackFor = Exception.class)
    public UserEventDto addRecord(Integer userId, Integer eventId, Boolean isEdited) {
        UserEventDto dto = null;
        if (null != userId && null != eventId) {
            try {
                TUserEvent tUserEvent = tUserEventService.getByUserIdAndEventId(userId, eventId);
                if (null == tUserEvent) {
                    tUserEvent = new TUserEvent();
                    tUserEvent.setId(null);
                    tUserEvent.setEventId(eventId);
                    tUserEvent.setUserId(userId);
                    tUserEvent.setIsEdited(isEdited);
                    tUserEventService.insert(tUserEvent);
                } else {
                    tUserEvent.setIsEdited(isEdited);
                    tUserEventService.updateByPrimaryKey(tUserEvent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            dto = UserEventDto.fail("userId or eventId is null");
        }
        return dto;
    }
}

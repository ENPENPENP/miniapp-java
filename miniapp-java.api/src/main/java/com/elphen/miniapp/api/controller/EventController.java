package com.elphen.miniapp.api.controller;

import com.elphen.miniapp.api.service.*;
import com.elphen.miniapp.common.dto.EventDto;
import com.elphen.miniapp.domain.entity.TEvent;
import com.elphen.miniapp.domain.entity.TFileData;
import com.elphen.miniapp.domain.entity.TFileInfo;
import com.elphen.miniapp.domain.entity.TUserEvent;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: miniapp
 * @description: 表格事件控制器
 * @author: Elphen
 * @create: 2019-12-30 11:51
 **/
@RestController
@RequestMapping("${api.miniapp.prefix}/event")
public class EventController {

    private Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private TEventService tEventService;

    @Autowired
    private TUserService tUserService;

    @Autowired
    private TFileInfoService fileInfoService;

    @Autowired
    private TFileDataService fileDataService;

    @Autowired
    private TUserEventService tUserEventService;

    /**
     * 创建相应的事件，文件，文件数据行
     *
     * @param userId      发起创建的用户id
     * @param rowJsonData json格式的行数据
     * @param colCount    数据的行数
     * @param fileName    文件名
     * @param isPrivate   是否为隐私文件
     * @param isLimit     是的有截止时间
     * @param limitTime   截止的时间
     * @return
     */
    @PostMapping("create")
    @Transactional(rollbackFor = Exception.class)
    public EventDto createEvent(Integer userId, String rowJsonData, Integer colCount, String fileName, Boolean isPrivate, Boolean isLimit, String limitTime) {
        EventDto eventDto = null;
        try {
            if (null == userId || StringUtils.isBlank(fileName) || null == colCount || StringUtils.isBlank(rowJsonData) || null == isPrivate || null == isLimit) {
                return EventDto.fail("basic params require not null values");
            }
            if (null == tUserService.selectByPrimaryKey(userId)) {
                return EventDto.fail("target userId is not exist");
            }
            if (isLimit && null == limitTime) {
                return EventDto.fail("require value of the property 'limitTime'");
            }
            TFileInfo newFileInfo = TFileInfo.newFileInfo(fileName, colCount);
//        首先先生成新文件信息对象，插入数据库
            if (fileInfoService.insert(newFileInfo) != 0) {
                logger.info("inset into table t_file_info ->" + newFileInfo.toString());
//                生成新事件对象，插入数据库
                TEvent newEvent = TEvent.newEvent(newFileInfo.getFileId(), userId, isPrivate, newFileInfo.getCreateTime(), newFileInfo.getUpdateTime());
//                如果有截止时间，设置截止时间
                if (isLimit) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                    newEvent.setStopTime(sdf.parse(limitTime));
                }
                if (tEventService.insert(newEvent) != 0) {
                    logger.info("inset into table t_event ->" + newEvent.toString());
//                    事件和文件信息插入成功后，将新文件数据行插入到文件数据表中
                    TFileData columnNameRowData = TFileData.columnNameRow(newEvent.getFileId(), newEvent.getUserId(), rowJsonData);
                    if (fileDataService.insert(columnNameRowData) != 0) {
                        logger.info("inset into table t_file_data ->" + columnNameRowData.toString());
//                      添加用户和事件关联
                        TUserEvent userEvent = new TUserEvent();
                        userEvent.setId(null);
                        userEvent.setEventId(newEvent.getEventId());
                        userEvent.setUserId(newEvent.getUserId());
                        userEvent.setIsEdited(false);
                        tUserEventService.insert(userEvent);
//                        返回文件信息
                        List<TEvent> eventList = new ArrayList<>();
                        newEvent.setFileInfo(newFileInfo);
                        eventList.add(newEvent);
                        eventDto = EventDto.ok(eventList);
                    }
                }
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return eventDto;
    }

    /**
     * 获取指定用户的事件信息
     *
     * @param userId 用户id
     * @return 事件dto
     */
    @GetMapping("getList/{userId}")
    @Transactional(readOnly = true)
    public EventDto getUsersEventList(@PathVariable("userId") Integer userId) {
        EventDto eventDto = null;
        try {
            if (null == userId) {
                eventDto = EventDto.fail("userId is null");
            }
            //                用户存在
            if (tUserService.selectByPrimaryKey(userId) != null) {
                //                查询出相关事件和表格信息
                List<TEvent> events = tEventService.selectByUserId(userId);
                eventDto = EventDto.ok(events);
            } else {
                eventDto = EventDto.fail("userId is not exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventDto;
    }

    /**
     * 删除指定的事件信息
     *
     * @param eventId 目标事件id
     * @return
     */
    @GetMapping("delete/{eventId}")
    @Transactional(rollbackFor = Exception.class)
    public EventDto deleteByFileId(@PathVariable("eventId") Integer eventId) {
        EventDto eventDto = null;
        try {
            if (null == eventId) {
                eventDto = EventDto.fail("fileId not exist");
            } else {
                TEvent event = tEventService.selectByEventId(eventId);
                if (tEventService.deleteByEventId(eventId) != 0
                        && fileInfoService.deleteByFileId(event.getFileId()) != 0
                        && fileDataService.deleteByFileId(event.getFileId()) != 0
                        && tUserEventService.deleteByEventId(event.getEventId()) != 0) {
                    eventDto = EventDto.ok(null);
                } else {
                    eventDto = EventDto.fail("data error");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventDto;
    }

    /**
     * 获取指定事件id的数据
     *
     * @param eventId 事件id
     * @return
     */
    @GetMapping("get/{eventId}")
    @Transactional(readOnly = true)
    public EventDto getSingle(@PathVariable("eventId") Integer eventId) {
        EventDto dto = null;
        try {
            if (null != eventId) {
                TEvent event = tEventService.selectByEventId(eventId);
                if (null != event) {
                    List<TEvent> events = new ArrayList<>();
                    events.add(event);
                    dto = EventDto.ok(events);
                } else {
                    dto = EventDto.fail("eventId not exist: " + eventId);
                }
            } else {
                dto = EventDto.fail("eventId is null");
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return dto;
    }

    /**
     * 更新表格事件为截止状态，同时更新表格信息数据
     * @param eventId
     * @return
     */
    @PostMapping("stop")
    @Transactional(rollbackFor = Exception.class)
    public EventDto setStop(Integer eventId) {
        EventDto dto = null;
        try {
            if (null != eventId) {
                TEvent tEvent = tEventService.selectByEventId(eventId);
                tEvent.setIsStoped(true);
                tEvent.getFileInfo().setIsStoped(true);
                tEventService.updateByPrimaryKey(tEvent);
                fileInfoService.updateByPrimaryKey(tEvent.getFileInfo());
                List<TEvent> events = new ArrayList<>();
                events.add(tEvent);
                dto = EventDto.ok(events);
            } else {
                dto = EventDto.fail("eventId is null");
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return dto;
    }

    @PostMapping("switchPrivate")
    @Transactional(rollbackFor = Exception.class)
    public EventDto switchPrivate(Integer eventId){
        EventDto dto = null;
        try {
            if (null != eventId) {
                TEvent tEvent = tEventService.selectByEventId(eventId);
                tEvent.setIsPrivate(!tEvent.getIsPrivate());
                tEventService.updateByPrimaryKey(tEvent);
                List<TEvent> events = new ArrayList<>();
                events.add(tEvent);
                dto = EventDto.ok(events);
            } else {
                dto = EventDto.fail("eventId is null");
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return dto;
    }
}

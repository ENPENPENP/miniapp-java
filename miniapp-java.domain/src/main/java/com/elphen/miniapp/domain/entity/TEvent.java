package com.elphen.miniapp.domain.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TEvent implements Serializable {
    /**
     * 事件的唯一标识，自增ID
     */
    private Integer eventId;

    /**
     * 事件对应的（excel表格）文件ID，不为空
     */
    private Integer fileId;

    /**
     * 事件发起用户的ID，不为空
     */
    private Integer userId;

    /**
     * 是否为私有事件，1 为 true ,0 为 false (默认值)，不为空
     */
    private Boolean isPrivate;

    /**
     * 事件是否已经结束，1 为 true ,0 为 false (默认值)，不为空
     */
    private Boolean isStoped;

    /**
     * 事件创建时间，不为空
     */
    private Date createTime;

    /**
     * 事件更新时间，不为空
     */
    private Date updateTime;

    /**
     * 事件截止事件，可以为空
     */
    private Date stopTime;

    private TFileInfo fileInfo;

    private static final long serialVersionUID = 1L;

    public static TEvent newEvent(Integer fileId, Integer userId, boolean isPrivate, Date createTime, Date updateTime, Date stopTime) {
        TEvent event = new TEvent();
        event.setEventId(null);
        event.setFileId(fileId);
        event.setUserId(userId);
        event.setIsPrivate(isPrivate);
        event.setIsStoped(false);
        event.setCreateTime(createTime);
        event.setUpdateTime(updateTime);
        event.setStopTime(stopTime);
        return event;
    }

    public static TEvent newEvent(Integer fileId, Integer userId, boolean isPrivate, Date createTime, Date updateTime) {
        TEvent event = new TEvent();
        event.setEventId(null);
        event.setFileId(fileId);
        event.setUserId(userId);
        event.setIsPrivate(isPrivate);
        event.setIsStoped(false);
        event.setCreateTime(createTime);
        event.setUpdateTime(updateTime);
        event.setStopTime(null);
        return event;
    }
}
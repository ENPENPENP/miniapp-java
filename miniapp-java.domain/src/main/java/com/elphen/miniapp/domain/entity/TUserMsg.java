package com.elphen.miniapp.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TUserMsg implements Serializable {
    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 消息发送的目标用户ID，不为空
     */
    private Integer userId;

    /**
     * 发送的消息内容，不为空
     */
    private String message;

    /**
     * 消息类型，1 事件未完成，2 为某事件已结束，3 为某事件发起人催促完成事件
     */
    private Integer messagType;

    /**
     * 消息附属的事件ID
     */
    private Integer eventId;

    /**
     * 消息附属的群组ID
     */
    private Integer groupId;

    /**
     * 消息是否已读，1 为已读，0 为未读（默认值）
     */
    private Boolean isReaded;

    /**
     * 消息创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}
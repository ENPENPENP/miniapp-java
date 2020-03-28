package com.elphen.miniapp.domain.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TGroup implements Serializable {
    /**
     * 微信群组opengid
     */
    private String groupId;

    /**
     * 是否为私有群组，1 为私有，0 为开放，不为空
     */
    private Boolean isPrivate;

    /**
     * 生成该条记录的时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}
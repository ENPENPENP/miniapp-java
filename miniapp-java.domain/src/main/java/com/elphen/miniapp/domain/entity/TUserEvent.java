package com.elphen.miniapp.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TUserEvent implements Serializable {
    /**
    * 自增id
    */
    private Integer id;

    /**
    * 事件id
    */
    private Integer eventId;

    /**
    * 用户id
    */
    private Integer userId;

    /**
    * 是否已经编辑过对应事件的表格
    */
    private Boolean isEdited;

    private static final long serialVersionUID = 1L;
}
package com.elphen.miniapp.domain.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TUserGroup implements Serializable {
    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 用户ID，不为空
     */
    private Integer userId;

    /**
     * 群组ID，不为空
     */
    private String groupId;

    private static final long serialVersionUID = 1L;
}
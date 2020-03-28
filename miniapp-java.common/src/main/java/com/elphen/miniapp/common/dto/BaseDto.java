package com.elphen.miniapp.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: miniapp
 * @description: DTO父类
 * @author: Elphen
 * @create: 2019-12-31 18:05
 **/
@Data
public class BaseDto implements Serializable {

    public static Integer STATUS_OK = 1;
    public static Integer STATUS_FAIL = -1;

    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * 状态码
     */
    private Integer status;

}

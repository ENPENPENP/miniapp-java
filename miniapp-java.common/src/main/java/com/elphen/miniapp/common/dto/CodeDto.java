package com.elphen.miniapp.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回
 */
@Data
public class CodeDto implements Serializable {
    /**
     * 状态码成功
     */
    public static final Integer LOGIN_OK = 1;
    /**
     * 状态码失败
     */
    public static final Integer LOGIN_FAIL = -1;

    /**
     * 用户信息集合
     */
    private Map userInfo;

    /**
     * 错误信息
     */
    private Map<String,String> error;

    /**
     * 状态吗
     */
    private Integer status;

    /**
     * 获取登录信息成功
     * @param userInfo
     * @return
     */
    public static CodeDto ok(Map userInfo){
        CodeDto codeDto = new CodeDto();
        codeDto.setUserInfo(userInfo);
        codeDto.setError(null);
        codeDto.setStatus(LOGIN_OK);
        return codeDto;
    }

    /**
     * 获取登录信息成功
     * @param openId
     * @param sessionKey
     * @return
     */
    public static CodeDto ok(String openId, String sessionKey){
        CodeDto codeDto = new CodeDto();
        HashMap<String,String> userInfo = new HashMap();
        userInfo.put("openId",openId);
        userInfo.put("sessionKey",sessionKey);
        codeDto.setUserInfo(userInfo);
        codeDto.setError(null);
        codeDto.setStatus(LOGIN_OK);
        return codeDto;
    }

    /**
     * 获取登录信息失败
     * @param error
     * @return
     */
    public static CodeDto fail(Map error){
        CodeDto codeDto = new CodeDto();
        codeDto.setUserInfo(null);
        codeDto.setError(error);
        codeDto.setStatus(LOGIN_FAIL);
        return codeDto;
    }

    /**
     * 获取登录信息失败
     * @param errorcode 错误码
     * @param errormsg 错误信息
     * @return
     */
    public static CodeDto fail(String errorcode, String errormsg){
        CodeDto codeDto = new CodeDto();
        codeDto.setUserInfo(null);
        HashMap<String,String> error = new HashMap();
        error.put("errorcode",errorcode);
        error.put("errormsg",errormsg);
        codeDto.setError(error);
        codeDto.setStatus(LOGIN_FAIL);
        return codeDto;
    }

}

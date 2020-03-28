package com.elphen.miniapp.common.dto;

import lombok.Data;

import java.util.Date;

/**
 * @program: miniapp
 * @description: 返回自定义session给小程序前端
 * @author: Elphen
 * @create: 2019-12-31 17:31
 **/
@Data
public class TokenDto extends BaseDto {

    /**
     * 3rd_session
     */
    private String token;

    /**
     * 有效时间(秒)
     */
    private Date expireTime;

    private static final long serialVersionUID = 1L;

    public static TokenDto newToken(String token, Date expireTime) {
        TokenDto dto = new TokenDto();
        dto.setToken(token);
        dto.setExpireTime(expireTime);
        dto.setErrMsg("ok");
        dto.setStatus(STATUS_OK);
        return dto;
    }

    public static TokenDto fail(String errMsg) {
        TokenDto dto = new TokenDto();
        dto.setToken(null);
        dto.setExpireTime(null);
        dto.setErrMsg(errMsg);
        dto.setStatus(STATUS_FAIL);
        return dto;
    }
}

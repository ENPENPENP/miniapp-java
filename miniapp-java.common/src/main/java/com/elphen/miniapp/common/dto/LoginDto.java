package com.elphen.miniapp.common.dto;

import com.elphen.miniapp.domain.entity.TUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/*
 * @ClassName LoginDto
 * @Auth Elphen
 * @Description TODO
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto extends BaseDto {

    private TUser userInfo;

    private String token;

    private Date expireTime;

    private static final long serialVersionUID = 1L;

    public static LoginDto ok(TUser userInfo,String token,Date expireTime) {
        LoginDto loginDto = new LoginDto();
        loginDto.setUserInfo(userInfo);
        loginDto.setToken(token);
        loginDto.setExpireTime(expireTime);
        loginDto.setStatus(STATUS_OK);
        loginDto.setErrMsg("ok");
        return loginDto;
    }

    public static LoginDto fail(String errMsg) {
        LoginDto loginDto = new LoginDto();
        loginDto.setUserInfo(null);
        loginDto.setToken(null);
        loginDto.setExpireTime(null);
        loginDto.setStatus(STATUS_FAIL);
        loginDto.setErrMsg(errMsg);
        return loginDto;
    }
}

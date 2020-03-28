package com.elphen.miniapp.common.dto;

import lombok.Data;

/*
 * @ClassName UserDto
 * @Auth Elphen
 * @Description TODO
 **/
@Data
public class UserDto extends BaseDto {
    private String nickName;
    private Integer userId;

    public static UserDto ok(String nickName, Integer userId) {
        UserDto dto = new UserDto();
        dto.setNickName(nickName);
        dto.setUserId(userId);
        dto.setErrMsg("ok");
        dto.setStatus(STATUS_OK);
        return dto;
    }

    public static UserDto fail(String errMsg) {
        UserDto dto = new UserDto();
        dto.setNickName(null);
        dto.setUserId(null);
        dto.setErrMsg(errMsg);
        dto.setStatus(STATUS_FAIL);
        return dto;
    }
}

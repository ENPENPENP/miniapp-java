package com.elphen.miniapp.common.dto;

import com.elphen.miniapp.domain.entity.TEvent;
import com.elphen.miniapp.domain.entity.TUser;
import com.elphen.miniapp.domain.entity.TUserEvent;
import lombok.Data;

import java.util.List;

/*
 * @ClassName UserEventDto
 * @Auth Elphen
 * @Description TODO
 **/
@Data
public class UserEventDto extends BaseDto {

    private List<TUserEvent> userEventList;

    private List<TEvent> linkedEventList;

    private List<TUser> linkedUserList;

    public static UserEventDto ok(List<TUserEvent> userEventList,List<TEvent> linkedEventList,List<TUser> linkedUserList) {
        UserEventDto dto = new UserEventDto();
        dto.setUserEventList(userEventList);
        dto.setLinkedEventList(linkedEventList);
        dto.setLinkedUserList(linkedUserList);
        dto.setStatus(STATUS_OK);
        dto.setErrMsg("ok");
        return dto;
    }

    public static UserEventDto fail(String errMsg) {
        UserEventDto dto = new UserEventDto();
        dto.setUserEventList(null);
        dto.setLinkedUserList(null);
        dto.setUserEventList(null);
        dto.setStatus(STATUS_FAIL);
        dto.setErrMsg(errMsg);
        return dto;
    }
}

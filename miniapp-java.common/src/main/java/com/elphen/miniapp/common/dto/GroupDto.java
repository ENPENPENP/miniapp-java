package com.elphen.miniapp.common.dto;

import com.elphen.miniapp.domain.entity.TEventGroup;
import com.elphen.miniapp.domain.entity.TUserEvent;
import com.elphen.miniapp.domain.entity.TUserGroup;
import lombok.Data;

import java.util.List;

/*
 * @ClassName GroupDto
 * @Auth Elphen
 * @Description TODO
 **/
@Data
public class GroupDto extends BaseDto {

    private List<TUserEvent> userEventList;
    private List<TEventGroup> eventGroupList;
    private List<TUserGroup> userGroupList;

    public static GroupDto ok(List<TUserGroup> userGroupList, List<TUserEvent> userEventList, List<TEventGroup> eventGroupList) {
        GroupDto dto = new GroupDto();
        dto.setUserEventList(userEventList);
        dto.setEventGroupList(eventGroupList);
        dto.setUserGroupList(userGroupList);
        dto.setErrMsg("ok");
        dto.setStatus(STATUS_OK);
        return dto;
    }


    public static GroupDto fail(String errMsg) {
        GroupDto dto = new GroupDto();
        dto.setUserEventList(null);
        dto.setEventGroupList(null);
        dto.setUserGroupList(null);
        dto.setErrMsg(errMsg);
        dto.setStatus(STATUS_FAIL);
        return dto;
    }

}

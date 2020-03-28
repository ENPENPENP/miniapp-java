package com.elphen.miniapp.common.dto;

import com.elphen.miniapp.domain.entity.TEvent;
import com.elphen.miniapp.domain.entity.TFileData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: miniapp
 * @description: 表格事件传输层
 * @author: Elphen
 * @create: 2019-12-30 11:55
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto extends BaseDto {

    private List<TEvent> eventList;

    private List<TFileData> fileDataList;

    private static final long serialVersionUID = 1L;

    public static EventDto ok(List<TEvent> eventList) {
        EventDto eventDto = new EventDto();
        eventDto.setEventList(eventList);
        eventDto.setFileDataList(null);
        eventDto.setErrMsg("ok");
        eventDto.setStatus(STATUS_OK);
        return eventDto;
    }

    public static EventDto ok(List<TEvent> eventList,List<TFileData> fileDataList) {
        EventDto eventDto = new EventDto();
        eventDto.setEventList(eventList);
        eventDto.setFileDataList(fileDataList);
        eventDto.setErrMsg("ok");
        eventDto.setStatus(STATUS_OK);
        return eventDto;
    }

    public static EventDto fail(String errMsg) {
        EventDto eventDto = new EventDto();
        eventDto.setEventList(null);
        eventDto.setFileDataList(null);
        eventDto.setErrMsg(errMsg);
        eventDto.setStatus(STATUS_FAIL);
        return eventDto;
    }
}

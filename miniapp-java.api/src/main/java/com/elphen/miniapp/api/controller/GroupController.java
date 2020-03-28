package com.elphen.miniapp.api.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.util.crypt.WxMaCryptUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.elphen.miniapp.api.service.TEventGroupService;
import com.elphen.miniapp.api.service.TGroupService;
import com.elphen.miniapp.api.service.TUserGroupService;
import com.elphen.miniapp.common.dto.GroupDto;
import com.elphen.miniapp.domain.entity.TEventGroup;
import com.elphen.miniapp.domain.entity.TGroup;
import com.elphen.miniapp.domain.entity.TUserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/*
 * @ClassName GroupController
 * @Auth Elphen
 * @Description TODO
 **
 */
@RestController
@RequestMapping("${api.miniapp.prefix}/group")
public class GroupController {

    @Autowired
    private TGroupService tGroupService;

    @Autowired
    private TEventGroupService tEventGroupService;

    @Autowired
    private TUserGroupService tUserGroupService;

    @Autowired
    private WxMaService wxMaService;

    @PostMapping("addEventGroup")
    @Transactional(rollbackFor = Exception.class)
    public GroupDto addUserEventGroupRecords(Integer userId, Integer eventId, String jscode, String encryptedData, String iv) {
        GroupDto dto = null;
        try {
            WxMaJscode2SessionResult sessionInfo = wxMaService.getUserService().getSessionInfo(jscode);
//            获取sessionKey
            String sessionKey = sessionInfo.getSessionKey();
            WxMaCryptUtils wxMaCryptUtils = new WxMaCryptUtils(wxMaService.getWxMaConfig());
//            获取json格式的解密数据
            String groupData = wxMaCryptUtils.decrypt(sessionKey, encryptedData, iv);
//            获取群组唯一表示openGId
            JSONObject jsonObject = JSON.parseObject(groupData);
            Object openGId = jsonObject.get("openGId");
            TGroup group = tGroupService.selectByPrimaryKey(openGId.toString());
            if (null == group) {
//            生成新的group对象
                group = new TGroup();
                group.setGroupId(openGId.toString());
                group.setIsPrivate(false);
                group.setCreateTime(new Date());
                tGroupService.insert(group);
            }
            TEventGroup eventGroup = tEventGroupService.getByEventIdAndGroupId(eventId, openGId.toString());
            if (eventGroup == null) {
//            生成新的eventGroup对象
                eventGroup = new TEventGroup();
                eventGroup.setId(null);
                eventGroup.setEventId(eventId);
                eventGroup.setGroupId(openGId.toString());
                eventGroup.setIsAllUser(false);
                eventGroup.setIsPrivate(false);
                tEventGroupService.insert(eventGroup);
            }
            TUserGroup userGroup = tUserGroupService.getByUserIdAndGroupId(userId, openGId.toString());
            if (null == userGroup) {
//            生成userGroup对象
                userGroup = new TUserGroup();
                userGroup.setId(null);
                userGroup.setUserId(userId);
                userGroup.setGroupId(openGId.toString());
                tUserGroupService.insert(userGroup);
            }
            dto = GroupDto.ok(null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
}

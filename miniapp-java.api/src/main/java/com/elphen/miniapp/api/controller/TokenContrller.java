package com.elphen.miniapp.api.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.elphen.miniapp.api.service.TTokenService;
import com.elphen.miniapp.common.dto.TokenDto;
import com.elphen.miniapp.domain.entity.TToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @program: miniapp
 * @description: Token控制类
 * @author: Elphen
 * @create: 2020-01-03 18:12
 **/
@RestController
@RequestMapping("${api.miniapp.prefix}/token")
public class TokenContrller {

    @Autowired
    private WxMaService wxMaService;

    @Autowired
    private TTokenService tTokenService;

    private Logger logger = LoggerFactory.getLogger(TokenContrller.class);

    @GetMapping("update/{userId}")
    @Transactional(rollbackFor = Exception.class)
    public TokenDto getAssesToken(@PathVariable("userId") Integer userId, String jscode) {
        TokenDto dto = null;
        try {
            Date date = new Date();
            WxMaJscode2SessionResult sessionInfo = wxMaService.getUserService().getSessionInfo(jscode);
            String openId = sessionInfo.getOpenid();
            TToken tToken = new TToken(sessionInfo.getSessionKey(), openId, date, new Date(date.getTime() + 7200000L));
            if (tTokenService.insert(tToken) != 0) {
                dto = TokenDto.newToken(tToken.getToken(), tToken.getExpireTime());
            } else {
                dto = TokenDto.fail("get token fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
}

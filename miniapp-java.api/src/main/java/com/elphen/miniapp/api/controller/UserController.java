package com.elphen.miniapp.api.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.elphen.miniapp.api.service.TTokenService;
import com.elphen.miniapp.api.service.TUserService;
import com.elphen.miniapp.common.dto.LoginDto;
import com.elphen.miniapp.common.dto.UserDto;
import com.elphen.miniapp.domain.entity.TToken;
import com.elphen.miniapp.domain.entity.TUser;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


/**
 * @Description:
 * @Author: Elphen
 * @Date: 2019\12\30 0030
 */
@RestController
@RequestMapping("${api.miniapp.prefix}/user")
public class UserController {

    @Autowired
    private WxMaService wxMaService;

    @Autowired
    private TUserService tUserService;

    @Autowired
    private TTokenService tTokenService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 使用jscode解密数据，获取用户信息
     *
     * @param jscode   前端获取的code
     * @param nickName 前端获取的微信昵称
     * @return
     */
    @GetMapping(value = "weChatSignIn")
    @Transactional(rollbackFor = Exception.class)
    public LoginDto login(String jscode, String nickName) {
        LoginDto loginDto = null;
        if (StringUtils.isNotBlank(jscode)) {
            try {
                WxMaJscode2SessionResult sessionInfo = wxMaService.getUserService().getSessionInfo(jscode);
                String openId = sessionInfo.getOpenid();
                TToken tToken = null;
                //如果在数据库查询到对应的opneid记录
                if (tUserService.selectByOpenId(openId) == null) {
                    TUser newUser = new TUser();
                    newUser.setUserId(null);
                    newUser.setOpenId(openId);
                    newUser.setEmail(null);
                    newUser.setPassword(null);
                    newUser.setNickName(nickName);
                    newUser.setPhone(null);
                    if (tUserService.insert(newUser) == 0) {
                        loginDto = LoginDto.fail("database error");
                    }
                }
                TUser tUser = tUserService.selectByOpenId(openId);
                if (tUserService.selectByOpenId(openId) != null) {
                    Date date = new Date();
                    SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    isoFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                    tToken = new TToken(sessionInfo.getSessionKey(), openId, date, new Date(date.getTime() + 7200000L));
                    //插入数据库
                    if (tTokenService.insert(tToken) != 0) {
                        // 生成loginDto对象
                        loginDto = LoginDto.ok(tUser, tToken.getToken(),tToken.getExpireTime());
                    } else {
                        loginDto = LoginDto.fail("can not get a token");
                    }
                }
            } catch (WxErrorException we) {
                logger.info(we.getError().getErrorMsg());
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        } else {
            loginDto = LoginDto.fail("bad request");
        }
        return loginDto;
    }

//    @GetMapping("emailSignIn")
//    public LoginDto emailSignIn(String email, String password){
//        LoginDto loginDto = null;
//        if (StringUtils.isNotBlank(email ) && StringUtils.isNotBlank(password)) {
////            首先检查是否存在该账号
//            TUser user = tUserService.getByEmail(email);
//            if(null != user){
////                判断是否存在openId
//                if(null == user.getOpenId()){
//                    loginDto = new LoginDto();
//                    loginDto.setExpireTime(null);
//                    loginDto.setUserInfo(null);
//                    loginDto.setToken(null);
//                    loginDto.setStatus(-1);
//                    loginDto.setErrMsg("openId not exist");
//                }
//                else {
//                    if(user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
//
//                    }
//                }
//            }
//        }else {
//            loginDto = LoginDto.fail("email or password is null");
//        }
//
//
//        return loginDto;
//    }


    /**
     * 获取用户的用户名
     *
     * @param userId 用户id
     * @return
     */
    @GetMapping("getName/{userId}")
    @Transactional(readOnly = true)
    public UserDto getName(@PathVariable("userId") Integer userId) {
        UserDto dto = null;
        try {
            if (null != userId) {
                String name = tUserService.getNameByUerId(userId);
                dto = UserDto.ok(name, userId);
            } else {
                dto = UserDto.fail("userId not exist : " + userId);
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return dto;
    }
}


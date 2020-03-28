package com.elphen.miniapp.authserver.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*
 * @ClassName WxController
 * @Auth Elphen
 * @Description TODO
 **/
@Controller
@RequestMapping("authserver")
public class WxController {

    @Autowired
    private WxMaService wxMaService;

    /**
     * 使用前端获取的code获取openId和sessionKey
     * @param code
     * @return
     */
    @GetMapping("get_token")
    public String getToken(String code, RedirectAttributes redirectAttributes){
        try {
            WxMaJscode2SessionResult sessionInfo = wxMaService.getUserService().getSessionInfo(code);
            String openid = sessionInfo.getOpenid();
            String sessionKey = sessionInfo.getSessionKey();
            String tokenUrl = "";
            //重定向到token请求接口
            redirectAttributes
                    .addFlashAttribute("grant_type","")
                    .addFlashAttribute("client_id","")
                    .addFlashAttribute("secret","")
                    .addFlashAttribute("username","")
                    .addFlashAttribute("password","");
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return "redirect:/auth/token";
    }

}

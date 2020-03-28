package com.elphen.miniapp.authserver.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Elphen
 */
@Configuration
@EnableConfigurationProperties
public class WeChatConfiguration {

    @Autowired
    private WeChatProperties properties;

    /**
    * @Description:  检查properties
    * @Param: []
    * @return: void
    * @Author: Elphen
    * @Date: 2019\12\27 0027 17:10
    */
    public void checkProperties(){
        if (StringUtils.isBlank(this.properties.getAppid())) {
            throw new RuntimeException("配置错误：appid错误");
        }
        if(StringUtils.isBlank(this.properties.getSecret())){
            throw new RuntimeException("配置错误：secret错误");
        }
    }

    /**
    * @Description: 配置WxMaService bean
    * @Param: []
    * @return: cn.binarywang.wx.miniapp.api.WxMaService
    * @Author: Elphen
    * @Date: 2019\12\27
    */
    @Bean
    @ConditionalOnMissingBean(WxMaService.class)
    public WxMaService service() {
        checkProperties();
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        config.setAppid(StringUtils.trimToNull(this.properties.getAppid()));
        config.setSecret(StringUtils.trimToNull(this.properties.getSecret()));
        config.setToken(StringUtils.trimToNull(this.properties.getToken()));
        config.setAesKey(StringUtils.trimToNull(this.properties.getAesKey()));
        config.setMsgDataFormat(StringUtils.trimToNull(this.properties.getMsgDataFormat()));

        final WxMaServiceImpl service = new WxMaServiceImpl();
        service.setWxMaConfig(config);
        return service;
    }
}
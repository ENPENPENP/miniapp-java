package com.elphen.miniapp.authserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author Elphen
 */
@Data
@Component
@ConfigurationProperties(prefix = "wx")
public class WeChatProperties {

    /**
     * 设置微信小程序的appid
     */
    @NotNull
    private String appid;

    /**
     * 设置微信小程序的Secret
     */
    @NotNull
    private String secret;

    /**
     * 设置微信小程序消息服务器配置的token
     */
    private String token;

    /**
     * 设置微信小程序消息服务器配置的EncodingAESKey
     */
    private String aesKey;

    /**
     * 消息格式，XML或者JSON
     */
    private String msgDataFormat;

}

package com.elphen.miniapp.domain.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TUser implements Serializable {
    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * OPENID
     */
    @JsonIgnore
    private String openId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码，第一次创建账号使用微信授权，用户可在设置中修改
     */
    @JsonIgnore
    private String password;

    /**
     * 用户昵称，默认值是微信昵称
     */
    private String nickName;

    /**
     * 手机号码
     */
    @JsonIgnore
    private String phone;

    private static final long serialVersionUID = 1L;
}
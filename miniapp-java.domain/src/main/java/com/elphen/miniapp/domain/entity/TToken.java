package com.elphen.miniapp.domain.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.DigestUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TToken implements Serializable {

    private Integer id;

    private String token;

    private String sessionKey;

    private String openId;

    private Date createTime;

    private Date expireTime;

    private static final long serialVersionUID = 1L;

    public TToken(String sessionKey, String openId, Date createTime, Date expireTime){
        StringBuffer tokenStr = new StringBuffer(openId).append(":").append(sessionKey);
        this.openId = openId;
        this.sessionKey = sessionKey;
//        使用MD5加密
        this.token = DigestUtils.md5DigestAsHex(tokenStr.toString().getBytes());
        this.createTime = createTime;
        this.id = null;
        this.expireTime = expireTime;
    }
}
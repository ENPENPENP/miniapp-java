package com.elphen.miniapp.api.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.elphen.miniapp.common.dto.CodeDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: miniapp
 * @description:
 * @author: Elphen
 * @create: 2019-12-30 16:31
 **/
@SpringBootTest
public class JsonTest {

    @Test
    public void jsonTest(){
        Map<String, String> data = new HashMap<>();
        data.put("openid","dfsfdsthfghfghf");
        data.put("session_key","fdhskjfhkdjshfjkdhfkjd");
        CodeDto codeDto = CodeDto.ok(data);
        System.out.println(JSON.toJSON(codeDto));

    }

    @Test
    public void jsonobject() {
        Map<String, String> data = new HashMap<>();
        data.put("openid", "dfsfdsthfghfghf");
        data.put("session_key", "fdhskjfhkdjshfjkdhfkjd");
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("data", JSON.toJSON(data));
        jsonObject.put("status", 1);
        System.out.println(jsonObject.toJSONString());
    }
}

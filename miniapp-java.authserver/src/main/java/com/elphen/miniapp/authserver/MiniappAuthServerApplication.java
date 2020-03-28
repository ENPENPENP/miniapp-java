package com.elphen.miniapp.authserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * @ClassName MiniappAuthServerApplication
 * @Auth Elphen
 * @Description 认证服务启动类
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.elphen.miniapp.authserver.mapper")
public class MiniappAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniappAuthServerApplication.class, args);
    }


}

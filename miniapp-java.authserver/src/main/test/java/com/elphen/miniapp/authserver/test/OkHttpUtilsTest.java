package com.elphen.miniapp.authserver.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class OkHttpUtilsTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123456"));
    }
}

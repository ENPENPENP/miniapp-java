package com.elphen.miniapp.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @ClassName MyController
 * @Auth Elphen
 * @Description TODO
 **/
@RestController
public class MyController {

    @GetMapping(value = "/")
    public String myController(){
        return "Spring boot 第二个页面";

    }
}

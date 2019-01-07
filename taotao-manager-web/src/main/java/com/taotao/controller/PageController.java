package com.taotao.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
/**
 * 页面展示Controller
 */
public class PageController {

    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }
}

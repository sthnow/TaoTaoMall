package com.taotao.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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


    //将传递过来的请求 url 返回，通过SpringMVC找到对应的页面
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }

}

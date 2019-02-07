package com.taotao.item.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Controller
/**
 * 网页静态化处理controller
 */
public class HtmlGenController {
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @RequestMapping("genHtml")
    @ResponseBody
    public String getHtml() throws IOException, TemplateException {
        //生成静态页面
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        Template template = configuration.getTemplate("hello.ftl");
        //创建一个数据集
        Map data = new HashMap();
        data.put("hello", "hello freemarker Sprin");
        Writer out = new FileWriter("C:\\Users\\wangz\\IdeaProjects\\cache\\html\\out\\text.html");
        template.process(data,out);
        out.close();
        //返回结果
        return "ok";
    }
}

package com.taotao.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class TestFreeMarker {
    @Test
    public void testFreeMarker() throws Exception {
        //1.创建一个模板文件
        //2.创建一个configuration对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        //3.设置模板所在的路径
        configuration.setDirectoryForTemplateLoading(new File("C:\\Users\\wangz\\IdeaProjects\\taotaoparent\\taotao-item-web\\src\\main\\webapp\\WEB-INF\\jsp\\ftl"));
        //4.设置模板的字符集，一般是utf-8
        configuration.setDefaultEncoding("utf-8");
        //5.使用configuration对象加载一个模板文件，需要指定模板文件的文件名
        Template template = configuration.getTemplate("hello.ftl");
        //6.创建一个数据集，可以是pojo，也可以是map（推荐使用）
        Map data = new HashMap();
        data.put("hello", "hello freemarker");
        //7.创建一个writer对象，指定输出的路径及文件名
        Writer out = new FileWriter(new File("C:\\Users\\wangz\\IdeaProjects\\cache\\html\\out\\hello.txt"));
        //8.使用模板对象的process方法输出文件
        template.process(data, out);
        //9.关闭流
        out.close();
    }
}


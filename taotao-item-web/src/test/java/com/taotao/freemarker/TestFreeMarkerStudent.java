package com.taotao.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestFreeMarkerStudent {
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
        Template template = configuration.getTemplate("student.ftl");
        //6.创建一个数据集，可以是pojo，也可以是map（推荐使用）
        Map data = new HashMap();
        Student student = new Student(1,"小名",23,"北京");
        data.put("student", student);
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1,"小名1",13,"北京"));
        studentList.add(new Student(2,"小名2",23,"北京"));
        studentList.add(new Student(3,"小名3",33,"北京"));
        studentList.add(new Student(4,"小名4",43,"北京"));
        studentList.add(new Student(5,"小名5",53,"北京"));
        studentList.add(new Student(6,"小名6",63,"北京"));
        studentList.add(new Student(7,"小名7",73,"北京"));
        data.put("studentList", studentList);
        //7.创建一个writer对象，指定输出的路径及文件名
        Writer out = new FileWriter(new File("C:\\Users\\wangz\\IdeaProjects\\cache\\html\\out\\student.html"));
        //8.使用模板对象的process方法输出文件
        template.process(data, out);
        //9.关闭流
        out.close();
    }
}


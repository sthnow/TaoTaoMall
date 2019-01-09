package com.taotao.pagehelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


public class TestHelper {

    @Test
    public void testPageHelper() throws Exception{
        //1.先在mybatis的配置文件中配置分页插件
        //2.在执行查询之前配置分页条件，使用pageHeler的静态方法
        PageHelper.startPage(1,10);
        //3.执行查询
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:Spring/applicationContext-dao.xml");
        //找到这个借口的代理对象
        TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
        //创建example对象
        TbItemExample example = new TbItemExample();
//        //设置查询条件
//        TbItemExample.Criteria criteria = example.createCriteria();

        //现在没有查询条件，就是查询所有
        List<TbItem> list = itemMapper.selectByExample(example);
        //4.取分页信息，使用pageInfo对象取
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        //查询总记录数
        System.out.println("总记录数" + pageInfo.getTotal());
        //查询总页数
        System.out.println("总记页数" + pageInfo.getPages());
        //返回的总记录数
        System.out.println("返回的记录数" + list.size());

    }
}

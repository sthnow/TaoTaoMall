package com.taotao.jedis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestJedisSpring {

    @Test
    public void testJedisClientPool() throws Exception{
        //初始化Spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:Spring/applicationContext-redis.xml");
        //从容器中获得jedisClient对象
        JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
        //使用jedisClient对象操作redis
        jedisClient.set("jedisClient", "mytest");
        String result = jedisClient.get("jedisClient");
        System.out.println(result);
    }


}

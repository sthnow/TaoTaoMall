package com.taotao.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestJedis {


    @Test
    public void testJedis() throws Exception{
        //创建一个jedis对象，需要制定服务的ip和端口号
        Jedis jedis = new Jedis("192.168.25.88", 6379);
        //直接操作数据库
        jedis.set("jedis-key", "1234");
        String result = jedis.get("jedis-key");
        System.out.println(result);
        //关闭jedis
        jedis.close();
    }

    @Test
    public void testJedisPoll() throws Exception{
        //创建一个jedis连接池对象（单例），需要制定服务的ip和端口号
        JedisPool jedisPool = new JedisPool("192.168.25.88", 6379);
        //从连接池中获得连接
        Jedis jedis = jedisPool.getResource();
        //使用jedis操作数据库(方法级别使用)
        String result = jedis.get("jedis-key");
        System.out.println(result);
        //一定要关闭连接
        jedis.close();
        //系统关闭前，关闭连接池
        jedisPool.close();
    }
}

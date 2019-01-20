package com.taotao.jedis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

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

//    //测试jedis集群
//    @Test
//    public void testJedisCluster() throws Exception{
//        //创建一个jedisCluster对象，构造参数set类型，集合中每个元素是HostAndPort类型
//        Set<HostAndPort> nodes = new HashSet<>();
//        //向集合中添加节点
//        nodes.add(new HostAndPort("192.168.25.88", 7001));
//        nodes.add(new HostAndPort("192.168.25.88", 7002));
//        nodes.add(new HostAndPort("192.168.25.88", 7003));
//        nodes.add(new HostAndPort("192.168.25.88", 7004));
//        nodes.add(new HostAndPort("192.168.25.88", 7005));
//        nodes.add(new HostAndPort("192.168.25.88", 7006));
//        JedisCluster jedisCluster = new JedisCluster(nodes);
//        //直接使用jedisCluster操作redis，自带连接池。jedis对象可以是单例的。
//        jedisCluster.set("cluster-test", "hello jedis cluster");
//        System.out.println(jedisCluster.get("cluster-test"));
//        //系统关闭前关闭jedisCluster
//        jedisCluster.close();
//
//    }
}

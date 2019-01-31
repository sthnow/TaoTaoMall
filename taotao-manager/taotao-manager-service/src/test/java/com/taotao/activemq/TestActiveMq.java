package com.taotao.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Test;

import javax.jms.*;

public class TestActiveMq {

    @Test
    //queue
    //producer
    public void testQueueProducer() throws Exception{
        //1.创建一个连接工厂对象ConnectionFactory对象，需要指定mq服务的ip和端口号
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.100:61616");
        //2.使用ConnectionFactory创建一个connection对象
        Connection connection = connectionFactory.createConnection();
        //3.开启连接，调用connection对象的start方法
        connection.start();
        //4.使用connection对象创建一个session对象
        //第一个参数是是否开启事务，一般不使用事务。保证数据的最终一，可以使用消息队列实现
        //如果第一个参数为true,那么第二个参数自动忽略
        //如果第一个参数为false，第二个参数是消息的应答模式，通常有两种，手动应答和自动应答，一般为自动应答
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.使用session对象创建一个destination对象，两种形式queue和topic，现在应该使用queue
        //参数：指定当前消息队列的名称
        Queue queue = session.createQueue("test-queue");
        //6.使用session对象创建一个producer对象
        MessageProducer producer = session.createProducer(queue);
        //7.创建一个TextMessage对象
//        TextMessage textMessage = new ActiveMQTextMessage();
//        textMessage.setText("hello activeMq");
        TextMessage textMessage = session.createTextMessage("hellp activeMq");
        //8.发送消息
        producer.send(textMessage);
        //9.关闭资源
        producer.close();
        session.close();
        connection.close();

    }
}

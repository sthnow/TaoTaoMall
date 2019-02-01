package com.taotao.activemq;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

public class SpringActivemq {

    //使用Jmstemplate发送消息
    @Test
    public void testJmsTemplate() throws Exception{
        //初始化Spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:Spring/applicationContext-activemq.xml");
        //从容器中获得JmsTemplate对象
        //根据类型取
        JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
        //从容器中获得destination对象
        Destination destination =(Destination) applicationContext.getBean("test-queue");
        //发送消息
        //参数1：发送到那个消息队列    参数2：给这个消息队列发送什么消息
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("spring acitvemq send queue test");
                return textMessage;
            }
        });
    }
}

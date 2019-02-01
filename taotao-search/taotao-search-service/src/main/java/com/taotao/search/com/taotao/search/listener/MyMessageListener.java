package com.taotao.search.com.taotao.search.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


/**
 * 接收ActiveMq发送的消息
 */
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        //接收到消息
        TextMessage textMessage = (TextMessage) message;
        try {
            String text = ((TextMessage) message).getText();
            System.out.println(text);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

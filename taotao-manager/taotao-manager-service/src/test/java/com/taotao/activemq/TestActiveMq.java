package com.taotao.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Test;

import javax.jms.*;
import javax.xml.soap.Text;

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
        //第一个参数是是否开启事务（分布式事务），一般不使用事务。保证数据的最终一，可以使用消息队列实现
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
        TextMessage textMessage = session.createTextMessage("hello activeMq");
        //8.发送消息
        producer.send(textMessage);
        //9.关闭资源
        producer.close();
        session.close();
        connection.close();

    }

    @Test
    //接收消息
    public void testQueueConsumer() throws Exception{
        //创建一个连接工厂对象
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.100:61616");
        //使用连接工厂对象创建一个连接
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();
        //使用连接对象创建一个session对象
        final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //使用session创建一个destination，destination应该和消息的发送端一致
        Queue queue = session.createQueue("test-queue");
        //使用session创建一个消费者对象
        MessageConsumer consumer = session.createConsumer(queue);
        //向consumer对象中设置一个messageListener对象，用来接收消息
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                //取消息的内容
                if(message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        String text = textMessage.getText();
                        //打印消息内容
                        System.out.println(text);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //系统等待接收消息
//        while(true){
//            Thread.sleep(100);
//        }
        System.in.read();
        //关闭资源
        consumer.close();
        session.close();
        connection.close();
    }



    @Test
    //topic消息
    //producer
    public void testTopicProducer() throws Exception{
        //创建连接工厂对象
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.100:61616");
        //创建连接
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();
        //创建session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建destination，使用topic
        Topic topic = session.createTopic("test-topic");
        //创建producer对象
        MessageProducer producer = session.createProducer(topic);
        //创建一个testMessage对象
        TextMessage hello_topic = session.createTextMessage("hello topic");
        //发送消息
        producer.send(hello_topic);
        //关闭资源
        producer.close();
        session.close();
        connection.close();

    }

    @Test
    //接收topic消息
    public void testTopicConsumer1() throws Exception{
        //创建连接工厂对象
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.100:61616");
        //创建连接工厂
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();
        //创建session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建destination,相当于指定消息队列的名称，找那个消息队列接收消息
        Topic topic = session.createTopic("test-topic");
        //使用session创建一个消费者对象
        MessageConsumer consumer = session.createConsumer(topic);
        //向consumer对象中设置一个messageListener对象，用来接收消息
        consumer.setMessageListener(new MessageListener(){

            @Override
            public void onMessage(Message message) {
                //取消息的内容
                //如果是testMessage的实例
                if(message instanceof TextMessage){
                    //强转类型
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        //得到消息内容
                        String text = textMessage.getText();
                        //打印消息内容
                        System.out.println(text);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //系统等待接收消息
//        while(true){
//            Thread.sleep(100);
//        }
        System.in.read();
        //关闭资源
        consumer.close();
        session.close();
        connection.close();
    }

    @Test
    //接收topic消息
    public void testTopicConsumer2() throws Exception{
        //创建连接工厂对象
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.100:61616");
        //创建连接工厂
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();
        //创建session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建destination,相当于指定消息队列的名称，找那个消息队列接收消息
        Topic topic = session.createTopic("test-topic");
        //使用session创建一个消费者对象
        MessageConsumer consumer = session.createConsumer(topic);
        //向consumer对象中设置一个messageListener对象，用来接收消息
        consumer.setMessageListener(new MessageListener(){

            @Override
            public void onMessage(Message message) {
                //取消息的内容
                //如果是testMessage的实例
                if(message instanceof TextMessage){
                    //强转类型
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        //得到消息内容
                        String text = textMessage.getText();
                        //打印消息内容
                        System.out.println(text);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //系统等待接收消息
//        while(true){
//            Thread.sleep(100);
//        }
        System.in.read();
        //关闭资源
        consumer.close();
        session.close();
        connection.close();
    }

}

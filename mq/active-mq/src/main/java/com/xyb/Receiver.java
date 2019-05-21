package com.xyb;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Receiver {

    public static void main(String[] args) throws JMSException {

        //1.创建ConnectionFactory，需要填入用户名、密码、连接地址，默认端口位"tcp://localhost:61616"
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://localhost:61616");

        //2.创建连接，连接默认是关闭的
        Connection connection = connectionFactory.createConnection();

        //3.启动连接
        connection.start();

        //4.创建会话，用于接收消息，param1为是否启用事务(true支持)，param2为签收模式，一般设置自动签收。
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //5.创建一个目标，即生产者和消费者发送、接收消息的地方，注意topic和queue
        Destination topicDestination = session.createTopic("xybTopic");
        Destination queueDestination = session.createQueue("xybQueue");

        //6.创建生产或接收消息的生产者和消费者，此处可以为null，在producer.send(目的地，消息)时再确定目的地，更灵活
        MessageConsumer topicProducer = session.createConsumer(topicDestination);
        MessageConsumer queueProducer = session.createConsumer(queueDestination);

        //7.创建一个topic监听器
//        topicProducer.setMessageListener(new MessageListener() {
//
//            public void onMessage(Message message) {
//                TextMessage textMessage = (TextMessage)message;
//                try {
//                    System.out.println("接收xybTopic消息" + textMessage.getText());
//                } catch (JMSException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        //或者使用
//        while (true){
//            //receive(long)阻塞多长时间就不等了
//            TextMessage textMessage = (TextMessage)topicProducer.receive();
//            System.out.println("接收xybTopic消息" + textMessage.getText());
//        }

        //7.创建一个queue监听器
        queueProducer.setMessageListener(new MessageListener() {

            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage)message;
                try {
                    System.out.println("接收xybQueue消息" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
//        //或者使用
//        while (true){
//            TextMessage textMessage = (TextMessage)topicProducer.receive();
//            System.out.println("接收xybQueue消息" + textMessage.getText());
//        }


    }
}

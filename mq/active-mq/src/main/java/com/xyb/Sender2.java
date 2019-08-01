package com.xyb;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Sender2 {

    public static void main(String[] args) throws JMSException {
        //1.创建ConnectionFactory，需要填入用户名、密码、连接地址，默认端口位"tcp://localhost:61616"
        //当使用安全认证时，就不是默认账密了，需要在activemq.xml中找到对应插件上面的username和password，消费者同理
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://localhost:61616");

        //2.创建连接，连接默认是关闭的
        Connection connection = connectionFactory.createConnection();

        //3.启动连接
        connection.start();

        //4.创建会话，用于接收消息，param1为是否启用事务(true支持，且必须在发送消息后session.commit()，才能发送到mq中)
        // ，param2为签收模式，一般设置消费者自动签收。
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //5.创建一个目标，即生产者和消费者发送、接收消息的地方，注意topic和queue
        Destination queueDestination = session.createQueue("MQ_Data_Exchange_XybTest");

        //6.创建生产或接收消息的生产者和消费者，此处可以为null，在producer.send(目的地，消息)时再确定目的地，更灵活
        MessageProducer queueProducer = session.createProducer(queueDestination);

        //7.可以使用MessageProducer的setDeliveryMode方法为其设置持久化特性和非持久化特性
        //topicProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        //queueProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);


        ObjectMessage objectMessage = session.createObjectMessage("1111");
        //9. 发布消息，有多种参数，查看代码即可。
        queueProducer.send(objectMessage);

        System.out.println("发送queue消息" + objectMessage.toString());

        //如果开启事务，必须commit
        //session.commit();

        //9.关闭连接
        connection.close();
    }

}

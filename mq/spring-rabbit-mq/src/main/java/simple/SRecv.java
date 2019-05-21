package simple;


import com.rabbitmq.client.*;
import aUtils.ConnectionUtil;

import java.io.IOException;


/**
 * 〈一句话功能简述〉<br>
 * 〈消息消费者〉
 *
 * @author XYB
 * @create 2019/2/22
 * @since 1.0.0
 */
public class SRecv {

    private static final String QUEUE_NAME = "test_simple_queue";

    // 新版API
    public static void main(String[] args) throws Exception {
        // 1.获取连接
        Connection connection = ConnectionUtil.getConnection();

        // 2.从连接中创建通道
        Channel channel = connection.createChannel();

        // 3.声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 4.定义队列消费者，一旦有消息进入，则会触发此handleDelivery方法
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){

            // 处理接收到的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties basicProperties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("new api recv：" + msg);

            }
        };

        // 5.监听队列
        channel.basicConsume(QUEUE_NAME, true, defaultConsumer);

    }

    //老版API
//    public static void main(String[] args) throws Exception {
//        // 1.获取连接
//        Connection connection = ConnectionUtil.getConnection();
//
//        // 2.从连接中创建通道
//        Channel channel = connection.createChannel();
//
//        // 3.声明队列
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//
//        // 4.定义队列消费者
//        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
//
//        // 5.监听队列
//        channel.basicConsume(QUEUE_NAME, true, queueingConsumer);
//
//        // 6.获取消息
//        while(true){
//            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
//            String msg = new String(delivery.getBody());
//            System.out.println("--send msg：" + msg);
//        }
//
//
//    }

}
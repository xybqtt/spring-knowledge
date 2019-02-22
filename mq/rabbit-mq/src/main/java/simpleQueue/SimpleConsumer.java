package simpleQueue;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import utils.ConnectionUtil;


/**
 * 〈一句话功能简述〉<br> 
 * 〈消息消费者〉
 *
 * @author XYB
 * @create 2019/2/22
 * @since 1.0.0
 */
public class SimpleConsumer {

    private static final String QUEUE_NAME = "q_test_01";

    public static void main(String[] args) throws Exception {
        // 1.获取连接
        Connection connection = ConnectionUtil.getConnection();

        // 2.从连接中创建通道
        Channel channel = connection.createChannel();

        // 3.声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 4.定义队列的消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        // 5.监听队列
        channel.basicConsume(QUEUE_NAME, true, queueingConsumer);

        // 6.获取消息
        while(true){
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
        }


    }

}
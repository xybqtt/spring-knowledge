package pubSubModel.directModel;

import aUtils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author XYB
 * @create 2019/2/25
 * @since 1.0.0
 */
public class DirConsumer2 {
    private final static String QUEUE_NAME = "test_queue_direct_2";

    private final static String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws Exception {

        // 1.获取连接及通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 2.声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 3.绑定队列到交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "insert");
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "update");
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "delete");

        // 4.同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);

        // 5.定义队列消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);

        // 6.监听队列，手动返回完成
        channel.basicConsume(QUEUE_NAME, false, consumer);

        // 7.获取消息
        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [Recv] Received '" + message + "'");
            Thread.sleep(10);

            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }

    }
}
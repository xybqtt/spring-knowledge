package workModel;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import utils.ConnectionUtil;

/**
 * 〈一句话功能简述〉<br> 
 * 〈work模式下的消费者〉
 *
 * @author XYB
 * @create 2019/2/22
 * @since 1.0.0
 */
public class workConsumer2 {

    private static final String QUEUE_NAME = "test_queue_work";

    public static void main(String[] args) throws Exception {
        // 1.获取连接
        Connection connection = ConnectionUtil.getConnection();

        // 2.获取通道
        Channel channel = connection.createChannel();

        // 3.声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 4.同一时刻服务器只会发一条消息给消费者
        //channel.basicQos(1);

        // 5.定义队列的消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        // 6.监听队列，false表示手动返回完成状态，true表示自动
        channel.basicConsume(QUEUE_NAME, true, queueingConsumer);

        // 7.获取消息
        while(true){
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [y] Received2 '" + message + "'");
            // 休眠
            Thread.sleep(1000);
            // 返回确认状态，注释掉表示使用自动确认模式
            // channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }


    }

}


















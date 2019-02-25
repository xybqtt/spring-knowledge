package simpleQueue;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import aUtils.ConnectionUtil;


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

        // 4.同一时刻服务器只会发一条消息给消费者，即此条消息发送给消费者，在消费者返回ack确认前，不会再给此消费者发送消息
        // 为0时，则会一直发，为n时，则消费者最多保存并处理n条消息。
        channel.basicQos(5);

        // 4.定义队列的消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        // 5.监听队列
        /**
         * 参数autoAck：是否自动应答，
         * 模式1，为true：自动确认，只要消息从队列中获取，无论消费者获取到消息后是否成功消息，都认
         *      为是消息已经成功消费。设置自动模式了，就不要在下面进行手动反馈。
         * 模式2，为false：手动确认，消费者从队列中获取消息后，服务器会将该消息标记为不可用状态，等待消费者的反馈，如果消费者一直没
         *      有反馈，那么该消息将一直处于不可用状态。消息会一直保存在服务器，直到消息越来越多，内存溢出。
         *      使用channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);进行反馈。
         */
        channel.basicConsume(QUEUE_NAME, false, queueingConsumer);

        // 6.获取消息
        while(true){
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
            // 返回确认状态，如果channel.basicConsume(QUEUE_NAME, true, queueingConsumer);设置为false，则必须设置此项
             channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }


    }

}
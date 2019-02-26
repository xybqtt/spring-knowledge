package routing;

import aUtils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class RoutRecv1 {

    private static final String EXCHAGE_NAME = "test_exchange_direct";

    private static final String QUEUE_NAME = "test_queue_direct_1";

    public static void main(String[] args) throws Exception {
        // 1.获取连接
        Connection connection = ConnectionUtil.getConnection();

        // 2.获取通道
        Channel channel = connection.createChannel();

        // 3.声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 4.队列和交换机绑定
        channel.queueBind(QUEUE_NAME, EXCHAGE_NAME, "error");

        // 5.同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);

        // 6.定义队列的消费者
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){

            // 处理接收到的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties basicProperties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("[2] Recv msg：" + msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("[2] done");
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };

        // 6.监听队列，false表示手动返回完成状态，true表示自动
        channel.basicConsume(QUEUE_NAME, false, defaultConsumer);



    }
}

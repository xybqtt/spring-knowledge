package pubSub;

import aUtils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class PSRecv2 {

    private static final String QUEUE_NAME = "test_queue_fanout_sms";

    private static final String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws Exception {
        // 1.获取连接
        Connection connection = ConnectionUtil.getConnection();

        // 2.获取通道
        Channel channel = connection.createChannel();

        // 3.声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 4.绑定队列至交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

        // 4.同一时刻服务器只会发一条消息给消费者
//        channel.basicQos(1);

        // 5.定义队列的消费者
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
                }
            }
        };

        // 6.监听队列，false表示手动返回完成状态，true表示自动
        channel.basicConsume(QUEUE_NAME, true, defaultConsumer);



    }

}

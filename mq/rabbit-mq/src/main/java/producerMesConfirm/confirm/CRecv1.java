package producerMesConfirm.confirm;

import aUtils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 接收所有商品消息
 */
public class CRecv1 {

    private static final String QUEUE_NAME = "test_queue_confirm2";

    public static void main(String[] args) throws Exception {
        // 1.获取连接
        Connection connection = ConnectionUtil.getConnection();

        // 2.获取通道
        Channel channel = connection.createChannel();

        // 3.声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 4.同一时刻服务器只会发一条消息给消费者
//        channel.basicQos(1);

        // 5.定义队列的消费者
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {

            // 处理接收到的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties basicProperties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("[confirm] Recv msg：" + msg);
                System.out.println("[confirm] done");

            }
        };

        // 6.监听队列，false表示手动返回完成状态，true表示自动
        channel.basicConsume(QUEUE_NAME, false, defaultConsumer);


    }

}

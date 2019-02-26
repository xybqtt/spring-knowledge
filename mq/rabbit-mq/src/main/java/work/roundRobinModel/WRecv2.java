package work.roundRobinModel;

import aUtils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 〈一句话功能简述〉<br>
 * 〈work模式下的轮询消费者〉
 *
 * @author XYB
 * @create 2019/2/22
 * @since 1.0.0
 */
public class WRecv2 {

    private static final String QUEUE_NAME = "test_work_queue";

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


















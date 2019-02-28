package topic;

import aUtils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 当routingKey为goods.add时，两个都能接收，为goods.其它时，只有TopicRecv1可以接收。
 */
public class TopicSend {

    private static final String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] args) throws Exception {
        // 1.获取到连接
        Connection connection = ConnectionUtil.getConnection();

        // 2.从连接中创建通道
        Channel channel = connection.createChannel();

        // 3.声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");// 分发

        // 4.发送消息
        String msg = "商品.....";
        channel.basicPublish(EXCHANGE_NAME, "goods.adds", null, msg.getBytes());

        System.out.println("send :" + msg);
        channel.close();
        connection.close();
    }
}

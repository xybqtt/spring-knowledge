package routing;

import aUtils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class RoutSend {

    private static final String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws Exception {
        // 1.获取到连接
        Connection connection = ConnectionUtil.getConnection();

        // 2.从连接中创建通道
        Channel channel = connection.createChannel();

        // 3.声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        // 4.发送消息
        String msg = "hello direct error";
//        String routingKey = "info";
        String routingKey = "error";
//        String routingKey = "warning";
        channel.basicPublish(EXCHANGE_NAME, routingKey, null, msg.getBytes());

        channel.close();
        connection.close();
    }
}

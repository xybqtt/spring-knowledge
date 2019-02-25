package pubSubModel.fanoutModel;

import aUtils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 〈消息生产者〉
 *
 * @author XYB
 * @create 2019/2/25
 * @since 1.0.0
 */
public class FOProducer {

    private final static String EXCHANGE_NAME = "text_exchange_fanout";

    public static void main(String[] args) throws Exception {
        // 1.获取连接及通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 2.声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        // 3.消息内容
        String message = "Hello World";
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();

    }

}























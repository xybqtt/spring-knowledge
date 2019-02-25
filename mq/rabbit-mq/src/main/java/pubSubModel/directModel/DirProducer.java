package pubSubModel.directModel;

import aUtils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 〈一句话功能简述〉<br> 
 * 〈路由交换机生产者〉
 *
 * @author XYB
 * @create 2019/2/25
 * @since 1.0.0
 */
public class DirProducer {

    private final static String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws Exception {
        // 1.获取连接及通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 2.声明交换机及交换机类型
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        // 3.消息内容
        String message = "删除商品";
        // 声明交换机的消息key
        channel.basicPublish(EXCHANGE_NAME, "delete", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }

}













package simpleQueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.ConnectionUtil;

/**
 * 〈一句话功能简述〉<br> 
 * 〈消息生产者〉
 *
 * @author XYB
 * @create 2019/2/22
 * @since 1.0.0
 */
public class SimpleProducer {

    private final static String QUEUE_NAME = "q_test_01";

    public static void main(String[] args) throws Exception {
        // 1.获取到连接
        Connection connection = ConnectionUtil.getConnection();

        // 2.从连接中创建通道
        Channel channel = connection.createChannel();

        // 3.声明(创建)队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 4.消息内容
        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("[x] Sent '" + message + "'");

        // 5.关闭通道和连接
        channel.close();
        connection.close();




    }

}



















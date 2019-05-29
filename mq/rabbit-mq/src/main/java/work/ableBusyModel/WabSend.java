package work.ableBusyModel;

import aUtils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 〈一句话功能简述〉<br> 
 * 〈消息生产者〉
 *
 * @author XYB
 * @create 2019/2/22
 * @since 1.0.0
 */
public class WabSend {

    private final static String QUEUE_NAME = "test_queue_work";

    public static void main(String[] args) throws Exception {
        // 1.获取到连接
        Connection connection = ConnectionUtil.getConnection();

        // 2.从连接中创建通道
        Channel channel = connection.createChannel();

        // 3.声明(创建)队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        // 4.每个消费者发送ack消息前，消息队列不发送下一个消息到此消费者，一次只处理一个消息，限制发送给同一个消费者不得超过一条消息。
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);

        // 5.消息内容
        for(int i = 0; i< 50; i++){
            String message = "Hello World " + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("[x] Sent '" + message + "'");
            Thread.sleep(i * 5);
        }



        // 5.关闭通道和连接
        channel.close();
        connection.close();




    }

}



















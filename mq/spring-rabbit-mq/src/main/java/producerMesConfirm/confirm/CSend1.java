package producerMesConfirm.confirm;

import aUtils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * confirm普通模式
 */
public class CSend1 {

    private static final String QUEUE_NAME = "test_queue_confirm1";

    public static void main(String[] args) throws Exception {
        // 1.获取到连接
        Connection connection = ConnectionUtil.getConnection();

        // 2.从连接中创建通道
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 生产者调用confirmSelect，将channel设置为confirm模式，注意如果以前设置了amqp事务模式，则不能再次设置此模式。
        channel.confirmSelect();

        // 4.发送消息
        String msg = "hello confirm message";

        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());

        // 返回true，则发送成功。
        if(channel.waitForConfirms()){
            System.out.println("message send ok");
        } else {
            System.out.println("message send failed");
        }

        channel.close();
        connection.close();
    }
}

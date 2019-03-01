package producerMesConfirm.confirm;

import aUtils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * confirm批量模式
 */
public class CSend2 {

    private static final String QUEUE_NAME = "test_queue_confirm2";

    public static void main(String[] args) throws Exception {
        // 1.获取到连接
        Connection connection = ConnectionUtil.getConnection();

        // 2.从连接中创建通道
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 生产者调用confirmSelect，将channel设置为confirm模式，注意如果以前设置了amqp事务模式，则不能再次设置此模式。
        channel.confirmSelect();

        // 4.发送消息
        String msg = "hello confirm message batch";

        // 批量发送
        for(int i = 0; i < 50; i++){
            channel.basicPublish("", QUEUE_NAME, null, (msg + i).getBytes());
            System.out.println(i);
        }

        // 返回true，则发送成功。
        if(channel.waitForConfirms()){
            System.out.println("messages send ok");
        } else {
            System.out.println("messages send failed");
        }




        channel.close();
        connection.close();
    }
}

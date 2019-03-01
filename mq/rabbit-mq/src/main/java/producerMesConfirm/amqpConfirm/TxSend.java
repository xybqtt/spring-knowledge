package producerMesConfirm.amqpConfirm;

import aUtils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * producer的事务机制的amqp机制。
 * 会多次连接，降低吞吐量。
 */
public class TxSend {

    private static final String QUEUE_NAME = "test_queue_tx";

    public static void main(String[] args) throws Exception {
        // 1.获取到连接
        Connection connection = ConnectionUtil.getConnection();

        // 2.从连接中创建通道
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 4.发送消息
        String msg = "hello tx message";

        try{
            // 用户将当前channel设置为transation模式。
            channel.txSelect();
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());

            int xx = 1/0;

            System.out.println("send " + msg);
            channel.txCommit();
        } catch (Exception e) {
            channel.txRollback();
            System.out.println("send msg txRollback");
        }

        channel.txCommit();
        channel.close();
        connection.close();
    }
}

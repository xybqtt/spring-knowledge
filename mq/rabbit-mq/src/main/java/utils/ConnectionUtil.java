package utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 〈一句话功能简述〉<br> 
 * 〈rabbitMQ连接工具类〉
 *
 * @author XYB
 * @create 2019/2/22
 * @since 1.0.0
 */
public class ConnectionUtil {

    public static Connection getConnection() throws Exception {
        // 1.定义连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();

        // 2.设置服务地址
        connectionFactory.setHost("localhost");

        // 3.设置端口
        connectionFactory.setPort(5672);

        // 4.设置账户信息：用户名、密码、vhost
        connectionFactory.setVirtualHost("xybHost");
        connectionFactory.setUsername("xyb");
        connectionFactory.setPassword("xyb");

        // 5.通过工程获取连接
        Connection connection = connectionFactory.newConnection();
        return connection;
    }
}


























import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

public class AsynConfirmSend {
    public final static String EXCHANGE_NAME = "publisherconfirm-exchange";

    public static void execute(String host, String userName, String password,String routingKey, int num) {
        // 配置连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setUsername(userName);
        factory.setPassword(password);
        Connection connection = null;
        Channel channel = null;
        try {
            // 建立TCP连接
            connection = factory.newConnection();
            // 在TCP连接的基础上创建通道
            channel = connection.createChannel();
            // 声明一个direct交换机
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
            String message = "Transactional!" + System.currentTimeMillis();

            // 添加回调对象，处理返回值
            channel.addConfirmListener(new ConfirmListener(){
                @Override
                public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                    System.out.println("[AsynConfirmSend] handleAck : deliveryTag = " + deliveryTag + " multiple = " + multiple);
                }
                @Override
                public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                    System.out.println("[AsynConfirmSend] handleNack : deliveryTag = " + deliveryTag + " multiple = " + multiple);
                }
            });

            // 开启confirm模式：
            channel.confirmSelect();
            // 发送消息
            while(num-- > 0) {
                // 发送一个持久化消息到特定的交换机
                channel.basicPublish(EXCHANGE_NAME, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
                System.out.println(" [AsynConfirmSend] Sent '" + message + "'");
            }
            // 等待消息的回执
            Thread.sleep(1 * 1000);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                channel.close();
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // 测试线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // rabbitmq的IP地址
        String rabbitmq_host = "192.168.253.151";
        // rabbitmq的用户名称
        String rabbitmq_user = "root";
        // rabbitmq的用户密码
        String rabbitmq_pwd = "root";
        String routingKey = "publisher-confirm1";
        // 发送端
        executorService.submit(() -> {
            AsynConfirmSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routingKey, 2);
        });
    }
}

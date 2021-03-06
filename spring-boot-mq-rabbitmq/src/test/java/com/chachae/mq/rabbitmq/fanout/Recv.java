package com.chachae.mq.rabbitmq.fanout;

import com.chachae.mq.rabbitmq.util.ConnectUtil;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

// 消费者1
public class Recv {
  private static final String QUEUE_NAME = "fanout_exchange_queue_1";

  private static final String EXCHANGE_NAME = "fanout_exchange_test";

  public static void main(String[] args) throws Exception {
    // 获取到连接
    Connection connection = ConnectUtil.getConnection();
    // 获取通道
    Channel channel = connection.createChannel();
    // 声明队列
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);

    // 绑定队列到交换机
    channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");
    // 定义队列的消费者
    DefaultConsumer consumer =
        new DefaultConsumer(channel) {
          // 获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
          @Override
          public void handleDelivery(
              String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) {
            // body 即消息体
            String msg = new String(body);
            System.out.println(" [消费者1] received : " + msg + "!");
          }
        };
    // 监听队列，自动返回完成
    channel.basicConsume(QUEUE_NAME, true, consumer);
  }
}

package com.xsl.authority.test;

import org.junit.Test;

public class ActiveMqTest {

    /**
     * 点到点形式发送消息
     * @throws Exception
     */
    @Test
    public void testQueueProducer()throws Exception{
//        //1.创建工厂连接对象，需要指定服务器的ip及端口号
//        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://47.93.230.61:61616");
//        //2.使用工厂对象创建一个Connection对象
//        Connection connection = connectionFactory.createConnection();
//        //3.开启连接，调用Connection对象的start方法
//        connection.start();
//        //4.创建一个session对象
//        //第一个参数：是否开启事物，如果true开启事物，第二个参数无意义，一般不开启false
//        //第二个参数：应答模式，自动应答，或手动应答，一般自动应答
//        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
//        //5.使用session创建一个Destination对象，两种形式queue和topic
//        Queue queue = session.createQueue("test-queue");
//        //6.使用session对象创建一个Producer对象
//        MessageProducer producer = session.createProducer(queue);
//        //7.创建一个Message对象，可以使用TestMessage
//        TextMessage textMessage = session.createTextMessage("hello activemq");
//        //8.发送消息
//        producer.send(textMessage);
//        //9.关闭资源
//        producer.close();
//        session.close();
//        connection.close();
    }

    @Test
    public void testQueueConsumer() throws Exception{
//        //创建一个ConnectionFactory对象连接MQ服务器
//        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://47.93.19.164:61616");
//        //创建一个连接对象
//        Connection connection = connectionFactory.createConnection();
//        //开启连接
//        connection.start();
//        //使用connection对象创建一个session对象
//        final Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
//        //创建一个Destination对象，queue对象
//        Queue queue = session.createQueue("Authorityqueue");
//        //使用Session对象创建一个消费者对象
//        MessageConsumer consumer = session.createConsumer(queue);
//        //接收消息
//        consumer.setMessageListener(new MessageListener() {
//            @Override
//            public void onMessage(Message message) {
//                //打印结果
//                TextMessage textMessage = (TextMessage) message;
//                try {
//                    String text = textMessage.getText();
//                    System.out.println(text);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        });
//        System.in.read();
//        //关闭资源
//        consumer.close();
//        session.close();
//        connection.close();
    }
}

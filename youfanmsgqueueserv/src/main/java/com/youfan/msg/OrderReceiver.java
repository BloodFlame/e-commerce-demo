package com.youfan.msg;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.youfan.model.MessageLog;
import com.youfan.model.Order;
import com.youfan.service.MessageLogService;
import com.youfan.service.OrderService;
import com.youfan.vo.OrderAll;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
@RabbitListener(queues = "orderqueue")
public class OrderReceiver {

    @Autowired
    OrderService orderService;

    @Autowired
    private MessageLogService messageLogService;

    @RabbitHandler
    public void process(String order,Channel channel, Message message) throws IOException {
        System.out.println("OrderReceiver收到  : " + order +"收到时间"+new Date());

        try {
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            System.out.println("receiver success");

        } catch (Exception e) {
            e.printStackTrace();
            //丢弃这条消息
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
            System.out.println("receiver fail");
        }
 
    }


    @RabbitHandler
    public void process(OrderAll orderAll, Channel channel, Message message) throws IOException {
        if(isprocess(orderAll)){
            return;
        }
        String orderAlljson = JSONObject.toJSONString(orderAll);
        MessageLog messageLog = new MessageLog();
        messageLog.setYwtype("order");
        messageLog.setYwmessage(orderAlljson);
        messageLog.setYwmessagestatus(4);
        messageLog.setMsgupdatedate(new Date());
        System.out.println("OrderReceiver收到  : ==" + orderAlljson +"==收到时间"+new Date());
        try {
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            orderService.insertOutOrder(orderAll);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            messageLog.setYwmessagestatus(5);
            messageLogService.updateMessageLog(messageLog);
            System.out.println("receiver success");
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,true);
            messageLog.setYwmessagestatus(6);
            messageLogService.updateMessageLog(messageLog);
            System.out.println("receiver fail");
        }

    }

    /**
     * 业务是否处理完
     * @param orderAll
     * @return
     */
    public boolean isprocess(OrderAll orderAll){
        Order order = orderAll.getOrder();
        String tradenumber = order.getTradenumber();
        Order valicateOrder = orderService.findOrderByTradenumber(tradenumber);
        if(valicateOrder == null){
            return false;
        }else{
            return  true;
        }
    }
}

package com.youfan.msg;

import com.youfan.model.MessageLog;
import com.youfan.service.MessageLogService;
import com.youfan.vo.OrderAll;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderSender implements RabbitTemplate.ReturnCallback {
 
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessageLogService messageLogService;

    public void send(String msg) {
        MessageLog messageLog = new MessageLog();
        messageLog.setYwtype("order");
        messageLog.setYwmessage(msg);
        messageLog.setYwmessagestatus(1);
        messageLog.setMsgcreatedate(new Date());
        this.rabbitTemplate.setReturnCallback(this);
        this.rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                System.out.println("OrderSender消息发送失败" + cause + correlationData.toString());
                messageLog.setYwmessagestatus(3);
                messageLogService.insertMessageLog(messageLog);
            } else {
                System.out.println("OrderSender 消息发送成功 ");
                messageLog.setYwmessagestatus(2);
                messageLogService.insertMessageLog(messageLog);
            }
        });
        this.rabbitTemplate.convertAndSend("orderqueue", msg);
    }

    public void send(OrderAll orderAll) {

        this.rabbitTemplate.setReturnCallback(this);
        this.rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                System.out.println("OrderSender消息发送失败" + cause + correlationData.toString());
            } else {
                System.out.println("OrderSender 消息发送成功 ");
            }
        });
        this.rabbitTemplate.convertAndSend("orderqueue", orderAll);
    }

//    public void sendObj() {
//       MessageObj obj = new MessageObj();
//       obj.setACK(false);
//       obj.setId(123);
//       obj.setName("zhangsan");
//       obj.setValue("data");
//       System.out.println("发送 : " + obj);
//       this.rabbitTemplate.convertAndSend("helloObj", obj);
//    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println("sender return success" + message.toString()+"==="+i+"==="+s1+"==="+s2);
    }

//    @Override
//    public void confirm(CorrelationData correlationData, boolean b, String s) {
//        System.out.println("sender success");
//    }
 
}

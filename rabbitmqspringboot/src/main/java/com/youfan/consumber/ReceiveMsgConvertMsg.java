package com.youfan.consumber;

import com.youfan.config.RabbitMsgConvertConfigure;
import com.youfan.entity.MsgContent1;
import com.youfan.entity.MsgContent2;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
// @RabbitListener除了可以作用在方法，也可以作用在类上。在后者的情况下，需要在处理的方法使用@RabbitHandler。一个类可以配置多个@RabbitHandler
@RabbitListener(queues = RabbitMsgConvertConfigure.SPRING_BOOT_QUEUE)
public class ReceiveMsgConvertMsg {

    /**
     * 获取信息:
     *  queue也可以支持RabbitMQ中对队列的模糊匹配
     * @param content
     */
    @RabbitHandler
    public void receiveMsgContent1(MsgContent1 content) {
        // ...
        System.out.println("[ReceiveMsgConvertMsg-MsgContent1] receive receiveMsgContent1 msg: " + content);
    }

    @RabbitHandler
    public void receiveMsgContent2(MsgContent2 msgContent2) {
        // ...
        System.out.println("[ReceiveMsgConvertMsg-MsgContent2] receive receiveMsgContent2 msg: " + msgContent2);
    }

//    @RabbitHandler
//    public void receiveString(@Payload String content) {
//        // ...
//        System.out.println("[ReceiveMsgConvertMsg-MsgContent2] receive msg: " + content);
//    }
//
//    @RabbitHandler
//    public void receiveStringb(byte[] content) {
//        // ...
//        System.out.println("[ReceiveMsgConvertMsg-MsgContent2] receive msg: " + content);
//    }
}

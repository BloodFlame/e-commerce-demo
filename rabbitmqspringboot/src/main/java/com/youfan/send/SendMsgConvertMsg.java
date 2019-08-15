package com.youfan.send;

import com.youfan.config.RabbitMsgConvertConfigure;
import com.youfan.entity.MsgContent1;
import com.youfan.entity.MsgContent2;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendMsgConvertMsg {

    // 此接口的默认实现是RabbitTemplate，目前只有一个实现，
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 发送消息
     *
     * @param msgContent
     */
    public void sendMsgContent1(MsgContent1 msgContent) {
     //   amqpTemplate.convertAndSend(RabbitMsgConvertConfigure.SPRING_BOOT_EXCHANGE, RabbitMsgConvertConfigure.SPRING_BOOT_BIND_KEY, msgContent);
        amqpTemplate.convertAndSend(RabbitMsgConvertConfigure.SPRING_BOOT_EXCHANGE, RabbitMsgConvertConfigure.SPRING_BOOT_BIND_KEY, msgContent );


    }

    /**
     * 发送消息
     * @param msgContent
     */
    public void sendMsgContent2(MsgContent2 msgContent) {
        //   amqpTemplate.convertAndSend(RabbitMsgConvertConfigure.SPRING_BOOT_EXCHANGE, RabbitMsgConvertConfigure.SPRING_BOOT_BIND_KEY, msgContent);
        amqpTemplate.convertAndSend(RabbitMsgConvertConfigure.SPRING_BOOT_EXCHANGE, RabbitMsgConvertConfigure.SPRING_BOOT_BIND_KEY, msgContent);
    }
}

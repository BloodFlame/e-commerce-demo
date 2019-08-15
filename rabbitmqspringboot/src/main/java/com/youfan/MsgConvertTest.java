package com.youfan;

import com.youfan.entity.MsgContent1;
import com.youfan.entity.MsgContent2;
import com.youfan.send.SendMsgConvertMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ThreadLocalRandom;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= SpringBootRabbitApplication2.class, value = "spring.profiles.active=boot")
public class MsgConvertTest {
    @Autowired
    private SendMsgConvertMsg sendMsgConvertMsg;

    @Test
    public void sendMsgContent() throws Exception {
        // 发送消息对象MsgContent1
        MsgContent1 msgContent1 = new MsgContent1();
        msgContent1.setName("send msg via spring boot - msg convert - MsgContent1");
        msgContent1.setAge("" + ThreadLocalRandom.current().nextInt(100));
        sendMsgConvertMsg.sendMsgContent1(msgContent1);

        // 发送消息对象MsgContent2
        MsgContent2 msgContent2 = new MsgContent2();
        msgContent2.setId(ThreadLocalRandom.current().nextInt(100) + "");
        msgContent2.setContent("send msg via spring boot - msg convert - MsgContent1");
        sendMsgConvertMsg.sendMsgContent2(msgContent2);

        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

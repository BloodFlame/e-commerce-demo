package com.youfan.timer;

import com.alibaba.fastjson.JSONObject;
import com.youfan.dao.MsgDao;
import com.youfan.dao.ProductDao;
import com.youfan.model.MessageLog;
import com.youfan.model.Product;
import com.youfan.service.OrderMsgOutService;
import com.youfan.service.ProudctupateService;
import com.youfan.service.RedisService;
import com.youfan.utils.DateUtils;
import com.youfan.vo.OrderAll;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 实现高可靠的消息任务
 */
public class MsginfoTimer
    extends QuartzJobBean
{

    @Autowired
    MsgDao msgDao;

    @Autowired
    OrderMsgOutService orderMsgOutService;
    /**
     * logback
     */
    static Logger logger = LoggerFactory.getLogger(MsginfoTimer.class);
    /**
     * 定时任务逻辑实现方法
     * 每当触发器触发时会执行该方法逻辑
     * @param jobExecutionContext 任务执行上下文
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<MessageLog> msglist = msgDao.listbyall();
        for(MessageLog messageLog :msglist){
            logger.info("查询的消息实体是："+messageLog);
            int ywmessagestatus = messageLog.getYwmessagestatus();
            Date createtime = messageLog.getMsgcreatedate();//发送时间
            /**
             * //业务消息状态： 1未发送，2，已发送，3发送失败
             * 4,消费者未接受 5，消费成功，6消费失败
             */
            if(ywmessagestatus == 1){
                if(DateUtils.getBetweendays(createtime,new Date())>0){
                    //要发送
                    sendmsg(messageLog);
                }
            }else if (ywmessagestatus == 2){
                if(DateUtils.getBetweendays(createtime,new Date())>1){
                    //要发送
                    sendmsg(messageLog);
                }
            } else if (ywmessagestatus == 3){
                if(DateUtils.getBetweendays(createtime,new Date())>0){
                    //要发送
                    sendmsg(messageLog);
                }
            } else if (ywmessagestatus == 4){
                if(DateUtils.getBetweendays(createtime,new Date())>1){
                    //要发送
                    sendmsg(messageLog);
                }
            }else if (ywmessagestatus == 5){

            }else if (ywmessagestatus == 6){
                if(DateUtils.getBetweendays(createtime,new Date())>2){
                    //要发送
                    sendmsg(messageLog);
                }
            }
        }
        logger.info("商品信息更新定时任务执行，任务时间：{}",new Date());
    }

    public void sendmsg(MessageLog messageLog){
        String orderAlljson = messageLog.getYwmessage();
        OrderAll orderAll = JSONObject.parseObject(orderAlljson,OrderAll.class);
        orderMsgOutService.sendordermsgbyobj(orderAll);
    }


}

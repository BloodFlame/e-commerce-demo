package com.youfan.service;

import com.youfan.dao.MsgDao;
import com.youfan.model.MessageLog;
import com.youfan.timer.MsginfoTimer;
import com.youfan.timer.ProductinfoTimer;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2018/6/18 0018.
 */
@Service
public class MsgService {

    @Autowired
    private MsgDao msgDao;

    /**
     * 注入任务调度器
     */
    @Autowired
    private Scheduler scheduler;

    public List<MessageLog> listbyywmessagestatus(int ywmessagestatus){
        return msgDao.listbyywmessagestatus(ywmessagestatus);
    }

    public List<MessageLog> listbyall(){
        return msgDao.listbyall();
    }


    public void processmsgbystatus(){
        try {
            buildMsgbystatusTimer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 构建消息可靠的实现任务
     */
    private void buildMsgbystatusTimer() throws Exception
    {
        //任务名称
        String name = UUID.randomUUID().toString();
        //任务所属分组
        String group = MsginfoTimer.class.getName();

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/30 * * * * ?");
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(MsginfoTimer.class).withIdentity(name,group).build();
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name,group).withSchedule(scheduleBuilder).build();
        //将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
    }




}

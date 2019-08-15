package com.youfan.service;

import com.youfan.dao.ProductDao;
import com.youfan.model.Product;
import com.youfan.timer.ProductinfoTimer;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

/**
 * Created by Administrator on 2018/6/18 0018.
 */
@Service
public class ProductService {



    /**
     * 注入任务调度器
     */
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private RedisService redisService;

    public void updateproductbyid(){
        try {
            buildProductupdateTimer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 构建商品更新定时任务
     * @throws Exception
     */
    private void buildProductupdateTimer() throws Exception
    {
        //任务名称
        String name = UUID.randomUUID().toString();
        //任务所属分组
        String group = ProductinfoTimer.class.getName();

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("* 0/30 * * * ?");
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(ProductinfoTimer.class).withIdentity(name,group).build();
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name,group).withSchedule(scheduleBuilder).build();
        //将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
    }


}

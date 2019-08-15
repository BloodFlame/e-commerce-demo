package com.youfan.timer;

import com.alibaba.fastjson.JSONObject;
import com.youfan.dao.ProductDao;
import com.youfan.model.Product;
import com.youfan.service.ProudctupateService;
import com.youfan.service.RedisService;
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
 * 商品信息更新定时任务实现类
 */
public class ProductinfoTimer
    extends QuartzJobBean
{
    @Autowired
    RedisService redisService;

    @Autowired
    ProductDao productDao;

    @Autowired
    ProudctupateService proudctupateService;
    /**
     * logback
     */
    static Logger logger = LoggerFactory.getLogger(ProductinfoTimer.class);
    /**
     * 定时任务逻辑实现方法
     * 每当触发器触发时会执行该方法逻辑
     * @param jobExecutionContext 任务执行上下文
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Set<String> set = redisService.getallproductkeys();
        StringBuilder builder = new StringBuilder();
        List<String> ids = new ArrayList<String>();
        for(String o:set){
            ids.add(o);
        }
        List<Product> list = productDao.findproductbyids(ids);
        for(Product product :list){
            logger.info("测试list=="+product+"");
            String productjson = JSONObject.toJSONString(product);
            redisService.setkey(product.getId()+"",productjson);
            proudctupateService.upateProductbyquartz(product);
        }
        logger.info("商品信息更新定时任务执行，任务时间：{}",new Date());
    }
}

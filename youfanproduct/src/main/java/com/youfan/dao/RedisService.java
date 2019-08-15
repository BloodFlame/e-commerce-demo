package com.youfan.dao;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2018/9/9 0009.
 */
@FeignClient(value = "youfanredis")
public interface RedisService {

    @RequestMapping(value = "/getkey",method = RequestMethod.GET)
    public String getkey(@RequestParam(value = "key") String key);

    @RequestMapping(value = "/setkey",method = RequestMethod.GET)
    public void setkey(@RequestParam(value = "key") String key,@RequestParam(value = "value") String value);
}

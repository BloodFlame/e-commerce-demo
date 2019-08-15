package com.youfan.service;
import com.youfan.model.Order;
import com.youfan.vo.OrderAll;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/6/18 0018.
 */
@FeignClient(value = "youfanredis")
public interface RedisService {

    @RequestMapping(value = "/setkey",method = RequestMethod.GET)
    public void setkey(@RequestParam(value = "key") String key,@RequestParam(value = "value") String value) ;

    @RequestMapping(value = "/getallproductkeys",method = RequestMethod.POST)
    public Set<String> getallproductkeys();
}

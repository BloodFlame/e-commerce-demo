package com.youfan.service;
import com.youfan.model.Product;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

/**
 * Created by Administrator on 2018/6/18 0018.
 */
@FeignClient(value = "youfanproduct")
public interface ProudctupateService {

    @RequestMapping(value = "/upateProductbyquartz",method = RequestMethod.POST)
    public void upateProductbyquartz(@RequestBody Product product);
}

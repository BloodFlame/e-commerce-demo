package com.youfan.service;

import com.youfan.vo.OrderAll;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by youfan on 2018/6/6 0006.
 */
@FeignClient(value = "youfanmsgqueueserv")
public interface OrderMsgOutService {

    @RequestMapping(value = "/sendordermsg",method = RequestMethod.GET)
    public void sendordermsg(@RequestParam(value = "msg") String msg);

    @RequestMapping(value = "/sendordermsgbyobj",method = RequestMethod.POST)
    public void sendordermsgbyobj(@RequestBody OrderAll orderAll);
}

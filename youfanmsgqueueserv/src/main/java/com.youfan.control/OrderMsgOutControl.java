package com.youfan.control;

import com.youfan.msg.OrderSender;
import com.youfan.utils.HelloSender;
import com.youfan.vo.OrderAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by youfan on 2018/6/6 0006.
 */
@RestController
public class OrderMsgOutControl {
    @Autowired
    private OrderSender orderSender;

    @RequestMapping(value = "/sendordermsg",method = RequestMethod.GET)
    public void sendordermsg(@RequestParam String msg) {
        orderSender.send(msg);
    }

    @RequestMapping(value = "/sendordermsgbyobj",method = RequestMethod.POST)
    public void sendordermsgbyobj(@RequestBody OrderAll orderAll) {
        orderSender.send(orderAll);
    }
}

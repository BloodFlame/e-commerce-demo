package com.youfan.control;

import com.youfan.model.MessageLog;
import com.youfan.service.MsgService;
import com.youfan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 */
@Controller
@RequestMapping(value = "/msgController")
public class MsgController {

    @Autowired
    private MsgService msgService;

    /**
     */
    @RequestMapping(value = "/listall")
    public String listall(Model model) {
        List<MessageLog> msglist = msgService.listbyall();
        model.addAttribute("messagelist",msglist);
        return "listallmsg";
    }

}

package com.youfan.control;

import com.youfan.model.MessageLog;
import com.youfan.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 */
@RestController
@RequestMapping(value = "/msgrestController")
public class MsgRestController {

    @Autowired
    private MsgService msgService;


    @RequestMapping(value = "/starttask")
    public void starttask() {
        msgService.processmsgbystatus();
    }
}

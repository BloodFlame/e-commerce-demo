package com.youfan.service;

import com.youfan.dao.MessageLogDao;
import com.youfan.mapper.MessageLogMapper;
import com.youfan.model.MessageLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by youfan on 2018/6/8 0008.
 */
@Component
public class MessageLogService {

    @Autowired
    MessageLogDao messageLogDao;

    public void insertMessageLog(MessageLog messageLog){

        messageLogDao.insertMessageLog(messageLog);
    };
    public void updateMessageLog(MessageLog messageLog){

        messageLogDao.updateMessageLog(messageLog);
    };
}

package com.youfan.dao;

import com.youfan.mapper.MessageLogMapper;
import com.youfan.model.MessageLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by youfan on 2018/6/8 0008.
 */
@Component
public class MessageLogDao {

    @Autowired
    MessageLogMapper messageLogMapper;

    public void insertMessageLog(MessageLog messageLog){
        messageLogMapper.insertMessageLog(messageLog);
    };
    public void updateMessageLog(MessageLog messageLog){
        messageLogMapper.updateMessageLog(messageLog);
    };
}

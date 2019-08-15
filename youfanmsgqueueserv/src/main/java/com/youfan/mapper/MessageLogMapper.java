package com.youfan.mapper;

import com.youfan.model.MessageLog;
import com.youfan.model.ProductType;

import java.util.List;

/**
 * Created by youfan on 2018/6/8 0008.
 */
public interface MessageLogMapper {
    public void insertMessageLog(MessageLog messageLog);
    public void updateMessageLog(MessageLog messageLog);
}

package com.youfan.dao;

import com.youfan.mapper.MsgMapper;
import com.youfan.mapper.ProductMapper;
import com.youfan.model.MessageLog;
import com.youfan.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by youfan on 2018/6/8 0008.
 */
@Component
public class MsgDao {
    @Autowired
    MsgMapper msgMapper;




    public List<MessageLog> listbyywmessagestatus(int ywmessagestatus){
       return msgMapper.listbyywmessagestatus(ywmessagestatus);
    }

    public List<MessageLog> listbyall(){
            return msgMapper.listbyall();
    }

}

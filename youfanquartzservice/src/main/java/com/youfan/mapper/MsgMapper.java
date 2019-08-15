package com.youfan.mapper;

import com.youfan.model.MessageLog;
import com.youfan.model.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2018/6/18 0018.
 */
@Mapper
public interface MsgMapper {
  public List<MessageLog> listbyywmessagestatus(int ywmessagestatus);

    public List<MessageLog> listbyall();

}

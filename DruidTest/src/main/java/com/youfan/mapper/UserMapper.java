package com.youfan.mapper;

import com.youfan.vo.UserInfoRequest;
import com.youfan.vo.UserInfoResponse;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 */
@Mapper
public interface UserMapper {

    UserInfoResponse getUserInfo(UserInfoRequest request);
}
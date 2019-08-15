package com.youfan.Controller;

import com.youfan.mapper.UserMapper;
import com.youfan.vo.UserInfoRequest;
import com.youfan.vo.UserInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class WebController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public UserInfoResponse login(String username,String password){
        UserInfoRequest request = new UserInfoRequest();
        request.setUsername(username);
        request.setPassword(password);
          UserInfoResponse userInfoResponse = new UserInfoResponse();
          userInfoResponse = userMapper.getUserInfo(request);
          return userInfoResponse;
    }

}
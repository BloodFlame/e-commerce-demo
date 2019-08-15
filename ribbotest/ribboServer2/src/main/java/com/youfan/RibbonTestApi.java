package com.youfan;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ribbon")
public class RibbonTestApi {

    /**
     * 获取博客名称API
     *
     * @return 相关信息
     */
    @RequestMapping(value = "name", method = RequestMethod.GET)
    public String getMyBlogNameApi() {
        return "千万之路刚开始www.youfan.com"+"该服务器端口号：7072";
    }
}

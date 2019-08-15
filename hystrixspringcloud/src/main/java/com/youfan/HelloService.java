package com.youfan;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

@Service
public class HelloService {


    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloService(){
        try {
            Thread.sleep(3000);
        }catch (Exception e){

        }
        String a = "helloword";
        return a;
    }

    public String helloFallback(){
        return "error";
    }
}
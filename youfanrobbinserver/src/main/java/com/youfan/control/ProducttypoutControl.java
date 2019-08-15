package com.youfan.control;


import com.youfan.model.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by youfan on 2018/6/6 0006.
 */
@RestController
public class ProducttypoutControl {
    /**
     * 注入RestTemplate
     */
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/listproducttype",method = RequestMethod.GET)
    public List<ProductType> listproducttype(){
        String url="http://YOUFANPRODUCTTYPE/listproducttype";
        List<ProductType> list = restTemplate.getForObject(url,List.class);
        return list;
    }

}

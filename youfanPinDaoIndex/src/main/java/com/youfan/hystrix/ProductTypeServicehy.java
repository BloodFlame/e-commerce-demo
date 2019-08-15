package com.youfan.hystrix;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.youfan.model.ProductType;
import com.youfan.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/18 0018.
 */
@Service
public class ProductTypeServicehy {

    @Autowired
    ProductTypeService productTypeService ;

    @HystrixCommand(fallbackMethod = "listproductTypeFallback")
    public List<ProductType> listproductType(){
        return productTypeService.listproductType();
    }

    public List<ProductType> listproductTypeFallback(){
        ProductType productType = new ProductType();
        productType.setId(1);
        productType.setParentid(-1);
        productType.setProducttypedescription("吃的");
        productType.setProducttypename("食品18");
        productType.setTypegrade("1");
        List<ProductType> list = new ArrayList<ProductType>();
        list.add(productType);
        return list;

    }



}

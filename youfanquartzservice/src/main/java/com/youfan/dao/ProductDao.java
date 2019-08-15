package com.youfan.dao;

import com.youfan.mapper.ProductMapper;
import com.youfan.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by youfan on 2018/6/8 0008.
 */
@Component
public class ProductDao {
    @Autowired
    ProductMapper productMapper;




    public List<Product> findproductbyids(List<String> ids){
        return productMapper.findproductbyids(ids);

    }

}

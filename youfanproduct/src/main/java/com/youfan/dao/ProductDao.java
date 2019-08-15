package com.youfan.dao;

import com.youfan.mapper.ProductMapper;
import com.youfan.model.Product;
import com.youfan.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by youfan on 2018/6/8 0008.
 */
@Component
@CacheConfig(cacheNames = "productchache")
public class ProductDao {
    @Autowired
    ProductMapper productMapper;


    public int insertProduct(Product product){

        return productMapper.insertProduct(product);
    }

    public void auditProduct(Product product){
        productMapper.auditProduct(product);
    }

    @Cacheable(value="productchache",key="#id")
    public Product findproductbyid(int id){
        System.out.println("进入查询");
        return productMapper.findproductbyid(id);

    }

    @CachePut(value="productchache",key="#product.id")
    public Product upateProduct(Product product){
         productMapper.upateProduct(product);
         return product;
    }

    public void deleteProductbyid(int id){
        productMapper.deleteProductbyid(id);
    }

    public List<Product> queryProductbyvo(ProductVo productVo){
        return productMapper.queryProductbyvo(productVo);
    }


    public void upateProductbyproductstatus(Product product){
        productMapper.upateProductbyproductstatus(product);
    }

    public List<Product> queryProductbyids(List<String> ids){
        Map<String,List<String>> map = new HashMap<String,List<String>>();
        map.put("ids",ids);
        return productMapper.queryProductbyids(map);
    }

    @CachePut(value="productchache",key="#product.id")
    public Product upateProductbyquartz(Product product){
        return product;
    }
}

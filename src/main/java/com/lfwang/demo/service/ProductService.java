package com.lfwang.demo.service;

import com.lfwang.demo.repository.domain.Product;
import com.lfwang.demo.repository.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lfwang on 2017/8/22.
 */
@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;
    
    public Product getById(long id) {
        return productMapper.getById(id);
    }
    
    public long insert(String name, long price) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        return productMapper.insert(product);
    }
    
    public void update(long id, String name, long price) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        productMapper.update(product);
    }
    
    public void deleteById(long id) {
        productMapper.deleteById(id);
    }
}

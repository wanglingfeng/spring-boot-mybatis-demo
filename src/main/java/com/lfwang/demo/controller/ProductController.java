package com.lfwang.demo.controller;

import com.lfwang.demo.repository.domain.Product;
import com.lfwang.demo.repository.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lfwang on 2017/8/21.
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private ProductMapper productMapper;
    
    @GetMapping("/{id}")
    public Product get(@PathVariable long id) {
        return productMapper.get(id);
    }
    
    @PostMapping("/{id}")
    public void update(@PathVariable long id, @RequestParam String name, @RequestParam long price) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        productMapper.update(product);
    }
}

package com.lfwang.demo.controller;

import com.lfwang.demo.repository.domain.Product;
import com.lfwang.demo.repository.mapper.ProductMapper;
import com.lfwang.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lfwang on 2017/8/21.
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping("/{id}")
    public Product get(@PathVariable long id) {
        return productService.get(id);
    }
    
    @PostMapping("/{id}")
    public void update(@PathVariable long id, @RequestParam String name, @RequestParam long price) {
        productService.update(id, name, price);
    }
}

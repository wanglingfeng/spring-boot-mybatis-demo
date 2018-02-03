package com.lfwang.demo.controller;

import com.lfwang.demo.controller.domain.ProductDTO;
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
    public ProductDTO getById(@PathVariable long id) {
        return productService.getById(id);
    }
    
    @PostMapping
    public long insert(@RequestParam String name, @RequestParam long price) {
        return productService.insert(name, price);
    }
    
    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestParam String name, @RequestParam long price) {
        productService.update(id, name, price);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        productService.deleteById(id);
    }
}

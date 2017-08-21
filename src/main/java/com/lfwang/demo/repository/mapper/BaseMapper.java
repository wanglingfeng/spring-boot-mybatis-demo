package com.lfwang.demo.repository.mapper;

import com.lfwang.demo.repository.domain.Product;

/**
 * Created by lfwang on 2017/8/21.
 */
public interface BaseMapper<T> {

    T get(long id);
    
    void update(Product product);
}

package com.lfwang.demo.repository.mapper;

import com.lfwang.demo.repository.domain.Product;

/**
 * Created by lfwang on 2017/8/21.
 */
public interface ProductMapper {

    Product getById(long id);

    long insert(Product product);

    void update(Product product);

    void deleteById(long id);

    void delete(Product product);
}

package com.lfwang.demo.factory.impl;

import com.lfwang.demo.factory.BaseFactory;
import org.springframework.stereotype.Service;

/**
 * @author keleguo
 * @date Created in 2018/5/7
 */
@Service
public class FemaleFactoryImpl implements BaseFactory {

    @Override
    public String getRouting() {
        return "female";
    }

    @Override
    public void process(String word) {
        System.out.println("im female, i say " + word);
    }
}

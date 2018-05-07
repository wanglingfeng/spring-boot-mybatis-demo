package com.lfwang.demo.factory.impl;

import com.lfwang.demo.factory.BaseFactory;
import org.springframework.stereotype.Service;

/**
 * @author keleguo
 * @date Created in 2018/5/7
 */
@Service
public class MaleFactoryImpl implements BaseFactory {

    @Override
    public String getRouting() {
        return "male";
    }

    @Override
    public void process(String word) {
        System.out.println("im male, i say " + word);
    }
}

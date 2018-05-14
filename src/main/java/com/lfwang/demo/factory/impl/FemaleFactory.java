package com.lfwang.demo.factory.impl;

import com.lfwang.demo.factory.HumanFactory;
import org.springframework.stereotype.Service;

/**
 * @author keleguo
 * @date Created in 2018/5/7
 */
@Service
public class FemaleFactory implements HumanFactory {

    @Override
    public String getSex() {
        return "female";
    }

    @Override
    public void say(String word) {
        System.out.println("im female, i say " + word);
    }
}

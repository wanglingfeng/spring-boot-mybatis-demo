package com.lfwang.demo.service;

import org.springframework.stereotype.Service;

/**
 * @author keleguo
 * @date Created in 2018/5/2
 */
@Service
public class ExecuteService {

    public void execute(String word) {
        System.out.println("word is: " + word);
    }
}

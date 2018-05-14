package com.lfwang.demo;

import com.lfwang.demo.factory.FactoryContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * spring实现工厂模式
 *
 * @author keleguo
 * @date Created in 2018/5/7
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FactoryTests {

    @Autowired
    private FactoryContext factoryContext;

    @Test
    public void getAndExecute() {
        String routing = "female";
        String word = "hello";

        factoryContext.process(routing, word);
    }
}

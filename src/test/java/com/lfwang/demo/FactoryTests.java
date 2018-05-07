package com.lfwang.demo;

import com.lfwang.demo.factory.BaseFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
    private List<BaseFactory> factories;

    @Test
    public void getAndExecute() {
        String routing = "female";
        String word = "hello";

        factories.stream().filter(f -> f.getRouting().equals(routing)).findFirst().ifPresent(f -> f.process(word));
    }
}

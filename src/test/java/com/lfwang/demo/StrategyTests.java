package com.lfwang.demo;

import com.lfwang.demo.strategy.StrategyContext;
import com.lfwang.demo.strategy.request.CatRequest;
import com.lfwang.demo.strategy.request.DogRequest;
import com.lfwang.demo.strategy.response.CatResponse;
import com.lfwang.demo.strategy.response.DogResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author keleguo
 * @date Created in 2018/5/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyTests {

    @Autowired
    private StrategyContext strategyContext;

    @Test
    public void test() {
        System.out.println("strategy test animal say......\n");

        DogResponse dogResponse = strategyContext.say(new DogRequest("woof woof woof"));
        System.out.println(dogResponse);

        System.out.println("----------");

        CatResponse catResponse = strategyContext.say(new CatRequest("meow meow meow"));
        System.out.println(catResponse);
    }
}

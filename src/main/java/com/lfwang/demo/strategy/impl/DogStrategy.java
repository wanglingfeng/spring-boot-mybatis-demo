package com.lfwang.demo.strategy.impl;

import com.lfwang.demo.strategy.request.DogRequest;
import com.lfwang.demo.strategy.response.DogResponse;
import org.springframework.stereotype.Service;

/**
 * @author keleguo
 * @date Created in 2018/5/10
 */
@Service
public class DogStrategy extends AbstractAnimalStrategy<DogRequest, DogResponse> {

    @Override
    public DogResponse process(DogRequest request) {
        System.out.println("doy say " + request.getWord());
        return new DogResponse("what does dog say !!!");
    }
}

package com.lfwang.demo.strategy.impl;

import com.lfwang.demo.strategy.request.CatRequest;
import com.lfwang.demo.strategy.response.CatResponse;
import org.springframework.stereotype.Service;

/**
 * @author keleguo
 * @date Created in 2018/5/10
 */
@Service
public class CatStrategy extends AbstractAnimalStrategy<CatRequest, CatResponse> {

    @Override
    public CatResponse process(CatRequest request) {
        System.out.println("cat say " + request.getWord());
        return new CatResponse("what does cat say !!!");
    }
}

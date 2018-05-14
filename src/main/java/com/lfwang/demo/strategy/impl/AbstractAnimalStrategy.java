package com.lfwang.demo.strategy.impl;

import com.lfwang.demo.strategy.AnimalStrategy;
import com.lfwang.demo.strategy.request.AnimalRequest;
import com.lfwang.demo.strategy.response.AnimalResponse;

import java.util.Date;

/**
 * @author keleguo
 * @date Created in 2018/5/10
 */
public abstract class AbstractAnimalStrategy<REQ extends AnimalRequest, RESP extends AnimalResponse> implements AnimalStrategy<REQ, RESP> {

    @Override
    public RESP say(REQ request) {
        System.out.println("im abstract animal strategy now is " + new Date().toString());
        return process(request);
    }

    /**
     * 正常处理
     * @param request request
     * @return response
     */
    public abstract RESP process(REQ request);
}

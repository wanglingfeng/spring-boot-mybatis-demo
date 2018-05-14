package com.lfwang.demo.strategy;

/**
 * @author keleguo
 * @date Created in 2018/5/10
 */
public interface AnimalStrategy<REQ, RESP> {

    /**
     * say
     * @param request request
     * @return response
     */
    RESP say(REQ request);
}

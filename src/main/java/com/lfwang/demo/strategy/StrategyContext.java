package com.lfwang.demo.strategy;

import com.lfwang.demo.strategy.request.AnimalRequest;
import com.lfwang.demo.strategy.response.AnimalResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author keleguo
 * @date Created in 2018/5/10
 */
@Component
public class StrategyContext {

    private static final String REQUEST_SUFFIX = "Request";
    private static final String STRATEGY_SUFFIX = "Strategy";

    private final ApplicationContext applicationContext;

    public StrategyContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public <REQ extends AnimalRequest, RESP extends AnimalResponse> RESP say(REQ request) {
        String strategyClassName = getStrategyClassName(request.getClass());
        AnimalStrategy<REQ, RESP> animalStrategy = applicationContext.getBean(strategyClassName, AnimalStrategy.class);

        return animalStrategy.say(request);
    }

    private String getStrategyClassName(Class requestClass) {
        String className = requestClass.getSimpleName().replace(REQUEST_SUFFIX, STRATEGY_SUFFIX);
        return StringUtils.uncapitalize(className);
    }
}

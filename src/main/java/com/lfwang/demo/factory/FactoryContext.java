package com.lfwang.demo.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author keleguo
 * @date Created in 2018/5/10
 */
@Component
public class FactoryContext {

    private static Map<String, Optional<HumanFactory>> factoryMap = new ConcurrentHashMap<>();

    @Autowired
    private List<HumanFactory> factories;

    @PostConstruct
    public void init() {
        factories.forEach(h -> factoryMap.put(h.getSex(), Optional.ofNullable(h)));
    }

    public void process(String routing, String word) {
        factoryMap.get(routing).ifPresent(f -> f.say(word));
    }
}

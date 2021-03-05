package com.lfwang.demo.controller;

import com.lfwang.demo.pulsar.DemoMessage;
import com.lfwang.demo.pulsar.DemoMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanglingfeng
 * @date Created in 2021/3/5
 */
@RestController
@RequestMapping("/pulsar")
public class PulsarController {

    @Autowired
    private DemoMessageProducer demoMessageProducer;

    @GetMapping
    public void sendMessage() {
        DemoMessage message = new DemoMessage();
        message.setName("壹加壹等于贰");
        demoMessageProducer.send(message);
    }
}

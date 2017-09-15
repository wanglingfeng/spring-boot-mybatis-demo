package com.lfwang.demo.controller;

import com.lfwang.demo.controller.domain.MessageDTO;
import com.lfwang.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lfwang on 2017/9/15.
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public String getById(MessageDTO dto) {
        String message = messageService.buildMessage(dto);
        System.out.println(dto.getMessage());
        
        return message;
    }
}

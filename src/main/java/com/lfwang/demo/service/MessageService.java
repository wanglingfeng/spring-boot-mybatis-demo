package com.lfwang.demo.service;

import com.lfwang.demo.controller.domain.MessageDTO;
import org.springframework.stereotype.Service;

/**
 * Created by lfwang on 2017/9/15.
 */
@Service
public class MessageService {
    
    public String buildMessage(MessageDTO dto) {
        String message = "hello " + dto.getName();
        dto.setMessage(message);
        return message;
    }
}

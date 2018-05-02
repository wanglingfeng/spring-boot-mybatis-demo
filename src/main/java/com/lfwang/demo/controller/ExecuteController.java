package com.lfwang.demo.controller;


import com.lfwang.demo.service.ExecuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author keleguo
 * @date Created in 2018/5/2
 */
@RestController
@RequestMapping("/execute")
public class ExecuteController {

    @Autowired
    private ExecuteService executeService;

    @GetMapping
    public void execute(@RequestParam String word) {
        executeService.execute(word);
        System.out.println("*** execute end ***");
    }
}

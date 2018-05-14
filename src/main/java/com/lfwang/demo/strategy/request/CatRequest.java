package com.lfwang.demo.strategy.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author keleguo
 * @date Created in 2018/5/10
 */
@Data
@AllArgsConstructor
public class CatRequest implements AnimalRequest {

    private String word;
}

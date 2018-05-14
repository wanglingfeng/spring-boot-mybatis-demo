package com.lfwang.demo.strategy.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author keleguo
 * @date Created in 2018/5/10
 */
@Data
@AllArgsConstructor
public class CatResponse implements AnimalResponse {

    private String kind;
}

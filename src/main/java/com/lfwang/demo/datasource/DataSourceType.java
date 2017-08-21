package com.lfwang.demo.datasource;

import lombok.Getter;

/**
 * Created by lfwang on 2017/8/21.
 */
public enum DataSourceType {
    read("read", "从库"), 
    write("write", "主库");

    @Getter
    private String type;
    @Getter
    private String name;

    DataSourceType(String type, String name) {
        this.type = type;
        this.name = name;
    }
}

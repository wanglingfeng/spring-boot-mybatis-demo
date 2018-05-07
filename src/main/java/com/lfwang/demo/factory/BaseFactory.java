package com.lfwang.demo.factory;

/**
 * @author keleguo
 * @date Created in 2018/5/7
 */
public interface BaseFactory {

    /**
     * 获取路由
     *
     * @return 路由
     */
    default String getRouting() {
        return "";
    }

    /**
     * 处理
     * @param word 单词
     */
    void process(String word);
}

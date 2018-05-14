package com.lfwang.demo.factory;

/**
 * @author keleguo
 * @date Created in 2018/5/7
 */
public interface HumanFactory {

    /**
     * 获取性别
     *
     * @return 路由
     */
    default String getSex() {
        return "";
    }

    /**
     * say
     * @param word word
     */
    void say(String word);
}

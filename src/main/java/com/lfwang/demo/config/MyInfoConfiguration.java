package com.lfwang.demo.config;

import com.lfwang.demo.processor.annotation.MyInfo;

@MyInfo(name = "${customize.name}", age = 28)
public class MyInfoConfiguration {
}

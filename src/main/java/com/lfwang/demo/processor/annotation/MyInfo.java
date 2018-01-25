package com.lfwang.demo.processor.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Component
public @interface MyInfo {

    String name() default "";

    int age() default 0;
}

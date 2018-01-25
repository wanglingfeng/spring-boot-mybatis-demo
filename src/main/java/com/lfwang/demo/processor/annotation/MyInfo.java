package com.lfwang.demo.processor.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface MyInfo {

    String name() default "";

    int age() default 0;
}

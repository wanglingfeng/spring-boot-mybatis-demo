package com.lfwang.demo;

import com.lfwang.demo.processor.MyInfoAnnotationBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(basePackages = "com.lfwang.demo.repository.mapper")
public class SpringBootMybatisDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybatisDemoApplication.class, args);
	}

	@Bean
	public MyInfoAnnotationBean myInfoAnnotationBean() {
		MyInfoAnnotationBean bean = new MyInfoAnnotationBean();
		bean.setPackage("com.lfwang.demo");

		return bean;
	}
}

package com.lfwang.demo.processor;

import com.lfwang.demo.processor.annotation.MyInfo;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;

/**
 * 自定义注解解析
 * postProcessBeanFactory方法作用是添加扫描，扫描通过setPackage指定的包种的spring bean
 * postProcessAfterInitialization方法作用是获取指定注解的信息分析并处理
 */
public class MyInfoAnnotationBean implements ApplicationContextAware, BeanFactoryPostProcessor, BeanPostProcessor {

    private String annotationPackage;

    private String[] annotationPackages;


    private ApplicationContext applicationContext;

    private static final String COMMA_SPLIT_PATTERN = "\\s*[,]+\\s*";

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        if (annotationPackage == null || annotationPackage.length() == 0) {
            return;
        }
        if (beanFactory instanceof BeanDefinitionRegistry) {
            try {
                // init scanner
                Class<?> scannerClass = ClassUtils.forName(
                        "org.springframework.context.annotation.ClassPathBeanDefinitionScanner",
                        MyInfoAnnotationBean.class.getClassLoader());

                Object scanner = scannerClass
                        .getConstructor(new Class<?>[]{BeanDefinitionRegistry.class, boolean.class})
                        .newInstance((BeanDefinitionRegistry) beanFactory, true);

                // add filter
                Class<?> filterClass = ClassUtils.forName(
                        "org.springframework.core.type.filter.AnnotationTypeFilter",
                        MyInfoAnnotationBean.class.getClassLoader());

                Object filter = filterClass.getConstructor(Class.class).newInstance(MyInfo.class);

                Method addIncludeFilter = scannerClass.getMethod("addIncludeFilter",
                        ClassUtils.forName(
                                "org.springframework.core.type.filter.TypeFilter",
                                MyInfoAnnotationBean.class.getClassLoader()));

                addIncludeFilter.invoke(scanner, filter);
                // scan packages
                Method scan = scannerClass.getMethod("scan", String[].class);
                scan.invoke(scanner, new Object[]{annotationPackages});
            } catch (Throwable e) {
                // spring 2.0
            }
        }
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!isMatchPackage(bean)) return bean;

        Class<?> clazz = bean.getClass();

        if (isProxyBean(bean)) clazz = AopUtils.getTargetClass(bean);

        MyInfo info = clazz.getAnnotation(MyInfo.class);

        if (null != info) {
            if (info.name().length() > 0) {
                // 如果使用了spring el该方法可读取对应的值
                String name = this.applicationContext.getEnvironment().resolveRequiredPlaceholders(info.name());
                System.out.println("name is: " + name);
            }

            if (info.age() > 0) {
                int age = info.age();
                System.out.println("age: " + age);
            }
        }

        return bean;
    }

    private boolean isMatchPackage(Object bean) {
        if (annotationPackages == null || annotationPackages.length == 0) return true;

        Class clazz = bean.getClass();
        if (isProxyBean(bean)) clazz = AopUtils.getTargetClass(bean);

        String beanClassName = clazz.getName();

        for (String pkg : annotationPackages) {
            if (beanClassName.startsWith(pkg)) return true;
        }
        return false;
    }

    public void setPackage(String annotationPackage) {
        this.annotationPackage = annotationPackage;
        this.annotationPackages = (annotationPackage == null || annotationPackage.length() == 0) ?
                null : annotationPackage.split(COMMA_SPLIT_PATTERN);
    }

    private boolean isProxyBean(Object bean) {
        return AopUtils.isAopProxy(bean);
    }
}

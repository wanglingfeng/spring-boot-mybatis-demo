package com.lfwang.demo.orika.autoconfigure;

import ma.glasnost.orika.Converter;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

public class OrikaMapper extends ConfigurableMapper implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private MapperFactory mapperFactory;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

        addCustomSpringMappers();
        addCustomSpringConverters();
    }

    @Override
    protected void configure(MapperFactory factory) {
        super.configure(factory);

        this.mapperFactory = factory;
    }

    /**
     * Scans the application context and registers all Mappers found in it.
     */
    private void addCustomSpringMappers() {
        final Map<String, Mapper> mappers = applicationContext.getBeansOfType(Mapper.class);

        mappers.values().forEach(this::addMapper);
    }

    private void addMapper(Mapper<?, ?> mapper) {
        this.mapperFactory.registerMapper(mapper);
    }

    /**
     * Scans the application context and registers all Converters found in it.
     */
    private void addCustomSpringConverters() {
        final Map<String, Converter> converters = applicationContext.getBeansOfType(Converter.class);
        converters.values().forEach(this::addConverter);
    }

    private void addConverter(Converter<?, ?> converter) {
        this.mapperFactory.getConverterFactory().registerConverter(converter);
    }
}

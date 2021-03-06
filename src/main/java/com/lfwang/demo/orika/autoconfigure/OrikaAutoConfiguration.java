package com.lfwang.demo.orika.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({OrikaCustomProperties.class})
public class OrikaAutoConfiguration {

    private final OrikaCustomProperties customProperties;

    public OrikaAutoConfiguration(OrikaCustomProperties customProperties) {
        this.customProperties = customProperties;
    }

    @Bean
    @ConditionalOnProperty(prefix = "orika", name = "enabled", matchIfMissing = true, havingValue = "true")
    public OrikaMapper mapper() {
        System.out.println("create orika");
        System.out.println("message is: " + customProperties.getMessage());

        return new OrikaMapper();
    }
}

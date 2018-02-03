package com.lfwang.demo.orika.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "orika", name = "enabled", matchIfMissing = true, havingValue = "true")
@EnableConfigurationProperties({OrikaCustomProperties.class})
public class OrikaAutoConfiguration {

    private final OrikaCustomProperties customProperties;

    public OrikaAutoConfiguration(OrikaCustomProperties customProperties) {
        this.customProperties = customProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public OrikaMapper mapper() {
        System.out.println("create orika");
        System.out.println("message is: " + customProperties.getMessage());

        return new OrikaMapper();
    }
}

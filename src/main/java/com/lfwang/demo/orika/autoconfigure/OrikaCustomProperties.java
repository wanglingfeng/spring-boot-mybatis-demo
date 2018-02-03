package com.lfwang.demo.orika.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "orika.custom")
@Data
public class OrikaCustomProperties {

    private String message;
}

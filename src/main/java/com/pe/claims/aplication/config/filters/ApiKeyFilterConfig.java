package com.pe.claims.aplication.config.filters;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.filters.apikey")
public class ApiKeyFilterConfig {

    private String pathPrefix;
}
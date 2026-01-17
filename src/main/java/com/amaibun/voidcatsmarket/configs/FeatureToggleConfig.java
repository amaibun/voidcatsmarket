package com.amaibun.voidcatsmarket.configs;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "feature")
@Getter
@Setter
public class FeatureToggleConfig {

    private Map<String, FeatureProperties> toggles = new HashMap<>();

    @Getter
    @Setter
    public static class FeatureProperties {
        private boolean enabled;
    }
}


package com.amaibun.voidcatsmarket.toggle;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.amaibun.voidcatsmarket.configs.FeatureToggleConfig;
import com.amaibun.voidcatsmarket.configs.FeatureToggleConfig.FeatureProperties;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeatureToggleService {

    private final FeatureToggleConfig config;

    public boolean isEnabled(String featureName) {
        return Optional.ofNullable(config.getToggles().get(featureName))
                .map(FeatureProperties::isEnabled)
                .orElse(false);
    }
}


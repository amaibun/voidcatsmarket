package com.amaibun.voidcatsmarket.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amaibun.voidcatsmarket.toggle.FeatureToggle;

@Service
public class CosmoCatService {

    @FeatureToggle("cosmocats")
    public List<String> getCosmoCats() {
        return List.of("Luna", "Nebula", "Orion");
    }
}


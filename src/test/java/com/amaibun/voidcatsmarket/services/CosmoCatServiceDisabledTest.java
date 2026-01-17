package com.amaibun.voidcatsmarket.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.amaibun.voidcatsmarket.exceptions.FeatureNotAvailableException;
import com.amaibun.voidcatsmarket.services.impl.CosmoCatService;

@SpringBootTest(properties = "feature.toggles.cosmocats.enabled=false")
@ActiveProfiles("test")
class CosmoCatServiceDisabledTest {

    @Autowired
    private CosmoCatService cosmoCatService;

    @Test
    void shouldThrowExceptionWhenFeatureDisabled() {
        assertThrows(
                FeatureNotAvailableException.class,
                () -> cosmoCatService.getCosmoCats()
        );
    }
}


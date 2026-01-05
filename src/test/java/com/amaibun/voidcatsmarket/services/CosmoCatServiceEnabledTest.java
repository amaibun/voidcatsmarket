package com.amaibun.voidcatsmarket.services;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.amaibun.voidcatsmarket.services.impl.CosmoCatService;

@SpringBootTest(properties = "feature.toggles.cosmocats.enabled=true")
@ActiveProfiles("test")
class CosmoCatServiceEnabledTest {

    @Autowired
    private CosmoCatService cosmoCatService;

    @Test
    void shouldReturnCatsWhenFeatureEnabled() {
        List<String> cats = cosmoCatService.getCosmoCats();

        assertEquals(3, cats.size());
    }
}


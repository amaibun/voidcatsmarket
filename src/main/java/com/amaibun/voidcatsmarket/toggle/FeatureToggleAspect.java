package com.amaibun.voidcatsmarket.toggle;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.amaibun.voidcatsmarket.exceptions.FeatureNotAvailableException;

import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class FeatureToggleAspect {

    private final FeatureToggleService featureToggleService;

    @Around("@annotation(featureToggle)")
    public Object checkFeatureToggle(
            ProceedingJoinPoint joinPoint,
            FeatureToggle featureToggle
    ) throws Throwable {

        String featureName = featureToggle.value();

        if (featureToggleService.isEnabled(featureName)) {
            return joinPoint.proceed();
        }

        throw new FeatureNotAvailableException(featureName);
    }
}


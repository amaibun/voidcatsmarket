package com.amaibun.voidcatsmarket.exceptions;

public class FeatureNotAvailableException extends RuntimeException {
    public FeatureNotAvailableException(String feature) {
        super("Feature '" + feature + "' is not enabled");
    }
}


package com.amaibun.voidcatsmarket.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CosmicWordsValidator implements ConstraintValidator<CosmicWords, String> {

  private static final String[] COSMIC_WORDS = {
    "universe", "galaxy", "star", "planet", "black hole"
  };

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null || value.isEmpty()) {
      return false;
    }

    for (String cosmicWord : COSMIC_WORDS) {
      if (value.toLowerCase().contains(cosmicWord)) {
        return true;
      }
    }

    return false;
  }
}

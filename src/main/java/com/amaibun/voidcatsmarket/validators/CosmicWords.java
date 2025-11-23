package com.amaibun.voidcatsmarket.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = CosmicWordsValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CosmicWords {
    String message() default "The field must contain at least one cosmic word.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

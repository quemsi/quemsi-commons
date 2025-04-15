package com.quemsi.commons.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = ValidPasswordValidator.class)
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface ValidPassword {

    String message() default "invalid-password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


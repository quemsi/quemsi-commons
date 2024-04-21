package com.biddflux.commons.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { FieldsEqualityValidator.class })
public @interface FieldsEquality {

    String message() default "{FieldsEquality.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String firstFieldName();

    String secondFieldName();

    @Target({ TYPE, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    public @interface List {
        FieldsEquality[] value();
    }
}

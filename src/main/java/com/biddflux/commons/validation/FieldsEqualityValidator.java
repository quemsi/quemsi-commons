package com.biddflux.commons.validation;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

public class FieldsEqualityValidator implements ConstraintValidator<FieldsEquality, Object> {

    private static final Logger log = LoggerFactory.getLogger(FieldsEqualityValidator.class);

    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(FieldsEquality constraintAnnotation) {
        firstFieldName = constraintAnnotation.firstFieldName();
        secondFieldName = constraintAnnotation.secondFieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null)
            return true;

        try {
            Class<?> clazz = value.getClass();

            Field firstField = ReflectionUtils.findField(clazz, firstFieldName);
            if(firstField == null) {
            	context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate());
                return false;
            }
            firstField.setAccessible(true);
            Object first = firstField.get(value);

            Field secondField = ReflectionUtils.findField(clazz, secondFieldName);
            if(secondField == null) {
            	context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate());
                return false;
            }
            secondField.setAccessible(true);
            Object second = secondField.get(value);

            if (first != second && first != null && !first.equals(second)) {
                    ConstraintViolationBuilder cvb = context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate());
                    cvb.addPropertyNode(secondFieldName);
                return false;
            }
        } catch (Exception e) {
            log.error("Cannot validate fileds equality in '" + value + "'!", e);
            return false;
        }

        return true;
    }
}
package com.roy.spring.validation;

import org.apache.commons.validator.routines.EmailValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ValidEmailImpl implements ConstraintValidator<ValidEmail,String> {
    private int min;

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
        min = constraintAnnotation.min();
    }

    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if(email.length()<min){
            return false;
        }
        if (!EmailValidator.getInstance(false).isValid(email)){
            return false;
        }

        return true;
    }
}

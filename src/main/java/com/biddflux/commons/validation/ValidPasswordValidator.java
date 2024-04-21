package com.biddflux.commons.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class ValidPasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword arg0) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        
    	if(password != null) {
	    	String regex = "^(?=.*[0-9])"
	                       + "(?=.*[a-z])(?=.*[A-Z])"
//	                       + "(?=.*[@#$%^&+=])"
	                       + "(?=\\S+$).{3,100}$";
	        
	        Pattern p = Pattern.compile(regex);
	  
	        Matcher m = p.matcher(password);
	  
	        if(m.matches()) {
	        	return true;
	        }
	    }
        String messageTemplate = "invalid-password";
        context.buildConstraintViolationWithTemplate(messageTemplate)
            .addConstraintViolation()
            .disableDefaultConstraintViolation();
        return false;
    }
}

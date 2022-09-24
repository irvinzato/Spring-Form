package com.bolsadeideas.springboot.form.app.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RequeridoPersonalizadoValidador implements ConstraintValidator<RequeridoPersonalizado, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if( value == null || value.isEmpty() || value.isBlank() ) {
			return false;
		}
	
		return true;
	}

}

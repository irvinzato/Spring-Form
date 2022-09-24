package com.bolsadeideas.springboot.form.app.validations;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bolsadeideas.springboot.form.app.models.domain.Usuario;

//Clase personalizada para validar errores, puedo validar muchos campos(OTRA FORMA DE VALIDAR)
@Component
public class UsuarioValidador implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Usuario user = (Usuario) target;
		
		//Hay muchas funciones para validar que no sea vacio, que no tenga espacios vacios, etc.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.usuario.name"); //error, campo y mensaje de properties
		
		if( user.getIdentifier().matches("[0-9]{2}[.][0-9]{3}[.][0-9]{3}[-][A-Z]{1}")  == false ) {
			errors.rejectValue("identifier", "Pattern.usuario.identifier");
		}

	}

}

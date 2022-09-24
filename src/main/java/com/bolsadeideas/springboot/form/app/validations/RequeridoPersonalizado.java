package com.bolsadeideas.springboot.form.app.validations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = RequeridoPersonalizadoValidador.class)	//Enlace a clase validadora
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface RequeridoPersonalizado {
	
	String message() default "Campo requerido, validado con anotacion personalizada V-649";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}

package com.bolsadeideas.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bolsadeideas.springboot.form.app.services.RolService;

//Eligo los valores que quiero pasar como resultado de mis checkboxes
//Por cada Rol que se seleciona se aplica este editor
@Component
public class RolesEditor extends PropertyEditorSupport{

	@Autowired
	private RolService rolService;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			Integer id = Integer.parseInt(text);
			setValue(rolService.byId(id));	//Paso todo el ROL
		}catch( NumberFormatException e ) {
			setValue(null);
		}
	}

	
	
}

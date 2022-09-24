package com.bolsadeideas.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

//Filtro para trabajar campos del formulario, los paso con modificacion que quiera darles
public class NombreMayusculaEditor extends PropertyEditorSupport{

	@Override
	public void setAsText(String text) throws IllegalArgumentException {

		setValue(text.toUpperCase().trim());
	}

	
	
}

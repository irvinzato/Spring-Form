package com.bolsadeideas.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bolsadeideas.springboot.form.app.services.PaisService;

//Clase utilizada para recibir el id del pais y posteriormente regresar todo el objeto Pais V-661	
@Component
public class PaisPropertyEditor extends PropertyEditorSupport{
	
	@Autowired
	private PaisService paisService;

	@Override
	public void setAsText(String idString) throws IllegalArgumentException {
		try {
			Integer id = Integer.parseInt(idString);
			this.setValue(paisService.byId(id));	//Obtengo todos los atributos de Pais
		}catch(NumberFormatException e) {
			this.setValue(null);
		}		
	}

	
	
}

package com.bolsadeideas.springboot.form.app.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.form.app.models.domain.Pais;

@Service	//Registro en contenedor de Spring
public class PaisServiceImp implements PaisService{

	private List<Pais> listCountries;
	
	public PaisServiceImp() {
		this.listCountries = Arrays.asList(new Pais( 1, "MX", "Mexico"), 
				new Pais( 2, "ES", "España"), 
				new Pais( 3, "AR", "Argentina"), 
				new Pais( 4, "PE", "Peru"), 
				new Pais( 5, "COL", "Colombia"), 
				new Pais( 6, "CA", "Canada"), 
				new Pais( 7, "EU", "Estados Unidos"));
	}

	@Override
	public List<Pais> toList() {

		return listCountries;
	}

	@Override
	public Pais byId(Integer id) {
		Pais result = null;
		for(Pais country: this.listCountries) {
			if( country.getId() == id ) {
				result = country;
				break;
			}
		}
		return result;
	}

}

package com.bolsadeideas.springboot.form.app.services;

import java.util.List;

import com.bolsadeideas.springboot.form.app.models.domain.Rol;

public interface RolService {
	
	public List<Rol> toList();
	
	public Rol byId(Integer id);

}

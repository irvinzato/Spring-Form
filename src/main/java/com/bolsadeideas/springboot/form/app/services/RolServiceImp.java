package com.bolsadeideas.springboot.form.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.form.app.models.domain.Rol;

@Service
public class RolServiceImp implements RolService{

	private List<Rol> listRoles;
	
	public RolServiceImp() {
		listRoles = new ArrayList<>();
		listRoles.add(new Rol(1, "ROLE_ADMIN", "Administrador"));
		listRoles.add(new Rol(2, "ROLE_USER", "Usuario"));
		listRoles.add(new Rol(3, "ROLE_MODERATOR", "Moderador"));
	}

	@Override
	public List<Rol> toList() {
		return listRoles;
	}

	@Override
	public Rol byId(Integer id) {
		Rol result = null;
		for( Rol role: listRoles ) {
			if( role.getId() == id ) {
				result = role;
				break;
			}
		}
		return result;
	}

}

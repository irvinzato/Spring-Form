package com.bolsadeideas.springboot.form.app.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.form.app.editors.NombreMayusculaEditor;
import com.bolsadeideas.springboot.form.app.editors.RolesEditor;
import com.bolsadeideas.springboot.form.app.models.domain.Pais;
import com.bolsadeideas.springboot.form.app.models.domain.Rol;
import com.bolsadeideas.springboot.form.app.models.domain.Usuario;
import com.bolsadeideas.springboot.form.app.services.PaisService;
import com.bolsadeideas.springboot.form.app.services.RolService;
import com.bolsadeideas.springboot.form.app.validations.UsuarioValidador;

@Controller
@SessionAttributes("usuario")  //Para mantener todos los datos del objeto aunque no los pase en el formulario, en este caso quiero mantener el identificador del objeo Usuario
public class FormController {
	
	//Inyecto componente service de Pais V-660
	@Autowired
	private PaisService paisService;
	
	//Añado Roles como objeto V-664
	@Autowired
	private RolService rolService;
	
	//Validador personalizado(Otra manera de validar V646)
	@Autowired
	private UsuarioValidador validatorUser;
	
	@Autowired
	private RolesEditor rolesEditor;
	
	//Valida de forma transparente Spring con mi validador pero las anotaciones que validan se pierden si uso "setValidator"
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validatorUser);
		
		//Puedo utilizar CustomDateEditor para trabajar con validaciones de fecha, otra opcion a usar @DateTimeFormat V-654
		
		//binder.registerCustomEditor(String.class, new NombreMayusculaEditor());	//Creé un filtro para los campos String cuando sean pasados de Usuario, puede ser de un campo en especifico
		binder.registerCustomEditor(String.class, "name", new NombreMayusculaEditor());		//Para un campo en especifico del formulario
		
		//Puedo utilizar mi "PaisPropertyEditor" para mandar todo el objeto y no solo el un atributo V-661
		binder.registerCustomEditor(Rol.class, "roles", rolesEditor);		//Para pasar atributos de roles(Va de uno en uno) V-665
	}
	
	//Creado para pasar la lista de paises a mis vistas en List
	@ModelAttribute("paises")
	public List<String> countries() {
		return Arrays.asList("Mexico", "España", "Argentina", "Peru", "Colombia", "Canada", "EU");
	}
	
	//Creado para pasar la lista de paises a mis vistas en Map
	@ModelAttribute("paisesMap")
	public Map<String, String> countriesMap() {
		Map<String, String> countries = new HashMap<String, String>();
		countries.put("MX", "Mexico");
		countries.put("ES", "España");
		countries.put("AR", "Argentina");
		countries.put("PE", "Peru");
		countries.put("COL", "Colombia");
		countries.put("CA", "Canada");
		countries.put("EU", "Estados Unidos");
		
		return countries;
	}
	
	//Modifique las listas a Objeto de Pais
	@ModelAttribute("listaPaises")
	public List<Pais> countriesObj() {
		return paisService.toList();
	}
	
	@ModelAttribute("listaRolesString")
	public List<String> rolesString() {
		return Arrays.asList("ROLE_ADMIN", "ROLE_USER", "ROLE_MODERADOR");
	}
	
	@ModelAttribute("listaRolesMap")
	public Map<String, String> rolesMap() {
		Map<String, String> listRoles = new HashMap<String, String>();
		listRoles.put("ROLE_ADMIN", "Administrador");
		listRoles.put("ROLE_USER", "Usuario");
		listRoles.put("ROLE_MODERADOR", "Moderador");
		
		return listRoles;
	}
	
	@ModelAttribute("listaRoles")
	public List<Rol> rolesObj() {
		return rolService.toList();
	}
	
	@GetMapping("/form")
	public String form(Model model) {
		Usuario user = new Usuario();
		user.setName("Por defecto");
		user.setLastName("Ejemplo V-640");
		user.setIdentifier("12.123.233-K");	//No lo quiero en el formulario pero si en el resultado
		user.setHability(true);
		user.setSecretValue("Algun valor secreto, otra opcion para mantener valores que no se pueden modificar pero se deben mantener, @SessionAttributes es otra");
		
		model.addAttribute("usuario", user);
		
		return "form";
	}
	
	@PostMapping("/form")						
	public String processForm(@Valid Usuario user ,BindingResult result, Model model) { //Importante el orden, varias ventajas de usarlo asi, mismo nombre que los "name" en inputs
	//Puedo reducir mas colocando "Usuario user"(como coinciden los atributos a los name de la vista se pueblan) V.636 en parametros del método y quito los RequestParam
		
		//validatorUser.validate(user, result);	//Validador personalizado, con InitBinder ya no ocupo esta linea
		
		if( result.hasErrors() ) {
			/* Cambio drastico para reducir codiGo y utilizar mas thymeleaf V.639
			 Map<String, String> errors = new HashMap<>();
			result.getFieldErrors().forEach( err ->  {
				errors.put(err.getField(), "El campo ".concat(err.getField().concat(" ").concat(err.getDefaultMessage())));
			});
			model.addAttribute("error", errors); */
			return "form";
		}
		
		//model.addAttribute("username", user);	//Mejor lo paso con anotacion @SessionAttibutes
		
		return "redirect:/ver";		//Redireccion para que el usuario al hacer refresh no se guarden duplicados los datos del POST(Evitar datos duplicados en DB) V-670
	}
	
	@GetMapping("/ver")
	public String look(@SessionAttribute(name = "usuario", required = false) Usuario user, Model model, SessionStatus status ) {
		if( user == null ) {
			return "redirect:/form";
		}
		
		status.setComplete();	//Para completar el proceso y eliminar el objeto usuario de la sesion
		
		return "resultado";
	}
	

}

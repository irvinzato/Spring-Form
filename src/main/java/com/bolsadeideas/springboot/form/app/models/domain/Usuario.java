package com.bolsadeideas.springboot.form.app.models.domain;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class Usuario {
	
	//@Pattern(regexp = "[0-9]{2}[.][0-9]{3}[.][0-9]{3}[-][A-Z]{1}")		//Incluyo validacion con expresión regular, puedo ponerlo en formulario o tenerlo en la session, dos opciones diferentes
	private String identifier;				//Este campo no se puede modificar y debe persistir por eso no tiene anotacion V-641
	
	//@NotEmpty(message = "Para personalizar mensaje en caso de error")   //Otra forma de validar con clase personalizada
	private String name;
	
	@NotEmpty(message = "Tambien puedo personalizar con properties V-643 y 644")
	private String lastName;

	@NotBlank
	@Size(min = 3, max = 8)
	private String username;
	
	@NotEmpty
	private String password;
	
	@NotEmpty //Tambien sirve para listas
	@Email
	private String email;
	
	@NotNull
	@Min(10)
	@Max(50)
	private Integer account;
	
	@NotNull
	//@Past		//Valida que sea una fecha pasada
	//@Future	//Valida que sea una fecha futura
	@DateTimeFormat(pattern = "yyyy-MM-dd")	//Es el patron que utiliza HTML5 para input Date
	private Date dateOfBirth;

	@Valid		//Para que Valide campos de objeto Pais
	private Pais country;
	
	@NotEmpty
	private List<Rol> roles;
	
	private Boolean hability;
	
	@NotEmpty
	private String gender;
	
	private String secretValue;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Integer getAccount() {
		return account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Pais getCountry() {
		return country;
	}

	public void setCountry(Pais country) {
		this.country = country;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public Boolean getHability() {
		return hability;
	}

	public void setHability(Boolean hability) {
		this.hability = hability;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSecretValue() {
		return secretValue;
	}

	public void setSecretValue(String secretValue) {
		this.secretValue = secretValue;
	}
	
	
	
}

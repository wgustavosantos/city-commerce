package com.ufpa.projetointegrado.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ufpa.projetointegrado.domain.model.enums.Perfil;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	public void addPerfil(Perfil perfil) {
		/* pega o perfil e chama getCod para armazenar o inteiro correspondente ao perfil */
		perfis.add(perfil.getCod());
	}

	public Usuario(Long id, String email, String password, String name) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
	}

	public Usuario() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// @JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public Set<Perfil> getPerfis() {

		/*
		 * Para cada elemento x em perfis, retorna o elemento Perfil(cliente ou admin)
		 * de Perfil.toEnum e converte para um Set
		 */
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n Nome: ");
		builder.append(name);
		builder.append("\n Email: ");
		builder.append(email);
		builder.append("\n Senha: ");
		builder.append(password);
		return builder.toString();
	}	
	
	

}

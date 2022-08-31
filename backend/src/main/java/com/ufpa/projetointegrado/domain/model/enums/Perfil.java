package com.ufpa.projetointegrado.domain.model.enums;

public enum Perfil {

	USUARIO(1, "ROLE_USUARIO"), ADMIN(2, "ROLE_ADMIN"); /* Prefixo ROLE é exigido pelo spring security */

	private Integer cod;
	private String descricao;

	private Perfil(Integer cod, String descricao) { // construtor de enum privado

		this.cod = cod;
		this.descricao = descricao;

	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Perfil toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for (Perfil tipoCliente : Perfil.values()) {
			if(tipoCliente.getCod().equals(cod)) {
				return tipoCliente;
			}
		}


		throw new IllegalArgumentException("Id inválido: " + cod);

	}

}

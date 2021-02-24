package net.qualitibank.model;

import javax.persistence.Entity;

@Entity
public class Funcionario extends Usuario {

	public Funcionario() {
	}

	public Funcionario(String nome, String email) {
		super(nome, email);
	}	
}

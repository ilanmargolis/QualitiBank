package net.qualitibank.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Cliente extends Usuario {

	private String cpf;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Conta> contas;

	public Cliente() {
		super();
	}

	public Cliente(String cpf, List<Conta> contas) {
		super();
		this.cpf = cpf;
		this.contas = contas;
	}

	public Cliente(String nome, String email) {
		super(nome, email);
	}

	public Cliente(Integer id, String nome, String email, List<Conta> contas) {
		super(id, nome, email, null);
		this.contas = contas;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}
}

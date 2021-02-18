package net.qualitibank.model;

import javax.persistence.Entity;

@Entity
public class Poupanca extends Conta {
	private static double rentabilidade;

	public Poupanca(double rentabilidade) {
		super();
		Poupanca.rentabilidade = rentabilidade;
	}

	public Poupanca(String numero, Cliente cliente) {
		super(numero, cliente);
	}

	public double getRentabilidade() {
		return rentabilidade;
	}

	public void setRentabilidade(double rentabilidade) {
		Poupanca.rentabilidade = rentabilidade;
	}

	public void renderJuros() {
		if (Poupanca.rentabilidade > 0) {
			super.creditar(super.getSaldo() * Poupanca.rentabilidade / 100);
		}
	}
}

package net.qualitibank.model;

import javax.persistence.Entity;

@Entity
public class Bonificada extends Conta{
	private static final double TAXA_BONUS = .01;
	private double bonus;
	
	public Bonificada(String numero, Cliente cliente) {
		super(numero, cliente);
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public void renderBonus() {
		super.creditar(this.bonus);
		this.bonus = 0;
	}
	
	@Override
	public void creditar(double valor) {
		super.creditar(valor);
		this.bonus += (valor * TAXA_BONUS);
	}
}

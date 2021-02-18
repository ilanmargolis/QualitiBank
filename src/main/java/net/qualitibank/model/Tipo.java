package net.qualitibank.model;

public enum Tipo {
	VIP(1), CLASS(2), ESPECIAL(3);

	private final int valor;

	Tipo(int valorOpcao) {
		valor = valorOpcao;
	}

	public int getValor() {
		return valor;
	}
}

package net.qualitibank.model;

public enum Operacao {
	CREDITO(1), DEBITO(2), TRANSFERENCIA(3);

	private final int valor;

	Operacao(int valorOpcao) {
		valor = valorOpcao;
	}

	public int getValor() {
		return valor;
	}
}

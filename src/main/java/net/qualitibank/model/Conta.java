package net.qualitibank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String numero;
	private Double saldo;
	private final String tipo = this.getClass().getTypeName().replace("net.qualitibank.model.", "");
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;

	public Conta() {
	}

	public Conta(String numero, Cliente cliente) {
		super();
		this.numero = numero;
		this.cliente = cliente;

		this.saldo = 0.0;
	}

	public Conta(Integer id, String numero, Double saldo, Cliente cliente) {
		super();
		this.id = id;
		this.numero = numero;
		this.saldo = saldo;
		this.cliente = cliente;
	}

	public void creditar(double valor) {
		this.saldo += valor;
	}

	public boolean debitar(double valor) {
		if (this.getSaldo() >= valor) {
			this.saldo -= valor;
			return true;
		} else {
			System.out.println("Saldo insuficiente");
			return false;
		}
	}

	public void transferir(Conta conta, double valor) {
		if (this.debitar(valor)) {
			conta.creditar(valor);
		} else {
			System.out.println("Saldo da conta de origem é insuficiente para o valor a ser transferido");
		}
	}
		
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	public String getTipo() {
		return tipo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Conta [id=" + id + ", numero=" + numero + ", saldo=" + saldo + ", cliente=" + cliente + "]";
	}
}

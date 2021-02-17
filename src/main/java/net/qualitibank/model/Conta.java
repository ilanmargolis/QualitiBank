package net.qualitibank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String numero;
	private Double saldo;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;

	public Conta() {
	}

	public Conta(String numero, Cliente cliente) {
		super();
		this.numero = numero;
		this.saldo = 0.0;
		this.cliente = cliente;
	}

	public Conta(Integer id, String numero, Double saldo, Cliente cliente) {
		super();
		this.id = id;
		this.numero = numero;
		this.saldo = saldo;
		this.cliente = cliente;
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

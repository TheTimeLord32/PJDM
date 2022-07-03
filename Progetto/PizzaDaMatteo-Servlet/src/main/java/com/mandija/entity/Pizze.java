package com.mandija.entity;

public class Pizze {
	private String nome;
	
	public Pizze(String nome) {
		super();
		this.nome = nome;
	}
	
	public String getNome() { return nome; }
	
	@Override
	public String toString() {
		return "Pizze [nome=" + nome + "]";
	}
}

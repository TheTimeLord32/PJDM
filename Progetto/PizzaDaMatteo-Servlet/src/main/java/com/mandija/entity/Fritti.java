package com.mandija.entity;

public class Fritti {
	private String nome;
	
	public Fritti(String nome) {
		super();
		this.nome = nome;
	}
	
	public String getNome() { return nome; }
	
	@Override
	public String toString() {
		return "Fritti [nome=" + nome + "]";
	}	
}

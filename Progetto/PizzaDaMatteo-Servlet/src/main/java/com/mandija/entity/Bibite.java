package com.mandija.entity;

public class Bibite {
	private String nome;
	
	public Bibite(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() { return nome; }

	@Override
	public String toString() {
		return "Bibite [nome=" + nome + "]";
	}
}

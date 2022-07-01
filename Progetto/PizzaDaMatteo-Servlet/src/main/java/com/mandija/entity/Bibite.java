package com.mandija.entity;

public class Bibite {
	private String nome;
	private float prezzo;
	
	public Bibite(String nome, float prezzo) {
		super();
		this.nome = nome;
		this.prezzo = prezzo;
	}

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	public float getPrezzo() { return prezzo; }
	public void setPrezzo(float prezzo) { this.prezzo = prezzo; }

	@Override
	public String toString() {
		return "Bibite [nome=" + nome + ", prezzo=" + prezzo + "]";
	}
}

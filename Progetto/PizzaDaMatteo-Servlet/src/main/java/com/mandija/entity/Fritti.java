package com.mandija.entity;

public class Fritti {
	private String nome;
	private float prezzo;
	
	public Fritti(String nome, float prezzo) {
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
		return "Fritti [nome=" + nome + ", prezzo=" + prezzo + "]";
	}	
}

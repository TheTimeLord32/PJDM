package com.mandija.entity;

public class Pizze {
	private String nome;
	private float prezzo;
	
	public Pizze(String nome, float prezzo) {
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
		return "Pizze [nome=" + nome + ", prezzo=" + prezzo + "]";
	}
}

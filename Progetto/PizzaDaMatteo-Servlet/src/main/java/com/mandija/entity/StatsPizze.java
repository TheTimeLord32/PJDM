package com.mandija.entity;

public class StatsPizze {

	private String numPizza, nomePizza;

	public StatsPizze(String nomePizza, String numPizza) {
		super();
		this.nomePizza = nomePizza;
		this.numPizza = numPizza;
	}

	public String getNumPizza() { return numPizza; }
	public String getNomePizza() { return nomePizza; }

	@Override
	public String toString() { return "StatsPizze [numPizza=" + numPizza + ", nomePizza=" + nomePizza + "]"; }
}

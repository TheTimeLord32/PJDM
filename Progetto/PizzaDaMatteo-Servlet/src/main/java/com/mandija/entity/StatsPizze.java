package com.mandija.entity;

public class StatsPizze {

	private String numPizza, nomePizza;

	public StatsPizze(String numPizza, String nomePizza) {
		super();
		this.numPizza = numPizza;
		this.nomePizza = nomePizza;
	}

	public String getNumPizza() { return numPizza; }
	public String getNomePizza() { return nomePizza; }

	@Override
	public String toString() { return "StatsPizze [numPizza=" + numPizza + ", nomePizza=" + nomePizza + "]"; }
}

package com.mandija.entity;

public class Stats {

	private String orario, countOrario, nomePizza, numPizza;

	public Stats(String orario, String countOrario, String nomePizza, String numPizza) {
		super();
		this.orario = orario;
		this.countOrario = countOrario;
		this.nomePizza = nomePizza;
		this.numPizza = numPizza;
	}

	public String getOrario() {
		return orario;
	}

	public void setOrario(String orario) {
		this.orario = orario;
	}

	public String getCountOrario() {
		return countOrario;
	}

	public void setCountOrario(String countOrario) {
		this.countOrario = countOrario;
	}

	public String getNomePizza() {
		return nomePizza;
	}

	public void setNomePizza(String nomePizza) {
		this.nomePizza = nomePizza;
	}

	public String getNumPizza() {
		return numPizza;
	}

	public void setNumPizza(String numPizza) {
		this.numPizza = numPizza;
	}

	@Override
	public String toString() {
		return "Stats [orario=" + orario + ", countOrario=" + countOrario + ", nomePizza=" + nomePizza + ", numPizza="
				+ numPizza + "]";
	}	

	
}

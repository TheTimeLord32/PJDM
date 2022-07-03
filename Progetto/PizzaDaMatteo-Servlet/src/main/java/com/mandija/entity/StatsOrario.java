package com.mandija.entity;

public class StatsOrario {

	private String orario, countOrario;

	public StatsOrario(String orario, String countOrario) {
		super();
		this.orario = orario;
		this.countOrario = countOrario;
	}

	public String getOrario() { return orario; }
	public String getCountOrario() { return countOrario; }

	@Override
	public String toString() { return "Stats [orario=" + orario + ", countOrario=" + countOrario + "]"; }	
}

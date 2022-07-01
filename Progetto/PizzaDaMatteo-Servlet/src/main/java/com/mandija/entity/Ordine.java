package com.mandija.entity;

public class Ordine {
	private int id_ordine;
	private String modalita, nome_cliente, orario, recapito, indirizzo;
	private boolean confermato;
	
	public Ordine(int id_ordine, String modalita, String nome_cliente, String orario, String recapito, String indirizzo, boolean confermato) {
		super();
		this.id_ordine = id_ordine;
		this.modalita = modalita;
		this.nome_cliente = nome_cliente;
		this.orario = orario;
		this.recapito = recapito;
		this.indirizzo = indirizzo;
		this.confermato = confermato;
	}

	public int getId_ordine() { return id_ordine; }
	public String getModalita() { return modalita; }
	public String getNomeCliente() { return nome_cliente; }
	public String getOrario() { return orario; }
	public String getRecapito() { return recapito; }
	public String getIndirizzo() { return indirizzo; }
	public boolean getConfermato() { return confermato; }

	@Override
	public String toString() {
		return "Ordine [id_ordine=" + id_ordine + ", modalita=" + modalita + ", nomeCliente=" + nome_cliente + ", orario=" + orario + ", recapito=" + recapito + ", indirizzo=" + indirizzo + ", confermato=" + confermato + "]";
	}
	
}

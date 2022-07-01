package com.mandija.pizzadamatteo.entity;

public class ListaOrdineElemento {
    int id_ordine;
    String nome_cliente, orario, recapito, indirizzo;

    public ListaOrdineElemento(int id_ordine, String nome_cliente, String orario, String recapito, String indirizzo) {
        this.id_ordine = id_ordine;
        this.nome_cliente = nome_cliente;
        this.orario = orario;
        this.recapito = recapito;
        this.indirizzo = indirizzo;
    }

    public int getId_ordine() { return id_ordine; }
    public String getNome_cliente() { return nome_cliente; }
    public String getOrario() { return orario; }
    public String getRecapito() { return recapito; }
    public String getIndirizzo() { return indirizzo; }
}

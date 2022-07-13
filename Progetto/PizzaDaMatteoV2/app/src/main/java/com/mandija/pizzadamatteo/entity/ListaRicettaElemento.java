package com.mandija.pizzadamatteo.entity;

public class ListaRicettaElemento {
    String codice_pizza, codice_ingrediente;
    int quantita;

    public ListaRicettaElemento(String codice_pizza, String codice_ingrediente, int quantita) {
        this.codice_pizza = codice_pizza;
        this.codice_ingrediente = codice_ingrediente;
        this.quantita = quantita;
    }

    public String getCodicePizza() { return codice_pizza; }
    public String getCodiceIngrediente() { return codice_ingrediente; }
    public int getQuantita() { return quantita; }
}

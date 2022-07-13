package com.mandija.entity;

public class Ricetta {
    String codice_pizza, codice_ingrediente;
    int quantita;

    public Ricetta(String codice_pizza, String codice_ingrediente, int quantita) {
        this.codice_pizza = codice_pizza;
        this.codice_ingrediente = codice_ingrediente;
        this.quantita = quantita;
    }

    public String getCodice_pizza() { return codice_pizza; }
    public String getCodice_ingrediente() { return codice_ingrediente; }
    public int getQuantita() { return quantita; }

    @Override
    public String toString() {
        return "Ricetta{" + "codice_pizza='" + codice_pizza + ", codice_ingrediente='" + codice_ingrediente +  ", quantita=" + quantita + '}';
    }
}

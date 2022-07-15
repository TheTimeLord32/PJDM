package com.mandija.entity;

public class Ordine2Pizza {
    private int id_riga, id_ordine;
    private String pizza;
    private boolean confermato;

    public Ordine2Pizza(int id_riga, int id_ordine, String pizza, boolean confermato) {
        this.id_riga = id_riga;
        this.id_ordine = id_ordine;
        this.pizza = pizza;
        this.confermato = confermato;
    }

    public int getId_riga() { return id_riga; }
    public int getId_ordine() { return id_ordine; }
    public String getPizza() { return pizza; }
    public boolean getConfermato() { return confermato; }
}

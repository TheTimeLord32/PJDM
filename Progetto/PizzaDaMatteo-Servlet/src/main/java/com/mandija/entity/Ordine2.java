package com.mandija.entity;

public class Ordine2 {
    private int id_riga, id_ordine;
    private boolean confermato;
    private String pizza, fritti, bibite;

    public Ordine2(int id_riga, int id_ordine, String pizza, String fritti, String bibite,  boolean confermato) {
        this.id_riga = id_riga;
        this.id_ordine = id_ordine;
        this.confermato = confermato;
        this.pizza = pizza;
        this.fritti = fritti;
        this.bibite = bibite;
    }

    public int getId_riga() { return id_riga; }
    public int getId_ordine() { return id_ordine; }
    public boolean getConfermato() { return confermato; }
    public String getPizza() { return pizza; }
    public String getFritti() { return fritti; }
    public String getBibite() { return bibite; }
}

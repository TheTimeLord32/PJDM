package com.mandija.pizzadamatteo.entity;

public class ListaOrdine2Elemento {
    String pizza, fritti, bibite;

    public ListaOrdine2Elemento(String pizza, String fritti, String bibite) {
        this.pizza = pizza;
        this.fritti = fritti;
        this.bibite = bibite;
    }

    public String getPizza() {return pizza;}
    public String getFritti() {return fritti;}
    public String getBibite() {return bibite;}
}

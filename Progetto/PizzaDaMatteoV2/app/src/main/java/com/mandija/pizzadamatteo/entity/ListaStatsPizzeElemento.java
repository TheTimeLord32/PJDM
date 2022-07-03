package com.mandija.pizzadamatteo.entity;

public class ListaStatsPizzeElemento {
    String statsPizze;
    int statsPizzeNum;

    public ListaStatsPizzeElemento(int statsPizzeNum, String statsPizze) {
        this.statsPizze = statsPizze;
        this.statsPizzeNum = statsPizzeNum;
    }

    public String getStatsPizze() { return statsPizze; }
    public int getStatsPizzeNum() { return statsPizzeNum; }
}
package com.mandija.pizzadamatteo.entity;

public class ListaStatsOrarioElemento {
    String statsOrario;
    int statsOrarioNum;

    public ListaStatsOrarioElemento(String statsOrario, int statsOrarioNum) {
        this.statsOrario = statsOrario;
        this.statsOrarioNum = statsOrarioNum;
    }

    public String getStatsOrario() { return statsOrario; }
    public int getStatsOrarioNum() { return statsOrarioNum; }
}

package com.mandija.dao;

import java.util.ArrayList;
import java.sql.SQLException;

import com.mandija.entity.*;

public interface PizzaDaMatteoDAO {
	ArrayList<Pizze> loadPizze() throws SQLException;
	ArrayList<Fritti> loadFritti() throws SQLException;
	ArrayList<Bibite> loadBibite() throws SQLException;
	ArrayList<Ordine> loadOrdine() throws SQLException;
	ArrayList<Ordine2> loadOrdine2(int id_ordine) throws SQLException;
	void inserisciOrdine(Ordine ordine) throws SQLException;
	int inserisciOrdine2(Ordine2 ordine2) throws SQLException;
	void deleteOrdine(int id_ordine) throws SQLException;
	
	ArrayList<StatsOrario> getStatsOrario() throws SQLException;
	ArrayList<StatsPizze> getStatsPizze() throws SQLException;
	int getLastOrdine() throws SQLException;
    void updateConto(int id_ordine) throws SQLException;
	ArrayList<Ricetta> getRicetta(int id_ordine) throws SQLException;
}

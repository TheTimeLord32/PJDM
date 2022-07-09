package com.mandija.dao;

import java.util.ArrayList;
import java.sql.SQLException;

import com.mandija.entity.*;

public interface PizzaDaMatteoDAO {
	public ArrayList<Pizze> loadPizze() throws SQLException;
	public ArrayList<Fritti> loadFritti() throws SQLException;
	public ArrayList<Bibite> loadBibite() throws SQLException;

	public ArrayList<Ordine> loadOrdine() throws SQLException;
	public ArrayList<Ordine1> loadOrdine1(int id_ordine) throws SQLException;
	
	public int inserisciOrdine(Ordine ordine) throws SQLException;
	public int inserisciOrdine1(Ordine1 ordine1) throws SQLException;
	
	public void deleteOrdine(int id_ordine) throws SQLException;
	
	public ArrayList<StatsOrario> getStatsOrario() throws SQLException;
	public ArrayList<StatsPizze> getStatsPizze() throws SQLException;
}

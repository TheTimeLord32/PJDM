package com.mandija.dao;

import java.util.ArrayList;
import java.sql.SQLException;

import com.mandija.entity.*;

public interface PizzaDaMatteoDAO {
	public ArrayList<Pizze> loadPizze() throws SQLException;
	public ArrayList<Fritti> loadFritti() throws SQLException;
	public ArrayList<Bibite> loadBibite() throws SQLException;
	// public ArrayList<Ingredienti> loadIngredienti() throws SQLException;
	
	public ArrayList<Ordine> loadOrdine() throws SQLException;
	public ArrayList<Ordine1> loadOrdine1(int id_ordine) throws SQLException;
	
	public int inserisciOrdine(Ordine ordine) throws SQLException;
	public int inserisciOrdine1(Ordine1 ordine1) throws SQLException;
	
	public void deleteOrdine(int id_ordine) throws SQLException;
	public void deleteOrdine1(int id_ordine) throws SQLException;
	
	public ArrayList<Stats> getStats() throws SQLException;
}

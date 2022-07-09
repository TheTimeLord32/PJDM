package com.mandija.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;

import com.mandija.entity.*;

public class PizzaDaMatteoDAO_JDBC implements PizzaDaMatteoDAO{

	private Connection conn;
	
	// connessione al database
	public PizzaDaMatteoDAO_JDBC(String ip, String port, String dbName, String userName, String userPwd) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+dbName+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", userName, userPwd);
	}

	// chiusura connessione database
	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			Enumeration<Driver> enumDrivers = DriverManager.getDrivers();
			while (enumDrivers.hasMoreElements()) {
				Driver driver = enumDrivers.nextElement();
				DriverManager.deregisterDriver(driver);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// elenco pizze
	@Override
	public ArrayList<Pizze> loadPizze() throws SQLException {
		ArrayList<Pizze>res = new ArrayList<Pizze>();
		String query = "SELECT nome FROM pizza;";
		
		Statement stmt = conn.createStatement();
		ResultSet rsPizze = stmt.executeQuery(query);
		
		while(rsPizze.next()) {
			String nome = rsPizze.getString(1);
		
			res.add(new Pizze(nome));
		}
		rsPizze.close();
		stmt.close();
		
		return res;
	}

	// elenco fritti
	@Override
	public ArrayList<Fritti> loadFritti() throws SQLException {
		ArrayList<Fritti>res = new ArrayList<Fritti>();
		String query = "SELECT nome FROM fritti;";
		
		Statement stmt = conn.createStatement();
		ResultSet rsetFritti = stmt.executeQuery(query);
		
		while(rsetFritti.next()) {
			String nome = rsetFritti.getString(1);
		
			res.add(new Fritti(nome));
		}
		rsetFritti.close();
		stmt.close();
		
		return res;
	}

	// elenco bevande
	@Override
	public ArrayList<Bibite> loadBibite() throws SQLException {
		ArrayList<Bibite>res = new ArrayList<Bibite>();
		String query = "SELECT nome FROM bibite;";
		
		Statement stmt = conn.createStatement();
		ResultSet rsetBibite = stmt.executeQuery(query);
		
		while(rsetBibite.next()) {
			String nome = rsetBibite.getString(1);
			
			res.add(new Bibite(nome));
		}
		rsetBibite.close();
		stmt.close();
		
		return res;
	}

	// elenco ordine
	@Override
	public ArrayList<Ordine> loadOrdine() throws SQLException {
		ArrayList<Ordine>res = new ArrayList<Ordine>();
		String query = "SELECT * FROM ordine WHERE confermato != 1 ORDER BY ordine.id_ordine ASC;";
		
		Statement stmt = conn.createStatement();
		ResultSet rsetOrdine = stmt.executeQuery(query);
		
		while(rsetOrdine.next()) {
			int id_ordine = rsetOrdine.getInt(1);
			String nomeCliente = rsetOrdine.getString(2);
			String orario = rsetOrdine.getString(3);
			String recapito = rsetOrdine.getString(4);
			String indirizzo = rsetOrdine.getString(5);
			boolean confermato = rsetOrdine.getBoolean(6);
			
			res.add(new Ordine(id_ordine, nomeCliente, orario, recapito, indirizzo, confermato));
		}
		rsetOrdine.close();
		stmt.close();
		
		return res;
	}

	// elenco ordine1
	@Override
	public ArrayList<Ordine1> loadOrdine1(int id_ordine) throws SQLException {
		ArrayList<Ordine1>res = new ArrayList<Ordine1>();
		String query = "SELECT * FROM ordine1 WHERE id_ordine = " + id_ordine+ " AND confermato != true;";
		
		Statement stmt = conn.createStatement();
		ResultSet rsetOrdine1 = stmt.executeQuery(query);
		
		while(rsetOrdine1.next()) {
			String pizza1 = rsetOrdine1.getString(2);
			String pizza2 = rsetOrdine1.getString(3);
			String pizza3 = rsetOrdine1.getString(4);
			String pizza4 = rsetOrdine1.getString(5);
			String pizza5 = rsetOrdine1.getString(6);

			String fritti1 = rsetOrdine1.getString(7);
			String fritti2 = rsetOrdine1.getString(8);
			String fritti3 = rsetOrdine1.getString(9);
			String fritti4 = rsetOrdine1.getString(10);
			String fritto5 = rsetOrdine1.getString(11);

			String bibite1 = rsetOrdine1.getString(12);
			String bibite2 = rsetOrdine1.getString(13);
			String bibite3 = rsetOrdine1.getString(14);
			String bibite4 = rsetOrdine1.getString(15);
			String bibite5 = rsetOrdine1.getString(16);

			boolean confermato = rsetOrdine1.getBoolean(17);
			
			res.add(new Ordine1(id_ordine, pizza1, pizza2, pizza3, pizza4, pizza5, fritti1, fritti2, fritti3, fritti4, fritto5, bibite1, bibite2, bibite3, bibite4, bibite5,  confermato));
		}
		rsetOrdine1.close();
		stmt.close();
		
		return res;
	}

	// inserimento ordine
	@Override
	public int inserisciOrdine(Ordine ordine) throws SQLException {
		String insert = "INSERT INTO ordine VALUES (?, ?, ?, ?, ?, ?);";
		
		PreparedStatement pstmt = conn.prepareStatement(insert);
		
		pstmt.setInt(1, ordine.getId_ordine());
		pstmt.setString(2, ordine.getNomeCliente());
		pstmt.setString(3, ordine.getOrario());
		pstmt.setString(4, ordine.getRecapito());
		pstmt.setString(5, ordine.getIndirizzo());
		pstmt.setBoolean(6, ordine.getConfermato());
		
		int affectedRows = pstmt.executeUpdate();
		pstmt.close();
		
		return affectedRows;		
	}

	// inserimento ordine1
	@Override
	public int inserisciOrdine1(Ordine1 ordine1) throws SQLException {
		String insert = "INSERT INTO ordine1 VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		PreparedStatement pstmt = conn.prepareStatement(insert);

		pstmt.setInt(1, ordine1.getId_ordine());
		pstmt.setString(2, ordine1.getPizza1());
		pstmt.setString(3, ordine1.getPizza2());
		pstmt.setString(4, ordine1.getPizza3());
		pstmt.setString(5, ordine1.getPizza4());
		pstmt.setString(6, ordine1.getPizza5());
		pstmt.setString(7, ordine1.getFritti1());
		pstmt.setString(8, ordine1.getFritti2());
		pstmt.setString(9, ordine1.getFritti3());
		pstmt.setString(10, ordine1.getFritti4());
		pstmt.setString(11, ordine1.getFritti5());
		pstmt.setString(12, ordine1.getBibite1());
		pstmt.setString(13, ordine1.getBibite2());
		pstmt.setString(14, ordine1.getBibite3());
		pstmt.setString(15, ordine1.getBibite4());
		pstmt.setString(16, ordine1.getBibite5());
		pstmt.setBoolean(17, ordine1.getConfermato());

		int affectedRows = pstmt.executeUpdate();
		pstmt.close();
		
		String update = "UPDATE pizza SET quantita = quantita+1 WHERE nome=? OR nome=? OR nome=? OR nome=? OR nome=?;";
		// query per effettuare le statistiche

		PreparedStatement up = conn.prepareStatement(update);
		up.setString(1, ordine1.getPizza1());
		up.setString(2, ordine1.getPizza2());
		up.setString(3, ordine1.getPizza3());
		up.setString(4, ordine1.getPizza4());
		up.setString(5, ordine1.getPizza5());
		
		affectedRows = up.executeUpdate();
		up.close();
		
		/* scalare ingredienti pizze */
		
		return affectedRows;
	}

	// archiviazione ordine
	@Override
	public void deleteOrdine(int id_ordine) throws SQLException {
		String delete = "UPDATE ordine SET confermato = 1 WHERE id_ordine =" + id_ordine + ";";
		String delete1 = "UPDATE ordine1 SET confermato = 1 WHERE id_ordine =" + id_ordine + ";";
		
		Statement statement = conn.createStatement();
		statement.execute(delete);
		statement.close();
		
		Statement statement1 = conn.createStatement();
		statement1.execute(delete1);
		statement1.close();
	}

	// elenco statistiche orario + pizza
	@Override
	public ArrayList<StatsOrario> getStatsOrario() throws SQLException {
		ArrayList<StatsOrario> res = new ArrayList<StatsOrario>();
		String queryOrario = "SELECT orario, count(orario) AS countOrario FROM ordine GROUP BY orario;";
		
		Statement stmtStatsOrario = conn.createStatement();
		ResultSet rsetStatsOrario = stmtStatsOrario.executeQuery(queryOrario);
	
		while(rsetStatsOrario.next()) {
			String orario = rsetStatsOrario.getString(1);
			String countOrario = rsetStatsOrario.getString(2);
			res.add(new StatsOrario(orario, countOrario));
		}

		rsetStatsOrario.close();
		stmtStatsOrario.close();
		return res;		
		
	}

	@Override
	public ArrayList<StatsPizze> getStatsPizze() throws SQLException {
		ArrayList<StatsPizze> res = new ArrayList<StatsPizze>();
		String queryPizze = "SELECT nome, quantita FROM pizza WHERE quantita != 0 and nome != '';";
		
		Statement stmtStatsPizze = conn.createStatement();
		ResultSet rsetStatsPizze = stmtStatsPizze.executeQuery(queryPizze);

		while(rsetStatsPizze.next()) {
			String quantita = rsetStatsPizze.getString(1);
			String nome = rsetStatsPizze.getString(2);
			res.add(new StatsPizze(nome, quantita));
		}
		rsetStatsPizze.close();
		stmtStatsPizze.close();
		return res;
	}	
}

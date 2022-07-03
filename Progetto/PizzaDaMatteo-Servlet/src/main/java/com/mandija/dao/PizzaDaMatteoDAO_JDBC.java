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
			// float prezzo = rsPizze.getFloat(2);
		
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
		String query = "SELECT * FROM fritti WHERE fritti.nome != 'Scegli i fritti' AND fritti.prezzo != '0' ORDER BY fritti.nome ASC;";
		
		Statement stmt = conn.createStatement();
		ResultSet rsetFritti = stmt.executeQuery(query);
		
		while(rsetFritti.next()) {
			String nome = rsetFritti.getString(1);
			float prezzo = rsetFritti.getFloat(2);
		
			res.add(new Fritti(nome, prezzo));
		}
		rsetFritti.close();
		stmt.close();
		
		return res;
	}

	// elenco bevande
	@Override
	public ArrayList<Bibite> loadBibite() throws SQLException {
		ArrayList<Bibite>res = new ArrayList<Bibite>();
		String query = "SELECT * FROM bibite WHERE bibite.nome != 'Scegli la bibita' AND bibite.prezzo != '0' ORDER BY bibite.nome ASC;";
		
		Statement stmt = conn.createStatement();
		ResultSet rsetBibite = stmt.executeQuery(query);
		
		while(rsetBibite.next()) {
			String nome = rsetBibite.getString(1);
			float prezzo = rsetBibite.getFloat(2);
		
			res.add(new Bibite(nome, prezzo));
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
			String modalita = rsetOrdine.getString(2);
			String nomeCliente = rsetOrdine.getString(3);
			String orario = rsetOrdine.getString(4);
			String recapito = rsetOrdine.getString(5);
			String indirizzo = rsetOrdine.getString(6);
			boolean confermato = rsetOrdine.getBoolean(7);
			
			res.add(new Ordine(id_ordine, modalita, nomeCliente, orario, recapito, indirizzo, confermato));
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
			int numPizze = rsetOrdine1.getInt(2);
			int numFritti = rsetOrdine1.getInt(8);
			int numBibite = rsetOrdine1.getInt(14);

			String pizza1 = rsetOrdine1.getString(3);
			String pizza2 = rsetOrdine1.getString(4);
			String pizza3 = rsetOrdine1.getString(5);
			String pizza4 = rsetOrdine1.getString(6);
			String pizza5 = rsetOrdine1.getString(7);

			String fritto1 = rsetOrdine1.getString(9);
			String fritto2 = rsetOrdine1.getString(10);
			String fritto3 = rsetOrdine1.getString(11);
			String fritto4 = rsetOrdine1.getString(12);
			String fritto5 = rsetOrdine1.getString(13);

			String bibita1 = rsetOrdine1.getString(15);
			String bibita2 = rsetOrdine1.getString(16);
			String bibita3 = rsetOrdine1.getString(17);
			String bibita4 = rsetOrdine1.getString(18);
			String bibita5 = rsetOrdine1.getString(19);
			
			String note = rsetOrdine1.getString(20);
			int conto = rsetOrdine1.getInt(21);
			boolean confermato = rsetOrdine1.getBoolean(22);
			
			res.add(new Ordine1(id_ordine, numPizze, numFritti, numBibite, conto, pizza1, pizza2, pizza3, pizza4, pizza5, fritto1, fritto2, fritto3, fritto4, fritto5, bibita1, bibita2, bibita3, bibita4, bibita5, note, confermato));
		}
		rsetOrdine1.close();
		stmt.close();
		
		return res;
	}

	// inserimento ordine
	@Override
	public int inserisciOrdine(Ordine ordine) throws SQLException {
		String insert = "INSERT INTO ordine VALUES (?, ?, ?, ?, ?, ?, ?);";
		
		PreparedStatement pstmt = conn.prepareStatement(insert);
		
		pstmt.setInt(1, ordine.getId_ordine());
		pstmt.setString(2, ordine.getModalita());
		pstmt.setString(3, ordine.getNomeCliente());
		pstmt.setString(4, ordine.getOrario());
		pstmt.setString(5, ordine.getRecapito());
		pstmt.setString(6, ordine.getIndirizzo());
		pstmt.setBoolean(7, ordine.getConfermato());
		
		int affectedRows = pstmt.executeUpdate();
		pstmt.close();
		
		return affectedRows;		
	}

	// inserimento ordine1
	@Override
	public int inserisciOrdine1(Ordine1 ordine1) throws SQLException {
		String insert = "INSERT INTO ordine1 VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		PreparedStatement pstmt = conn.prepareStatement(insert);

		pstmt.setInt(1, ordine1.getId_ordine());
		pstmt.setInt(2, ordine1.getnum_pizze());
		pstmt.setString(3, ordine1.getPizza1());
		pstmt.setString(4, ordine1.getPizza2());
		pstmt.setString(5, ordine1.getPizza3());
		pstmt.setString(6, ordine1.getPizza4());
		pstmt.setString(7, ordine1.getPizza5());
		pstmt.setInt(8, ordine1.getnum_fritti());
		pstmt.setString(9, ordine1.getFritto1());
		pstmt.setString(10, ordine1.getFritto2());
		pstmt.setString(11, ordine1.getFritto3());
		pstmt.setString(12, ordine1.getFritto4());
		pstmt.setString(13, ordine1.getFritto5());
		pstmt.setInt(14, ordine1.getnum_bibite());
		pstmt.setString(15, ordine1.getBibita1());
		pstmt.setString(16, ordine1.getBibita2());
		pstmt.setString(17, ordine1.getBibita3());
		pstmt.setString(18, ordine1.getBibita4());
		pstmt.setString(19, ordine1.getBibita5());
		pstmt.setString(20, ordine1.getNote());
		pstmt.setInt(21, ordine1.getConto());
		pstmt.setBoolean(22, ordine1.getConfermato());

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
		String delete = "UPDATE ordine SET confermato = 1 WHERE id_ordine =\"" + id_ordine + "\";";

		Statement statement = conn.createStatement();
		statement.execute(delete);
		statement.close();		
	}

	// archiviazione ordine1
	@Override
	public void deleteOrdine1(int id_ordine) throws SQLException {
		String delete = "UPDATE ordine1 SET confermato = 1 WHERE id_ordine =\"" + id_ordine + "\";";
		
		Statement statement = conn.createStatement();
		statement.execute(delete);
		statement.close();
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
		String queryPizze = "SELECT nome, quantita FROM pizza WHERE quantita != 0;";
		
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

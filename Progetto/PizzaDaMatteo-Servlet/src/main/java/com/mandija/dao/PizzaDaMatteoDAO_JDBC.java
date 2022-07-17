package com.mandija.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;

import com.mandija.entity.*;

public class PizzaDaMatteoDAO_JDBC implements PizzaDaMatteoDAO{

	private final Connection conn;
	
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
		ArrayList<Pizze>res = new ArrayList<>();
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
		ArrayList<Fritti>res = new ArrayList<>();
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
		ArrayList<Bibite>res = new ArrayList<>();
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
		ArrayList<Ordine>res = new ArrayList<>();
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
			float conto = rsetOrdine.getFloat(7);
			
			res.add(new Ordine(id_ordine, nomeCliente, orario, recapito, indirizzo, confermato, conto));
		}
		rsetOrdine.close();
		stmt.close();
		
		return res;
	}

	@Override
	public ArrayList<Ordine2> loadOrdine2(int id_ordine) throws SQLException {
		ArrayList<Ordine2>res = new ArrayList<>();
		String query = "SELECT * FROM ordine2 WHERE id_ordine = " + id_ordine+ " AND confermato != true;";

		Statement stmt = conn.createStatement();
		ResultSet rsetOrdine2 = stmt.executeQuery(query);

		while(rsetOrdine2.next()) {
			int id_riga = Integer.parseInt(rsetOrdine2.getString(1));
			int id_ordine1 = Integer.parseInt(rsetOrdine2.getString(2));
			String pizza = rsetOrdine2.getString(3);
			String fritti = rsetOrdine2.getString(4);
			String bibite = rsetOrdine2.getString(5);
			boolean confermato = rsetOrdine2.getBoolean(6);

			res.add(new Ordine2(id_riga, id_ordine1, pizza, fritti, bibite, confermato));
		}
		rsetOrdine2.close();
		stmt.close();
		return res;
	}

	// inserimento ordine
	@Override
	public void inserisciOrdine(Ordine ordine) throws SQLException {
		String insert = "INSERT INTO ordine VALUES (?, ?, ?, ?, ?, ?, ?);";
		
		PreparedStatement pstmt = conn.prepareStatement(insert);
		
		pstmt.setInt(1, ordine.getId_ordine());
		pstmt.setString(2, ordine.getNomeCliente());
		pstmt.setString(3, ordine.getOrario());
		pstmt.setString(4, ordine.getRecapito());
		pstmt.setString(5, ordine.getIndirizzo());
		pstmt.setBoolean(6, ordine.getConfermato());
		pstmt.setFloat(7, ordine.getConto());
		
		pstmt.executeUpdate();
		pstmt.close();
	}

	@Override
	public int inserisciOrdine2(Ordine2 ordine2) throws SQLException {
		String insert = "INSERT INTO ordine2 VALUES (?, ?, ?, ?, ?, ?);";

		PreparedStatement pstmt = conn.prepareStatement(insert);

		pstmt.setInt(1, ordine2.getId_riga());
		pstmt.setInt(2, ordine2.getId_ordine());
		pstmt.setString(3, ordine2.getPizza());
		pstmt.setString(4, ordine2.getFritti());
		pstmt.setString(5, ordine2.getBibite());
		pstmt.setBoolean(6, ordine2.getConfermato());

		int affectedRows = pstmt.executeUpdate();
		pstmt.close();

		return affectedRows;
	}

	// archiviazione ordine
	@Override
	public void deleteOrdine(int id_ordine) throws SQLException {
		String delete = "UPDATE ordine SET confermato = 1 WHERE id_ordine =" + id_ordine + ";";
		String delete1 = "UPDATE ordine2 SET confermato = 1 WHERE id_ordine =" + id_ordine + ";";
		
		Statement statement = conn.createStatement();
		statement.execute(delete);
		statement.close();
		
		Statement statement1 = conn.createStatement();
		statement1.execute(delete1);
		statement1.close();
	}

	// calcolo il conto dell'ordine
	@Override
	public void updateConto(int id_ordine) throws SQLException {
		String update = "update ordine set conto = (select sum(pizza.prezzo + fritti.prezzo + bibite.prezzo) as tot from ordine2, pizza, fritti, bibite where pizza.nome = ordine2.pizza and fritti.nome = ordine2.fritti and bibite.nome = ordine2.bibite and id_ordine = " + id_ordine + ") where id_ordine = " + id_ordine + ";";

		Statement statement = conn.createStatement();
		statement.execute(update);
		statement.close();
	}

	// elenco statistiche orario + pizza
	@Override
	public ArrayList<StatsOrario> getStatsOrario() throws SQLException {
		ArrayList<StatsOrario> res = new ArrayList<>();
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
		ArrayList<StatsPizze> res = new ArrayList<>();
		String queryPizze = "SELECT pizza, cntPizza FROM stats_pizza;";
		
		Statement stmtStatsPizze = conn.createStatement();
		ResultSet rsetStatsPizze = stmtStatsPizze.executeQuery(queryPizze);

		while(rsetStatsPizze.next()) {
			String pizza = rsetStatsPizze.getString(1);
			String cntPizza = rsetStatsPizze.getString(2);
			res.add(new StatsPizze(pizza, cntPizza));
		}
		rsetStatsPizze.close();
		stmtStatsPizze.close();
		return res;
	}

	public int getLastOrdine() throws SQLException {
		int res = 0;
		String query = "SELECT id_ordine FROM ordine ORDER BY id_ordine DESC LIMIT 1;";
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery(query);
		if(rset.next()) {
			res = rset.getInt(1);
		}
		rset.close();
		stmt.close();
		return res;
	}

	@Override
	public ArrayList<Ricetta> getRicetta(int id_ordine) throws SQLException {
		ArrayList<Ricetta> res = new ArrayList<>();
		String query = "select ricetta.* from ricetta, ordine2 where  ricetta.codice_pizza = ordine2.pizza and id_ordine=" + id_ordine + " and ordine2.confermato != true;";

		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery(query);

		while (rset.next()){
			String codice_pizza = rset.getString(1);
			String codice_ingrediente = rset.getString(2);
			int quantita = rset.getInt(3);
			res.add(new Ricetta(codice_pizza, codice_ingrediente, quantita));
		}
		rset.close();
		stmt.close();
		return res;
	}
}

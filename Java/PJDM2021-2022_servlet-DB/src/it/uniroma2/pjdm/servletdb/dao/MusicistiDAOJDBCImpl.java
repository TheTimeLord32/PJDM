package it.uniroma2.pjdm.servletdb.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;

import it.uniroma2.pjdm.servletdb.entity.Musicista;

public class MusicistiDAOJDBCImpl implements MusicistiDAO {

	private Connection conn;

	public MusicistiDAOJDBCImpl(String ip, String port, String dbName, String userName, String pwd) {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection(
					"jdbc:mysql://" + ip + ":" + port + "/" + dbName
							+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					userName, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Musicista loadMusicista(String name) {

		String query = "SELECT ssn, name FROM musicians_ WHERE name = \"" + name + "\"";

		try {
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(query);

			Musicista res = null;

			while (rset.next()) {
				String nome = rset.getString(2);
				int ssn = rset.getInt(1);

				res = new Musicista(nome, ssn);

				break;
			}

			rset.close();
			stmt.close();

			return res;

		} catch (SQLException e) {

			e.printStackTrace();

			return null;
		}

	}

	@Override
	public Musicista loadMusicistaByID(int inputSsn) {

		String query = "SELECT ssn, name FROM musicians_ WHERE ssn  = \"" + inputSsn + "\"";

		try {
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(query);

			Musicista res = null;

			while (rset.next()) {
				String nome = rset.getString(2);
				int ssn = rset.getInt(1);

				res = new Musicista(nome, ssn);

				break;
			}

			rset.close();
			stmt.close();

			return res;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public int insertMusicista(Musicista musicista) {
		String SQL = "INSERT INTO musicians_(ssn,name) " + "VALUES(?,?)";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);

			pstmt.setInt(1, musicista.getSsn());
			pstmt.setString(2, musicista.getName());

			int affectedRows = pstmt.executeUpdate();

			return affectedRows;

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}

	}

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

	
	@Override
	public ArrayList<Musicista> loadTuttiMusicisti() {
		String query = "SELECT ssn, name FROM musicians_ ";

		try {
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(query);

			ArrayList<Musicista> res = new ArrayList<>();

			while (rset.next()) {
				String nome = rset.getString(2);
				int ssn = rset.getInt(1);

				Musicista m = new Musicista(nome, ssn);
				res.add(m);
			}

			rset.close();
			stmt.close();

			return res;

		} catch (SQLException e) {

			e.printStackTrace();

			return null;
		}

	}

	@Override
	public boolean deleteMusicista(Musicista musicista) {

		String name = musicista.getName();

		String SQL = "DELETE FROM musicians_ WHERE name='" + name + "'";

		try {
			Statement statement = conn.createStatement();
			statement.execute(SQL);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

}
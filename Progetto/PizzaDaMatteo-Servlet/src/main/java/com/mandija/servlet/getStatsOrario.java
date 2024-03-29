package com.mandija.servlet;

import com.mandija.dao.*;
import com.mandija.entity.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;

/**
 * Servlet implementation class getStats
 */
public class getStatsOrario extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PizzaDaMatteoDAO_JDBC dao;

    public getStatsOrario() { super(); }

	public void init() throws ServletException {
    	String ip = getServletContext().getInitParameter("ip");
		String port = getServletContext().getInitParameter("port");
		String dbName = getServletContext().getInitParameter("dbName");
		String userName = getServletContext().getInitParameter("userName");
		String password = getServletContext().getInitParameter("userPwd");

		System.out.print("PizzaDaMatteo - Stats Orario. Opening DB connection... \n");
		
		try {
			dao = new PizzaDaMatteoDAO_JDBC(ip, port, dbName, userName, password);
			System.out.println("DONE.");
		}
		catch(SQLException | ClassNotFoundException e) {
			System.out.println("PizzaDaMatteo - Stats Orario. Error opening DB connection. \n");
			e.printStackTrace();
		}
    }
    
    public void destroy() {
		System.out.print("PizzaDaMatteo - Stats Orario. Closing DB connection... \n");
		dao.closeConnection();
		System.out.println("DONE.");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try{
			ArrayList<StatsOrario> allStatsOrario = dao.getStatsOrario();
			if (allStatsOrario.toString().equals("[]"))
			{
				response.setStatus(204);
				System.out.println("Inventario statistiche orario vuoto \n");
			} else {
				response.setStatus(200);
				System.out.println("Statistiche orario caricate \n");
				JSONArray allStatsOrarioJson = new JSONArray(allStatsOrario);
				out.print(allStatsOrarioJson);
				out.flush();
			}
		} catch (SQLException e) {
			response.setStatus(400);
			System.out.println("Errore: " + e.getMessage());
			out.println("Errore: " + e.getMessage());
		}
	}
}

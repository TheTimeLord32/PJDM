package com.mandija.servlet;

import com.mandija.dao.PizzaDaMatteoDAO_JDBC;
import com.mandija.entity.StatsPizze;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;

/**
 * Servlet implementation class getStatsPizze
 */
public class getStatsPizze extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PizzaDaMatteoDAO_JDBC dao;
       
    public getStatsPizze() { super(); }
    
    public void init() throws ServletException {
    	String ip = getServletContext().getInitParameter("ip");
		String port = getServletContext().getInitParameter("port");
		String dbName = getServletContext().getInitParameter("dbName");
		String userName = getServletContext().getInitParameter("userName");
		String password = getServletContext().getInitParameter("userPwd");

		System.out.print("PizzaDaMatteo - Stats Pizze. Opening DB connection... \n");
		
		try {
			dao = new PizzaDaMatteoDAO_JDBC(ip, port, dbName, userName, password);
		}
		catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("PizzaDaMatteo - Stats Pizze. Error opening DB connection. \n");
		}
		System.out.println("DONE.");
    }
    
    public void destroy() {
		System.out.print("PizzaDaMatteo - Stats Pizze. Closing DB connection... \n");
		dao.closeConnection();
		System.out.println("DONE.");
	}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try{
			ArrayList<StatsPizze> allStatsPizze = dao.getStatsPizze();
			if (allStatsPizze.toString().equals("[]"))
			{
				System.out.println("Inventario statistiche pizze vuoto");
				response.setStatus(400);
			} else {
				response.setStatus(200);
				JSONArray allStatsPizzeJson = new JSONArray(allStatsPizze);
				out.print(allStatsPizzeJson);
				out.flush();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

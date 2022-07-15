package com.mandija.servlet;

import com.mandija.dao.*;
import com.mandija.entity.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

import org.json.JSONArray;

/**
 * Servlet implementation class getProdotti
 */
public class getPizze extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PizzaDaMatteoDAO_JDBC dao;
    
    public getPizze() {
        super();
    }

    public void init() throws ServletException {
    	String ip = getServletContext().getInitParameter("ip");
		String port = getServletContext().getInitParameter("port");
		String dbName = getServletContext().getInitParameter("dbName");
		String userName = getServletContext().getInitParameter("userName");
		String password = getServletContext().getInitParameter("userPwd");

		System.out.print("PizzaDaMatteo - Pizze. Opening DB connection... \n");
		
		try {
			dao = new PizzaDaMatteoDAO_JDBC(ip, port, dbName, userName, password);
			System.out.println("DONE.");
		}
		catch(SQLException | ClassNotFoundException e) {
			System.out.println("PizzaDaMatteo - Pizze. Errore connessione DB. \n");
			e.printStackTrace();
		}
    }
    
    public void destroy() {
		System.out.print("PizzaDaMatteo - Pizze. Closing DB connection... \n");
		dao.closeConnection();
		System.out.println("DONE.");
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		try{
			ArrayList<Pizze> allPizze = dao.loadPizze();
			if (allPizze.toString().equals("[]"))
			{
				System.out.println("Inventario pizze vuoto");
				response.setStatus(400);
			} else {
				response.setStatus(200);
				JSONArray allPizzeJson = new JSONArray(allPizze);
				out.print(allPizzeJson);
				out.flush();
			}
		} catch (SQLException e) {
			response.setStatus(400);
			System.out.println("Errore: " + e.getMessage());
			out.println("Errore: " + e.getMessage());
		}		 
	}
}

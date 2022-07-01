package com.mandija.servlet;

import com.mandija.dao.*;
import com.mandija.entity.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;

/**
 * Servlet implementation class getStats
 */
public class getStats extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PizzaDaMatteoDAO_JDBC dao;

    public getStats() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init() throws ServletException {
    	String ip = getServletContext().getInitParameter("ip");
		String port = getServletContext().getInitParameter("port");
		String dbName = getServletContext().getInitParameter("dbName");
		String userName = getServletContext().getInitParameter("userName");
		String password = getServletContext().getInitParameter("userPwd");

		System.out.print("PizzaDaMatteo - Stats. Opening DB connection... \n");
		
		try {
			dao = new PizzaDaMatteoDAO_JDBC(ip, port, dbName, userName, password);
		}
		catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("DONE.");
    }
    
    public void destroy() {
		System.out.print("PizzaDaMatteo - Stats. Closing DB connection... \n");
		dao.closeConnection();
		System.out.println("DONE.");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String timeStats = request.getParameter("timeStats");

		 try{
			ArrayList<Stats> allStats = dao.getStats();
			JSONArray timeStatsJSON = new JSONArray(allStats);
			out.print(timeStatsJSON.toString());
			out.flush();
		} catch(SQLException err){
			err.printStackTrace();
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

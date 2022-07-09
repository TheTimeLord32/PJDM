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
 * Servlet implementation class getOrdine1
 */
public class getOrdine1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PizzaDaMatteoDAO_JDBC dao;
   
	public getOrdine1() {
        super();
    }
	
	public void init() throws ServletException {
    	String ip = getServletContext().getInitParameter("ip");
		String port = getServletContext().getInitParameter("port");
		String dbName = getServletContext().getInitParameter("dbName");
		String userName = getServletContext().getInitParameter("userName");
		String password = getServletContext().getInitParameter("userPwd");

		System.out.print("PizzaDaMatteo - Ordine1. Opening DB connection... \n");
		
		try {
			dao = new PizzaDaMatteoDAO_JDBC(ip, port, dbName, userName, password);
		}
		catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("DONE.");
    }
    
    public void destroy() {
		System.out.print("PizzaDaMatteo - Ordine1. Closing DB connection... \n");
		dao.closeConnection();
		System.out.println("DONE.");
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String id_ordine = request.getParameter("id_ordine");

		try{
			ArrayList<Ordine1> allOrdine1 = dao.loadOrdine1(Integer.parseInt(id_ordine));
			if (allOrdine1.toString().equals("[]"))
			{
				System.out.println("Ordine1 vuoto");
				response.setStatus(400);
			} else {
				response.setStatus(200);
				JSONArray allOrdine1Json = new JSONArray(allOrdine1);
				out.print(allOrdine1Json);
				out.flush();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String id_ordine = request.getParameter("id_ordine");
		String pizza1 = request.getParameter("pizza1");
		String pizza2 = request.getParameter("pizza2");
		String pizza3 = request.getParameter("pizza3");
		String pizza4 = request.getParameter("pizza4");
		String pizza5 = request.getParameter("pizza5");
		String fritti1 = request.getParameter("fritti1");
		String fritti2 = request.getParameter("fritti2");
		String fritti3 = request.getParameter("fritti3");
		String fritti4 = request.getParameter("fritti4");
		String fritti5 = request.getParameter("fritti5");
		String bibite1 = request.getParameter("bibite1");
		String bibite2 = request.getParameter("bibite2");
		String bibite3 = request.getParameter("bibite3");
		String bibite4 = request.getParameter("bibite4");
		String bibite5 = request.getParameter("bibite5");
		String confermato = request.getParameter("confermato");

		try {
			dao.inserisciOrdine1(new Ordine1(0, pizza1, pizza2, pizza3, pizza4, pizza5, fritti1, fritti2, fritti3, fritti4, fritti5, bibite1, bibite2, bibite3, bibite4, bibite5, false));
			response.setStatus(200);
			out.print("{\r\n"
					+ "	\"id_ordine\": \"" + id_ordine + "\",\r\n"
					+ "	\"pizza1\": \"" + pizza1 + "\",\r\n"
					+ "	\"pizza2\": \"" + pizza2 + "\",\r\n"
					+ "	\"pizza3\": \"" + pizza3 + "\",\r\n"
					+ "	\"pizza4\": \"" + pizza4 + "\",\r\n"
					+ "	\"pizza5\": \"" + pizza5 + "\",\r\n"
					+ "	\"fritti1\": \"" + fritti1 + "\",\r\n"
					+ "	\"fritti2\": \"" + fritti2 + "\",\r\n"
					+ "	\"fritti3\": \"" + fritti3 + "\",\r\n"
					+ "	\"fritti4\": \"" + fritti4 + "\",\r\n"
					+ "	\"fritti5\": \"" + fritti5 + "\",\r\n"
					+ "	\"bibite1\": \"" + bibite1 + "\",\r\n"
					+ "	\"bibite2\": \"" + bibite2 + "\",\r\n"
					+ "	\"bibite3\": \"" + bibite3 + "\",\r\n"
					+ "	\"bibite4\": \"" + bibite4 + "\",\r\n"
					+ "	\"bibite5\": \"" + bibite5 + "\",\r\n"
					+ "	\"confermato\": \"" + confermato + "\",\r\n"
					+ "}");
		} catch (SQLException err) {
			response.setStatus(505);
			out.print("{\r\n"
					+ "	\"error\": \"Ordine non inserito\"\r\n"
					+ "}");
			err.printStackTrace();
		}
	}

}

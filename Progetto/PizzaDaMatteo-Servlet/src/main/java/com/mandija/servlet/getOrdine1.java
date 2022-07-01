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
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String nomeOrdine1 = request.getParameter("nomeOrdine1");
		String id_ordine = request.getParameter("id_ordine");
		
		try {
			if(nomeOrdine1 != null) {
				ArrayList<Ordine1> allOrdine1 = dao.loadOrdine1(Integer.parseInt(id_ordine));
				JSONArray allOrdine1Json = new JSONArray(allOrdine1);
				out.print(allOrdine1Json.toString());
				out.flush();
			} else {
				ArrayList<Ordine1> allOrdine1 = dao.loadOrdine1(Integer.parseInt(id_ordine));
				JSONArray allOrdine1Json = new JSONArray(allOrdine1);
				out.print(allOrdine1Json.toString());
				out.flush();
			}
		}
		catch(SQLException err) {
			err.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String id_ordine = request.getParameter("id_ordine");
		String num_pizze = request.getParameter("num_pizze");
		String pizza1 = request.getParameter("pizza1");
		String pizza2 = request.getParameter("pizza2");
		String pizza3 = request.getParameter("pizza3");
		String pizza4 = request.getParameter("pizza4");
		String pizza5 = request.getParameter("pizza5");
		String num_fritti = request.getParameter("num_fritti");
		String fritti1 = request.getParameter("fritti1");
		String fritti2 = request.getParameter("fritti2");
		String fritti3 = request.getParameter("fritti3");
		String fritti4 = request.getParameter("fritti4");
		String fritti5 = request.getParameter("fritti5");
		String num_bibite = request.getParameter("num_bibite");
		String bibite1 = request.getParameter("bibite1");
		String bibite2 = request.getParameter("bibite2");
		String bibite3 = request.getParameter("bibite3");
		String bibite4 = request.getParameter("bibite4");
		String bibite5 = request.getParameter("bibite5");
		String note = request.getParameter("note");
		String conto = request.getParameter("conto");
		String confermato = request.getParameter("confermato");

		try {
			dao.inserisciOrdine1(new Ordine1(0, 0, 0, 0, 0, pizza1, pizza2, pizza3, pizza4, pizza5, fritti1, fritti2, fritti3, fritti4, fritti5, bibite1, bibite2, bibite3, bibite4, bibite5, note, false));
			response.setStatus(200);
			out.print("{\r\n"
					+ "	\"id_ordine\": \"" + id_ordine + "\",\r\n"
					+ "	\"num_pizze\": " + num_pizze + ",\r\n"
					+ "	\"pizza1\": \"" + pizza1 + "\",\r\n"
					+ "	\"pizza2\": \"" + pizza2 + "\",\r\n"
					+ "	\"pizza3\": \"" + pizza3 + "\",\r\n"
					+ "	\"pizza4\": \"" + pizza4 + "\",\r\n"
					+ "	\"pizza5\": \"" + pizza5 + "\",\r\n"
					+ "	\"num_fritti\": " + num_fritti + ",\r\n"
					+ "	\"fritti1\": \"" + fritti1 + "\",\r\n"
					+ "	\"fritti2\": \"" + fritti2 + "\",\r\n"
					+ "	\"fritti3\": \"" + fritti3 + "\",\r\n"
					+ "	\"fritti4\": \"" + fritti4 + "\",\r\n"
					+ "	\"fritti5\": \"" + fritti5 + "\",\r\n"
					+ "	\"num_bibite\": " + num_bibite + ",\r\n"
					+ "	\"bibite1\": \"" + bibite1 + "\",\r\n"
					+ "	\"bibite2\": \"" + bibite2 + "\",\r\n"
					+ "	\"bibite3\": \"" + bibite3 + "\",\r\n"
					+ "	\"bibite4\": \"" + bibite4 + "\",\r\n"
					+ "	\"bibite5\": \"" + bibite5 + "\",\r\n"
					+ "	\"note\": \"" + note + "\",\r\n"
					+ "	\"conto\": " + conto + "\r\n"
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

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String id_ordine = request.getParameter("id_ordine");
		try {
			dao.deleteOrdine1(Integer.parseInt(id_ordine));
			out.print("Ordine rimosso correttamente");
			out.flush();
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			response.setStatus(404);
			out.print("Ordine non rimosso correttamente");
			out.flush();
		}
	}
	
}

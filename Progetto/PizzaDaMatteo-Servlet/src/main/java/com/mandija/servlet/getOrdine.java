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

public class getOrdine extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PizzaDaMatteoDAO_JDBC dao;
   
	public getOrdine() {
        super();
    }
	
	public void init() throws ServletException {
    	String ip = getServletContext().getInitParameter("ip");
		String port = getServletContext().getInitParameter("port");
		String dbName = getServletContext().getInitParameter("dbName");
		String userName = getServletContext().getInitParameter("userName");
		String password = getServletContext().getInitParameter("userPwd");

		System.out.print("PizzaDaMatteo - Ordine. Opening DB connection... \n");
		
		try {
			dao = new PizzaDaMatteoDAO_JDBC(ip, port, dbName, userName, password);
			System.out.println("DONE.");
		}
		catch(SQLException | ClassNotFoundException e) {
			System.out.println("PizzaDaMatteo - Ordine. Errore connessione DB. \n");
			e.printStackTrace();
		}
    }
    
    public void destroy() {
		System.out.print("PizzaDaMatteo - Ordine. Closing DB connection... \n");
		dao.closeConnection();
		System.out.println("DONE.");
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try{
			ArrayList<Ordine> allOrdine = dao.loadOrdine();
			if (allOrdine.toString().equals("[]")) {
				response.setStatus(204);
				System.out.println("Ordine vuoto \n");
			} else {
				response.setStatus(200);
				System.out.println("Ordine caricato \n");
				JSONArray allOrdineJson = new JSONArray(allOrdine);
				out.print(allOrdineJson);
				out.flush();
			}
		} catch (SQLException e) {
			response.setStatus(400);
			System.out.println("Errore: " + e.getMessage());
			out.println("Errore: " + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String nome_cliente = request.getParameter("nome_cliente");
		String orario = request.getParameter("orario");
		String recapito = request.getParameter("recapito");
		String indirizzo = request.getParameter("indirizzo");

		try {
			if (!nome_cliente.equals("") && !orario.equals("") && !recapito.equals("") && !indirizzo.equals("")) {
				dao.inserisciOrdine(new Ordine(0, nome_cliente, orario, recapito, indirizzo, false, 0));
				response.setStatus(201);
				out.print("Ordine inserito correttamente \n");
				out.flush();
			}
		} catch (NullPointerException | SQLException e){
			response.setStatus(422);
			out.print("Campi vuoti o errati. Riprovare\n");
			e.printStackTrace();
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {
			int id_ordine = Integer.parseInt(request.getParameter("id_ordine"));
			if (id_ordine == Integer.parseInt(String.valueOf(id_ordine))) {
				dao.deleteOrdine(id_ordine);
				out.print("Ordine rimosso correttamente \n");
				out.flush();
			} else {
				out.print("Ordine non rimosso. Riprovare \n");
				out.flush();
			}
		} catch (NumberFormatException | SQLException e) {
			response.setStatus(400);
			System.out.println("ID Ordine non numerico. Riprovare \n");
			out.println("ID Ordine non numerico. Riprovare \n");
			e.printStackTrace();
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {
			int id_ordine = Integer.parseInt(request.getParameter("id_ordine"));
			if (id_ordine == Integer.parseInt(String.valueOf(id_ordine))) {
				dao.updateConto(id_ordine);
				out.print("Conto inserito correttamente \n");
				out.flush();
			} else {
				out.print("Conto non inserito. Riprovare \n");
				out.flush();
			}
		} catch (NumberFormatException | SQLException e) {
			response.setStatus(400);
			System.out.println("ID Ordine non numerico. Riprovare \n");
			out.println("ID Ordine non numerico. Riprovare \n");
			e.printStackTrace();
		}
	}
}

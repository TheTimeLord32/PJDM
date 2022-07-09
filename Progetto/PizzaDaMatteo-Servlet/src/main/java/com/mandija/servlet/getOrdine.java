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
 * Servlet implementation class getOrdine
 */
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
		}
		catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("DONE.");
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
			if (allOrdine.toString().equals("[]"))
			{
				System.out.println("Ordine vuoto");
				response.setStatus(400);
				out.print("Ordine vuoto 1\n");
				out.flush();
			} else {
				response.setStatus(200);
				JSONArray allOrdineJson = new JSONArray(allOrdine);
				out.print(allOrdineJson);
				out.flush();
			}
		} catch (SQLException e) {
			out.print("Ordine vuoto 2\n");
			out.flush();
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
		String nome_cliente = request.getParameter("nome_cliente");
		String orario = request.getParameter("orario");
		String recapito = request.getParameter("recapito");
		String indirizzo = request.getParameter("indirizzo");
		String confermato = request.getParameter("confermato");

		try {
			dao.inserisciOrdine(new Ordine(0, nome_cliente, orario, recapito, indirizzo, false));
			response.setStatus(200);
			out.print("Ordine inserito\n");
		} catch (SQLException err) {
			response.setStatus(505);
			out.print("Errore inserimento ordine\n");
			err.printStackTrace();
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String id_ordine = request.getParameter("id_ordine");
		try {
			doGet(request, response);
			if (response.getStatus() == 200) {
				dao.deleteOrdine(Integer.parseInt(id_ordine));
				out.print("Ordine rimosso correttamente");
				out.flush();
			} else {
				out.print("Ordine non rimosso - Try");
				out.flush();
			}

		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			System.out.println("Ordine non rimosso - Catch");
		}
	}
	
}

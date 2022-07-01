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
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String nomeOrdine = request.getParameter("nomeOrdine");
		
		try {
			if(nomeOrdine != null) {
				ArrayList<Ordine> allOrdine = dao.loadOrdine();
				JSONArray allOrdineJson = new JSONArray(allOrdine);
				out.print(allOrdineJson.toString());
				out.flush();
			} else {
				ArrayList<Ordine> allOrdine = dao.loadOrdine();
				JSONArray allOrdineJson = new JSONArray(allOrdine);
				out.print(allOrdineJson.toString());
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
		String modalita = request.getParameter("modalita");
		String nome_cliente = request.getParameter("nome_cliente");
		String orario = request.getParameter("orario");
		String recapito = request.getParameter("recapito");
		String indirizzo = request.getParameter("indirizzo");
		String confermato = request.getParameter("confermato");

		try {
			dao.inserisciOrdine(new Ordine(0, modalita, nome_cliente, orario, recapito, indirizzo, false));
			response.setStatus(200);
			out.print("{\r\n"
					+ "	\"id_ordine\": \"" + id_ordine + "\",\r\n"
					+ "	\"modalita\": \"" + modalita + "\",\r\n"
					+ "	\"nomeCliente\": \"" + nome_cliente + "\",\r\n"
					+ "	\"orario\": \"" + orario + "\",\r\n"
					+ "	\"recapito\": \"" + recapito + "\",\r\n"
					+ "	\"indirizzo\": \"" + indirizzo + "\",\r\n"
					+ "	\"confermato\": " + confermato +"\r\n"
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
			dao.deleteOrdine(Integer.parseInt(id_ordine));
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

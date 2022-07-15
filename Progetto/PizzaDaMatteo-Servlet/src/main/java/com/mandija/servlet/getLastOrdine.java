package com.mandija.servlet;

import com.mandija.dao.PizzaDaMatteoDAO_JDBC;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class getLastOrdine extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PizzaDaMatteoDAO_JDBC dao;

    public getLastOrdine() {
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
            int lastOrdine = dao.getLastOrdine();
            if (lastOrdine == 0) {
                System.out.println("Nessun ordine presente");
                response.setStatus(400);
            } else {
                System.out.println("Ultimo ordine: " + lastOrdine);
                response.setStatus(200);
                out.print(lastOrdine);
                out.flush();
            }
        } catch(SQLException e) {
            response.setStatus(400);
            System.out.println("Errore: " + e.getMessage());
            out.println("Errore: " + e.getMessage());
        }
    }
}

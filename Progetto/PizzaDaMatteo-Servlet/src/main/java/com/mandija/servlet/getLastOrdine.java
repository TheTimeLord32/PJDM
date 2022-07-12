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
            int lastOrdine = dao.getLastOrdine();
            out.print(lastOrdine);
            out.flush();
        } catch(SQLException e) {
            e.printStackTrace();
            out.println("Errore \n" + e.getMessage());
        }
    }
}

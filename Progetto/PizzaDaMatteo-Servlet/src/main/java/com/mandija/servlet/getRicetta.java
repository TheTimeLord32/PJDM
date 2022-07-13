package com.mandija.servlet;

import com.mandija.dao.PizzaDaMatteoDAO_JDBC;
import com.mandija.entity.Ricetta;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

public class getRicetta extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PizzaDaMatteoDAO_JDBC dao;

    public getRicetta() { super(); }

    public void init() throws ServletException {
        String ip = getServletContext().getInitParameter("ip");
        String port = getServletContext().getInitParameter("port");
        String dbName = getServletContext().getInitParameter("dbName");
        String userName = getServletContext().getInitParameter("userName");
        String password = getServletContext().getInitParameter("userPwd");

        System.out.print("PizzaDaMatteo - Ricetta. Opening DB connection... \n");

        try {
            dao = new PizzaDaMatteoDAO_JDBC(ip, port, dbName, userName, password);
        }
        catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("DONE.");
    }

    public void destroy() {
        System.out.print("PizzaDaMatteo - Ricetta. Closing DB connection... \n");
        dao.closeConnection();
        System.out.println("DONE.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String id_ordine = request.getParameter("id_ordine");

        try{
            ArrayList<Ricetta> allRicetta = dao.getRicetta(Integer.parseInt(id_ordine));
            if (allRicetta.toString().equals("[]"))
            {
                System.out.println("Ricetta vuoto");
                response.setStatus(400);
                out.print("Ricetta vuoto 1\n");
                out.flush();
            } else {
                response.setStatus(200);
                JSONArray allOrdineJson = new JSONArray(allRicetta);
                out.print(allOrdineJson);
                out.flush();
            }
        } catch (SQLException e) {
            out.print("Ricetta vuoto 2\n");
            out.flush();
            throw new RuntimeException(e);
        }
    }
}

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
            System.out.println("DONE.");
        }
        catch(SQLException | ClassNotFoundException e) {
            System.out.println("PizzaDaMatteo - Ricetta. Errore connessione DB. \n");
            e.printStackTrace();
        }
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

        try{
            int id_ordine = Integer.parseInt(request.getParameter("id_ordine"));

            if(id_ordine == Integer.parseInt(String.valueOf(id_ordine))) {
                System.out.println("ID Ordine numerico! \n");
                out.println("ID Ordine numerico! \n");
                ArrayList<Ricetta> allRicetta = dao.getRicetta(Integer.valueOf(id_ordine));
                if (allRicetta.toString().equals("[]")) {
                    response.setStatus(204);
                    System.out.println("Ricetta vuoto");
                    out.print("Ricetta vuoto\n");
                } else {
                    response.setStatus(200);
                    JSONArray allOrdineJson = new JSONArray(allRicetta);
                    out.print(allOrdineJson);
                    out.flush();
                }
            }
        } catch (NumberFormatException e) {
            response.setStatus(400);
            System.out.println("ID Ordine non numerico! \n");
            out.println("ID Ordine non numerico! \n");
            e.printStackTrace();
        } catch (SQLException e) {
            response.setStatus(400);
            System.out.println("Errore: " + e.getMessage());
            out.println("Errore: " + e.getMessage());
        }
    }
}

package com.mandija.servlet;

import com.mandija.dao.PizzaDaMatteoDAO_JDBC;
import com.mandija.entity.Ordine2Pizza;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

public class getOrdine2Pizza extends HttpServlet {
    private PizzaDaMatteoDAO_JDBC dao;

    public getOrdine2Pizza() { super(); }

    public void init() throws ServletException {
        String ip = getServletContext().getInitParameter("ip");
        String port = getServletContext().getInitParameter("port");
        String dbName = getServletContext().getInitParameter("dbName");
        String userName = getServletContext().getInitParameter("userName");
        String password = getServletContext().getInitParameter("userPwd");

        System.out.print("PizzaDaMatteo - Ordine2Pizza. Opening DB connection... \n");

        try {
            dao = new PizzaDaMatteoDAO_JDBC(ip, port, dbName, userName, password);
            System.out.println("DONE.");
        }
        catch(SQLException | ClassNotFoundException e) {
            System.out.println("PizzaDaMatteo - Ordine2Pizza. Errore connessione DB. \n");
            e.printStackTrace();
        }
    }

    public void destroy() {
        System.out.print("PizzaDaMatteo - Ordine2Pizza. Closing DB connection... \n");
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
                ArrayList<Ordine2Pizza> allOrdine2Pizza = dao.loadOrdine2Pizza(id_ordine);
                if (allOrdine2Pizza.toString().equals("[]")) {
                    response.setStatus(204);
                    System.out.println("Ordine non presente \n");
                } else {
                    response.setStatus(200);
                    JSONArray allOrdine2PizzaJSON = new JSONArray(allOrdine2Pizza);
                    out.println(allOrdine2PizzaJSON);
                    out.flush();
                }
            }
        } catch (NumberFormatException | SQLException e) {
            response.setStatus(400);
            System.out.println("ID Ordine non numerico! \n");
            out.println("ID Ordine non numerico! \n");
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            int id_ordine = Integer.parseInt(request.getParameter("id_ordine"));
            String pizza = request.getParameter("pizza");

            if (id_ordine == Integer.parseInt(String.valueOf(id_ordine))) {
                System.out.println("ID Ordine numerico! \n");
                out.println("ID Ordine numerico! \n");
                if (pizza.equals("")) {
                    response.setStatus(204);
                    System.out.println("Pizza non presente \n");
                } else {
                    response.setStatus(200);
                    Ordine2Pizza ordine2pizza = new Ordine2Pizza(0, id_ordine, pizza, false);
                    dao.inserisciOrdine2Pizza(ordine2pizza);
                    System.out.println("Pizza inserita \n");
                }
            }
        } catch (NumberFormatException | SQLException e) {
            response.setStatus(400);
            System.out.println("ID Ordine non numerico! \n");
            out.println("ID Ordine non numerico! \n");
            e.printStackTrace();
        }
    }
}

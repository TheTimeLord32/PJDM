package com.mandija.servlet;

import com.mandija.dao.PizzaDaMatteoDAO_JDBC;
import com.mandija.entity.Ordine2;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

public class getOrdine2 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PizzaDaMatteoDAO_JDBC dao;

    public getOrdine2() {
        super();
    }

    public void init() throws ServletException {
        String ip = getServletContext().getInitParameter("ip");
        String port = getServletContext().getInitParameter("port");
        String dbName = getServletContext().getInitParameter("dbName");
        String userName = getServletContext().getInitParameter("userName");
        String password = getServletContext().getInitParameter("userPwd");

        System.out.print("PizzaDaMatteo - Ordine2. Opening DB connection... \n");

        try {
            dao = new PizzaDaMatteoDAO_JDBC(ip, port, dbName, userName, password);
            System.out.println("DONE.");
        }
        catch(SQLException | ClassNotFoundException e) {
            System.out.println("PizzaDaMatteo - Ordine2. Errore connessione DB. \n");
            e.printStackTrace();
        }
    }

    public void destroy() {
        System.out.print("PizzaDaMatteo - Ordine2. Closing DB connection... \n");
        dao.closeConnection();
        System.out.println("DONE.");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            int id_ordine = Integer.parseInt(request.getParameter("id_ordine"));

            if (id_ordine == Integer.parseInt(String.valueOf(id_ordine))) {
                System.out.println("ID Ordine numerico! \n");
                ArrayList<Ordine2> allOrdine2 = dao.loadOrdine2(id_ordine);
                if (allOrdine2.toString().equals("[]")) {
                    response.setStatus(400);
                    System.out.println("Ordine non presente \n");
                    out.println("Ordine non presente \n");
                } else {
                    response.setStatus(200);
                    JSONArray allOrdine1Json = new JSONArray(allOrdine2);
                    out.print(allOrdine1Json);
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            int id_ordine = Integer.parseInt(request.getParameter("id_ordine"));
            String pizza = request.getParameter("pizza");
            String fritti = request.getParameter("fritti");
            String bibite = request.getParameter("bibite");

            if (id_ordine == Integer.parseInt(String.valueOf(id_ordine))) {
                System.out.println("ID Ordine numerico! \n");
                dao.inserisciOrdine2(new Ordine2(0, id_ordine, pizza, fritti, bibite, false));
                response.setStatus(200);
                out.print("Ordine inserito\n");
            }
        } catch (NumberFormatException | SQLException e){
            response.setStatus(400);
            System.out.println("ID Ordine non numerico! \n");
            out.println("ID Ordine non numerico! \n");
            e.printStackTrace();
        }
    }
}

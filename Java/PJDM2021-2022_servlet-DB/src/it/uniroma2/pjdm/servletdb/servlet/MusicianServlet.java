package it.uniroma2.pjdm.servletdb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import it.uniroma2.pjdm.servletdb.dao.MusicistiDAO;
import it.uniroma2.pjdm.servletdb.dao.MusicistiDAOJDBCImpl;
import it.uniroma2.pjdm.servletdb.entity.Musicista;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Musician
 */
public class MusicianServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MusicistiDAO dao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MusicianServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		String ip = getInitParameter("ip");
		String port = getInitParameter("port");
		String dbName = getInitParameter("dbName");
		String userName = getInitParameter("userName");
		String password = getInitParameter("password");

		System.out.print("MusicianServlet. Opening DB connection...");

		dao = new MusicistiDAOJDBCImpl(ip, port, dbName, userName, password);

		System.out.println("DONE.");
	}

	@Override
	public void destroy() {
		System.out.print("MusicianServlet. Closing DB connection...");
		dao.closeConnection();
		System.out.println("DONE.");
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MusicianServlet. Invoking a doPut method...");
		response.setStatus(405);
		response.getWriter().append("L'implementazione del metodo PUT e' implementato come esercizio.");
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MusicianServlet. Invoking a doDelete method.");
		if (request.getParameter("ssn") == null || request.getParameter("name") == null) {
			response.setStatus(500);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append("Occorre specificare un ssn e nome da inserire");
			return;
		}

		int ssn = Integer.valueOf(request.getParameter("ssn"));
		String name = request.getParameter("name");

		Musicista oldMusicista = dao.loadMusicistaByID(ssn);
		if (oldMusicista == null || !oldMusicista.getName().equalsIgnoreCase(name)) {
			response.setStatus(400);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append("Purtroppo il seguente musicista non è nel DB");
			return;
		}

		Musicista newMusicista = new Musicista(name, ssn);
		boolean isOk = dao.deleteMusicista(newMusicista);

		if (isOk) {
			// IT WORKS BUT IT IS NOT... ELEGANT... or UNIFORM
			// Better to answer in JSON
			response.setStatus(200);
			response.getWriter().append("Tutto OK");
		} else {
			response.setStatus(500);
			response.getWriter().append("Problemi :-(");
		}
	}

	/**
	 * 
	 * This method requires the integer <code>ssn</code> used in the request to
	 * specify the serial number of the Musician to be retrieved by the DB. When
	 * <code>ssn=-1</code> the entire list of Musicians is retrieved.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MusicianServlet. Invoking a doGet method.");
		if (request.getParameter("ssn") == null) {
			response.setStatus(400);
			response.getWriter().append("Occorre specificare un ssn");
			return;
		}

		// What if the parameter ssn is "dog"?
		int ssn = Integer.valueOf(request.getParameter("ssn"));

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// ARE YOU SURE TO USE THE IF CONSTRUCT OR IS IT BETTER AN
		// ADDITIONAL SERVLET ?
		if (ssn == -1) {
			ArrayList<Musicista> allMusicians = dao.loadTuttiMusicisti();
			JSONArray allMusiciansJson = new JSONArray(allMusicians);
			out.print(allMusiciansJson.toString());
			out.flush();
		} else {
			Musicista musicista = dao.loadMusicistaByID(ssn);
			if (musicista == null) {
				response.setStatus(404);
				response.getWriter().append("Non esiste un musicista con ID " + ssn);
				return;
			}
			JSONObject musicistaJson = new JSONObject(musicista);
			out.print(musicistaJson.toString());
			out.flush();
		}
	}

	/**
	 * Il metodo POST permette di inserire un nuovo musicista, inviando un nuovo
	 * musicista specificando name e ssn
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MusicianServlet. Invoking a doPost method...");

		if (request.getParameter("ssn") == null || request.getParameter("name") == null) {
			response.setStatus(500);
			response.getWriter().append("Occorre specificare un ssn e nome da inserire");
			return;
		}

		int ssn = Integer.valueOf(request.getParameter("ssn"));
		String name = request.getParameter("name");

		Musicista newMusicista = new Musicista(name, ssn);
		int res = dao.insertMusicista(newMusicista);

		response.setContentType("application/json");

		JSONObject resJsonObject = new JSONObject();
		if (res > 0) {
			response.setStatus(200);
			try {
				resJsonObject.put("result", "OK");
				response.getWriter().append(resJsonObject.toString());
			} catch (JSONException e) {
				// A custom error
				response.setStatus(533);
			}

		} else {
			response.setStatus(500);

			try {
				resJsonObject.put("result", "KO");
				response.getWriter().append(resJsonObject.toString());
			} catch (JSONException e) {
				// A custom error
				response.setStatus(533);
			}
		}

	}

}

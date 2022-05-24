package it.uniroma2.pjdm.servletdb.main;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import it.uniroma2.pjdm.servletdb.dao.MusicistiDAO;
import it.uniroma2.pjdm.servletdb.dao.MusicistiDAOJDBCImpl;
import it.uniroma2.pjdm.servletdb.entity.Musicista;

public class Main {

	public static void main(String[] args) {

		String ip = "127.0.0.1";
		String port = "3306";
		String dbName = "musicisti";
		String userName = "danilo";
		String password = "danilo";

		MusicistiDAO dao = new MusicistiDAOJDBCImpl(ip, port, dbName, userName, password);

		String inputName = "Ron Wood";
		Musicista loadedMusician = dao.loadMusicista(inputName);
		System.out.println(loadedMusician);

		System.out.println("\nLista completa dei musicisti");
		ArrayList<Musicista> tuttiMusicisti = dao.loadTuttiMusicisti();
		for (Musicista musicista : tuttiMusicisti) {
			System.out.println(musicista);
		}

		JSONObject jsonObject = new JSONObject(loadedMusician);
		System.out.println(jsonObject);

		JSONArray jsonArray = new JSONArray(tuttiMusicisti);
		System.out.println(jsonArray);

		System.out.println("\nIl numero di Musicisti è " + dao.loadTuttiMusicisti().size());

		Musicista newMusicista = new Musicista("James Hatefield", 9);

		dao.insertMusicista(newMusicista);
		
		System.out.println();
		
		System.out.println("\nIl numero di Musicisti è " + dao.loadTuttiMusicisti().size() + " dopo l'aggiunta di "
				+ newMusicista);

		dao.deleteMusicista(newMusicista);

		System.out.println("\nIl numero di Musicisti è " + dao.loadTuttiMusicisti().size() + " dopo la rimozione di "
				+ newMusicista);

		dao.closeConnection();

		System.out.println("\nConnection Closed");

	}

}

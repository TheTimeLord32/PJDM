package it.uniroma2.pjdm.servletdb.dao;

import java.util.ArrayList;

import it.uniroma2.pjdm.servletdb.entity.Musicista;

public interface MusicistiDAO {

	public Musicista loadMusicista(String name);

	public Musicista loadMusicistaByID(int inputSsn);

	public ArrayList<Musicista> loadTuttiMusicisti();

	public int insertMusicista(Musicista musicista);

	public boolean deleteMusicista(Musicista musicista);

	public void closeConnection();

}

package com.mandija.entity;

public class Ordine1 {
	private int id_ordine, num_pizze, num_fritti, num_bibite, conto;
	private String pizza1, pizza2, pizza3, pizza4, pizza5, fritti1, fritti2, fritti3, fritti4, fritti5, bibite1, bibite2, bibite3, bibite4, bibite5, note;
	private boolean confermato;
		
	public Ordine1(int id_ordine, int num_pizze, int num_fritti, int num_bibite, int conto, String pizza1, String pizza2, String pizza3, String pizza4, String pizza5, String fritti1, String fritti2, String fritti3, String fritti4, String fritti5, String bibite1, String bibite2, String bibite3, String bibite4, String bibite5, String note, boolean confermato) {
		super();
		this.id_ordine = id_ordine;
		this.num_pizze = num_pizze;
		this.num_fritti = num_fritti;
		this.num_bibite = num_bibite;
		this.conto = conto;
		this.pizza1 = pizza1;
		this.pizza2 = pizza2;
		this.pizza3 = pizza3;
		this.pizza4 = pizza4;
		this.pizza5 = pizza5;
		this.fritti1 = fritti1;
		this.fritti2 = fritti2;
		this.fritti3 = fritti3;
		this.fritti4 = fritti4;
		this.fritti5 = fritti5;
		this.bibite1 = bibite1;
		this.bibite2 = bibite2;
		this.bibite3 = bibite3;
		this.bibite4 = bibite4;
		this.bibite5 = bibite5;
		this.note = note;
		this.confermato = confermato;
	}
	
	public int getId_ordine() { return id_ordine; }
	public int getnum_pizze() { return num_pizze; }
	public int getnum_fritti() { return num_fritti; }
	public int getnum_bibite() { return num_bibite; }
	public int getConto() { return conto; }
	public String getNote() { return note; }
	public boolean getConfermato() { return confermato; }
	
	public String getPizza1() { return pizza1; }
	public String getPizza2() { return pizza2; }
	public String getPizza3() { return pizza3; }
	public String getPizza4() { return pizza4; }
	public String getPizza5() { return pizza5; }
	public String getFritti1() { return fritti1; }
	public String getFritti2() { return fritti2; }
	public String getFritti3() { return fritti3; }
	public String getFritti4() { return fritti4; }
	public String getFritti5() { return fritti5; }
	public String getBibite1() { return bibite1; }
	public String getBibite2() { return bibite2; }
	public String getBibite3() { return bibite3; }
	public String getBibite4() { return bibite4; }
	public String getBibite5() { return bibite5; }
	
}

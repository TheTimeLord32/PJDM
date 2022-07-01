package com.mandija.entity;

import java.util.Arrays;

public class Ordine1 {
	private int id_ordine, num_pizze, num_fritti, num_bibite, conto;
	private String pizza1, pizza2, pizza3, pizza4, pizza5, fritto1, fritto2, fritto3, fritto4, fritto5, bibita1, bibita2, bibita3, bibita4, bibita5, note;
	private boolean confermato;
		
	public Ordine1(int id_ordine, int num_pizze, int num_fritti, int num_bibite, int conto, String pizza1, String pizza2, String pizza3, String pizza4, String pizza5, String fritto1, String fritto2, String fritto3, String fritto4, String fritto5, String bibita1, String bibita2, String bibita3, String bibita4, String bibita5, String note, boolean confermato) {
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
		this.fritto1 = fritto1;
		this.fritto2 = fritto2;
		this.fritto3 = fritto3;
		this.fritto4 = fritto4;
		this.fritto5 = fritto5;
		this.bibita1 = bibita1;
		this.bibita2 = bibita2;
		this.bibita3 = bibita3;
		this.bibita4 = bibita4;
		this.bibita5 = bibita5;
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
	public String getFritto1() { return fritto1; }
	public String getFritto2() { return fritto2; }
	public String getFritto3() { return fritto3; }
	public String getFritto4() { return fritto4; }
	public String getFritto5() { return fritto5; }
	public String getBibita1() { return bibita1; }
	public String getBibita2() { return bibita2; }
	public String getBibita3() { return bibita3; }
	public String getBibita4() { return bibita4; }
	public String getBibita5() { return bibita5; }
	
}

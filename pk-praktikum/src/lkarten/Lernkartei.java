package lkarten;

import java.util.ArrayList;

public class Lernkartei {

	private ArrayList<Lernkarte> lernkarten = new ArrayList<Lernkarte>();

	public void hinzufuegen(Lernkarte karte) {
		lernkarten.add(karte);
	}

	public void druckeAlleKarten() {
		for (Lernkarte karte : lernkarten) {
			karte.druckeKarte();
		}
	}

	public int gibAnzahlKarten() {
		int sum = 0;
		for (Lernkarte karte : lernkarten) {
			if (karte != null) {
				sum++;
			}
		}
		return sum;
	}

	public Lernkarte[] gibKartenZuKategorie(String kategorie) {
		
		return null;
	}

	public Lernkarte[] erzeugeDeck(int anzahlKarten) {
		return null;
	}
}

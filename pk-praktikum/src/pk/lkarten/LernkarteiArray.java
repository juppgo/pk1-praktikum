package pk.lkarten;

import java.util.Random;

public class LernkarteiArray {

	private Lernkarte[] karten;

	public LernkarteiArray(int kapazitaet) {
		this.karten = new Lernkarte[kapazitaet];
	}

	public void hinzufuegen(Lernkarte karte) {
		boolean full = true;
		for (int i = 0; i < karten.length; i++) {
			if (karten[i] == null) {
				karten[i] = karte;
				full = false;
				break;
			}
		}
		if (full) {
			System.out.println("Fehlermeldung: Das Array ist voll!");
		}
	}
//Auskommontiert, da sonst Compilerfehler durch Stream
	/*public void druckeAlleKarten() {
		for (int i = 0; i < karten.length; i++) {
			if (karten[i] != null) {
				karten[i].druckeKarte();
			}
		}
	}*/

	public int gibAnzahlKarten() {
		int sum = 0;
		for (int i = 0; i < karten.length; i++) {
			if (karten[i] != null) {
				sum++;
			}
		}
		return sum;
	}

	public Lernkarte[] gibKartenZuKategorie(String kategorie) {
		Lernkarte[] aKategorie = new Lernkarte[karten.length];
		int counter = 0;
		for (int i = 0; i < karten.length; i++) {
			if (karten[i] != null && karten[i].getKategorie().equals(kategorie)) {
				aKategorie[counter] = karten[i];
				counter++;
			}
		}
		return aKategorie;
	}

	public Lernkarte[] erzeugeDeck(int anzahlKarten) {
		Lernkarte[] deck = new Lernkarte[anzahlKarten];
		Random ran = new Random();
		for (int i = 0; i < anzahlKarten; i++) {
			deck[i] = karten[gibAnzahlKarten() == 1 ? 0 : ran.nextInt(gibAnzahlKarten())];
		}
		return deck;
	}
}

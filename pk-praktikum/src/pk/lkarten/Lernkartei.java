package pk.lkarten;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

public class Lernkartei {

	private ArrayList<Lernkarte> karten;

	public Lernkartei() {
		this.karten = new ArrayList<Lernkarte>();
	}

	public void hinzufuegen(Lernkarte karte) {
		karten.add(karte);
	}

	public void druckeAlleKarten() {
		ListIterator<Lernkarte> kartenIterator = karten.listIterator(0);
		while (kartenIterator.hasNext()) {
			kartenIterator.next().druckeKarte();
		}
	}

	public int gibAnzahlKarten() {
		for (int i = 0; i < karten.size(); i++) {
			karten.size();
		}
		return karten.size();
	}

	public ArrayList<Lernkarte> gibKartenZuKategorie(String kategorie) {
		ArrayList<Lernkarte> arrayListKategorie = new ArrayList<Lernkarte>();
		for(Lernkarte lernkarten: karten) {
			if(lernkarten.getKategorie().equals(kategorie)) {
				arrayListKategorie.add(lernkarten);
			}
		}
		return arrayListKategorie;
	}

	public ArrayList<Lernkarte> erzeugeDeck(int anzahlKarten) {
		ArrayList<Lernkarte> deck = new ArrayList<Lernkarte>();
		Random ran = new Random();
		for (int i = 0; i < anzahlKarten; i++) {
			deck.add(karten.get(gibAnzahlKarten() == 1 ? 0 : ran.nextInt(gibAnzahlKarten())));
		}
		return deck;
	}
}

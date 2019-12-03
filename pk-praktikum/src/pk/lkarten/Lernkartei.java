package pk.lkarten;

//import java.util.ArrayList;
//import java.util.ListIterator;
import java.util.*;

public class Lernkartei {


	private HashSet<Lernkarte> karten;

	public Lernkartei() {
		this.karten = new HashSet<Lernkarte>();
	}

	public void hinzufuegen(Lernkarte karte) throws UngueltigeKarteException {
		karte.validiere();
		karten.add(karte);
	}

	public void druckeAlleKarten() {
		List<Lernkarte> sortList = new ArrayList<>(karten);
		Collections.sort(sortList, new SortiereLernkarte());
		for(Lernkarte k: sortList) {
			k.druckeKarte();
		}
	}

	public int gibAnzahlKarten() {
		for (int i = 0; i < karten.size(); i++) {
			karten.size();
		}
		return karten.size();
	}

	public HashSet<Lernkarte> gibKartenZuKategorie(String kategorie) {
		HashSet<Lernkarte> arrayListKategorie = new HashSet<Lernkarte>();
		for(Lernkarte lernkarte: karten) {
			if(lernkarte.getKategorie().equals(kategorie)) {
				arrayListKategorie.add(lernkarte);
			}
		}
		return arrayListKategorie;
	}

	public ArrayList<Lernkarte> erzeugeDeck(int anzahlKarten) {
		ArrayList<Lernkarte> deck = new ArrayList<>();
	    Random random = new Random();
	    while ( anzahlKarten != 0 ){
	        int zufaellig = random.nextInt(karten.size());
	        int zaehler = 0;
	        for (Lernkarte k: karten) {
	            if ( zaehler == zufaellig)
	                deck.add(k);
	        }
	        anzahlKarten--;
	    }
	    return deck;
	}
}

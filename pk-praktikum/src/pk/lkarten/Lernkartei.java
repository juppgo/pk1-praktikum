package pk.lkarten;

//import java.util.ArrayList;
//import java.util.ListIterator;
import java.util.*;

public class Lernkartei {

	//private ArrayList<Lernkarte> karten;
	private HashSet<Lernkarte> karten;

	public Lernkartei() {
		this.karten = new HashSet<Lernkarte>();
	}

	public void hinzufuegen(Lernkarte karte) {
		karten.add(karte);
	}

	public void druckeAlleKarten() {
//		ListIterator<Lernkarte> kartenIterator = karten.listIterator(0);
//		while (kartenIterator.hasNext()) {
//			kartenIterator.next().druckeKarte();
//		}
		List<Lernkarte> sortList = new ArrayList<>(karten);
		Collections.sort(sortList, new SortiereLernkarte());
		
//		Iterator<Lernkarte> kartenIterator = karten.iterator();
//		while (kartenIterator.hasNext()) {
//			kartenIterator.next().druckeKarte();
//		}
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
//		ArrayList<Lernkarte> deck = new ArrayList<Lernkarte>();
//		Random ran = new Random();
//		for (int i = 0; i < anzahlKarten; i++) {
//			deck.add(karten.get(gibAnzahlKarten() == 1 ? 0 : ran.nextInt(gibAnzahlKarten())));
//		}
//		return deck;
		ArrayList<Lernkarte> deck = new ArrayList<>();
	    Random random = new Random();
	    while ( anzahlKarten != 0 ){
	        int zufällig = random.nextInt(karten.size());
	        int zaehler = 0;
	        for (Lernkarte k: karten) {
	            if ( zaehler == zufällig)
	                deck.add(k);
	        }
	        anzahlKarten--;
	    }
	    return deck;
	}



}

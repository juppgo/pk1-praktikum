package pk.lkarten;

//import java.util.ArrayList;
//import java.util.ListIterator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Lernkartei {


	private HashSet<Lernkarte> karten;

	public Lernkartei() {
		this.karten = new HashSet<>();
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

	public void exportiereEintraegeAlsCsv(File datei) {
		String csv = "";
		for(Lernkarte lernkarten: karten) {
			csv += lernkarten.exportiereAlsCsv();
		}

		//Dateiname korrekt einbinden
		String path= "/home/chris/pk1-praktikum/pk-praktikum/src/pk/lkarten/" + datei + ".csv";
		Path filePath = Paths.get(path);

		try {
			Files.writeString(filePath, csv);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

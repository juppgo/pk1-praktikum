package pk.lkarten;
import java.util.Random;


public class Lernkartei {

//	private Lernkarte[] karten = new Lernkarte[10];
	private Lernkarte[] karten;
	
	public Lernkartei() {
		this.karten = new Lernkarte[50];
	}

	
	public void hinzufuegen(Lernkarte karte) {
		boolean full = true;
		for (int i = 0; i < karten.length; i++) {
			if (karten[i] == null) {
				karten[i] = karte;
				full = false;
				break;
			}
			if(full) {
				System.out.println("Fehlermeldung: Das Array ist voll!");
			}
		}
	}

	public void druckeAlleKarten() {
		for (int i = 0; i < karten.length; i++) {
			if (karten[i] != null) {
				karten[i].druckeKarte();
			}
		}
	}

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
		Lernkarte[] aKategorie = new Lernkarte[50];
		int counter = 0;
		for(int i = 0; i < karten.length; i++) {
			if(karten[i].getKategorie().equals(kategorie)) {
				aKategorie[counter] = karten[i];
				counter++;
			}
		}
		return aKategorie;

	}

	public Lernkarte[] erzeugeDeck(int anzahlKarten) {
		int anzahlKarten;
		return null;
	}
}

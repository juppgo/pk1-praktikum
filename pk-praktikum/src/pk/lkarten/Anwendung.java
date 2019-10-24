package pk.lkarten;

public class Anwendung {

	public static void main(String[] args) {

		// 1. Instanz der Klasse erzeugen
		Lernkartei lernkartei = new Lernkartei(3);

		// 2. Drei Lernkarten erzeugen
		lernkartei.hinzufuegen(new Lernkarte("Kategorie 01", "Titel 01", "Frage 01", "Antwort 01"));
		lernkartei.hinzufuegen(new Lernkarte("Kategorie 02", "Titel 02", "Frage 02", "Antwort 02"));
		lernkartei.hinzufuegen(new Lernkarte("Kategorie 01", "Titel 03", "Frage 03", "Antwort 03"));
		 
			// Test, ob Fehlermeldung bei überschreiten der Kapazitätsgrenze erscheint
		lernkartei.hinzufuegen(new Lernkarte("Kategorie 04", "Titel 04", "Frage 04", "Antwort 04"));

		// 3. alle Methoden aufrufen
		System.out.println("\nMethodenaufruf: gibAnzahlKarten\n");
		System.out.println(lernkartei.gibAnzahlKarten());
		System.out.println("\nMethodenaufruf: druckeAlleKarten\n");
		lernkartei.druckeAlleKarten();
		System.out.println("\nMethodenaufruf: gibKartenZuKategorie\n");
		for (Lernkarte karte : lernkartei.gibKartenZuKategorie("Kategorie 01")) {
			if (karte != null) {
				karte.druckeKarte();
			}
		}
		System.out.println("\nMethodenaufruf: erzeugeDeck\n");
		for (Lernkarte karte : lernkartei.erzeugeDeck(10)) {
			karte.druckeKarte();
		}
	}

}

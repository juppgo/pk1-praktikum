package lkarten;

public class Lernkarte {
	private final int id = 0;
	private String kategorie;
	private String titel;
	private String frage;
	private String antwort;

	public Lernkarte(int id, String kategorie, String titel, String frage, String antwort) {
		id = this.id;
		id++;
	}

	public void zeigeVorderseite() {
		System.out.println("[" + id + ", " + kategorie + " ] " + titel + ":" + "\n" + frage);
	}

	public void zeigeRueckseite() {
		System.out.println(antwort);
	}

	public void druckeKarte() {
		zeigeVorderseite();
		zeigeRueckseite();
	}
}

package pk.lkarten;

public class Lernkarte {
	private final int id;
	private static int IDCOUNT = 1;
	private String kategorie;
	private String titel;
	private String frage;
	private String antwort;

	public Lernkarte(String kategorie, String titel, String frage, String antwort) {
		this.kategorie = kategorie;
		this.titel = titel;
		this.frage = frage;
		this.antwort = antwort;
		this.id = IDCOUNT++;
	}

	public String getKategorie() {
		return this.kategorie;
	}

	public String getTitel() {
		return this.titel;
	}

	public String getFrage() {
		return this.frage;
	}

	public String getAntwort() {
		return this.antwort;
	}

	public int getId() {
		return this.id;
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

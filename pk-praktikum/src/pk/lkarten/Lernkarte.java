package pk.lkarten;

public abstract class Lernkarte {
	private final int id;
	private static int IDCOUNT = 1;
	private String kategorie;
	private String titel;
	private String frage;

	public Lernkarte(String kategorie, String titel, String frage) {
		this.kategorie = kategorie;
		this.titel = titel;
		this.frage = frage;
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

	public int getId() {
		return this.id;
	}
	
	public abstract void zeigeVorderseite();

	
	public abstract void zeigeRueckseite();

	public void druckeKarte() {
		zeigeVorderseite();
		zeigeRueckseite();
	}
	
}

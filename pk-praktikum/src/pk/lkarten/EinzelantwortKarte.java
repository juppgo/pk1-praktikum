package pk.lkarten;

public class EinzelantwortKarte extends Lernkarte {

	private String antwort;

	public EinzelantwortKarte(String kategorie, String titel, String frage, String antwort) {
		super(kategorie, titel, frage);
		this.antwort = antwort;
	}

	public void zeigeVorderseite() {
		System.out.println("[" + super.getId() + ", " + super.getKategorie() + " ] " + super.getTitel() + ":" + "\n"
				+ super.getFrage());
	}

	public void zeigeRueckseite() {
		System.out.println(antwort);
	}
	
	public void druckeKarte() {
		zeigeVorderseite();
		zeigeRueckseite();
		System.out.println();
	}

}

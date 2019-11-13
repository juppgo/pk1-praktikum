package pk.lkarten;

public class MehrfachantwortKarte extends Lernkarte {

	private String[] moeglicheAntworten;
	private int[] richtigeAntworten;
	boolean mehrereAntworten;

	public MehrfachantwortKarte(String kategorie, String titel, String frage, String[] moeglicheAntworten,
			int[] richtigeAntworten) {
		super(kategorie, titel, frage);
		this.moeglicheAntworten = moeglicheAntworten;
		this.richtigeAntworten = richtigeAntworten;
	}

	public String[] getMoeglicheAntworten() {
		return moeglicheAntworten;
	}

	public int[] getRichtigeAntworten() {
		return richtigeAntworten;
	}

	public void printMoeglicheAntworten() {
		int counter = 1;
		for (int i = 0; i < this.moeglicheAntworten.length; i++) {
			if (this.moeglicheAntworten[i] != null) {
				System.out.println(counter + ": " + this.moeglicheAntworten[i]);
				counter++;
			}
		}
	}

	public void zeigeVorderseite() {
		System.out.println("[" + this.getId() + ", " + this.getKategorie() + " ] " + this.getTitel() + ":" + "\n"
				+ this.getFrage() + "\n");
		printMoeglicheAntworten();
		if (this.richtigeAntworten.length > 1) {
			System.out.println("(mehrere Antworten möglich)");
		}
	}

	public void zeigeRueckseite() {
		System.out.println();
		System.out.println("Die richtigen Antworten sind:");
		for (int i = 0; i < this.richtigeAntworten.length; i++) {
			System.out.println(richtigeAntworten[i] + 1 + ": " + moeglicheAntworten[richtigeAntworten[i]]);
		}
		System.out.println();
	}
}

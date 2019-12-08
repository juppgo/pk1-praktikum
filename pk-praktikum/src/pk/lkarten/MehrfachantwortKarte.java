package pk.lkarten;

import java.util.Arrays;

public class MehrfachantwortKarte extends Lernkarte implements ValidierbareKarte, CsvExportable {

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
			System.out.println("(mehrere Antworten moeglich)");
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

	public void validiere() throws UngueltigeKarteException {
		String fehlerstring = "";
		int counter = 0;
		try {
			super.validiere();
		}
		catch(UngueltigeKarteException exp) {
			fehlerstring = exp.getMessage();
			for(int i = 0; i < this.moeglicheAntworten.length; i++) {
				if (this.moeglicheAntworten[i].isBlank()) {
					counter++;
				}
			}
			if(counter <= 2) {
				fehlerstring += "Zu wenige moegliche Antworten gegeben.";
			}
			if(counter >= 2) {
				return;
			}
			throw new UngueltigeKarteException(fehlerstring);
		}
	}

	public String exportiereAlsCsv() {
		return (super.exportiereAlsCsv() +","+ Arrays.toString(moeglicheAntworten) + "," + Arrays.toString(richtigeAntworten) + "\n");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (mehrereAntworten ? 1231 : 1237);
		result = prime * result + Arrays.hashCode(moeglicheAntworten);
		result = prime * result + Arrays.hashCode(richtigeAntworten);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MehrfachantwortKarte other = (MehrfachantwortKarte) obj;
		if (mehrereAntworten != other.mehrereAntworten)
			return false;
		if (!Arrays.equals(moeglicheAntworten, other.moeglicheAntworten))
			return false;
        return Arrays.equals(richtigeAntworten, other.richtigeAntworten);
    }
}
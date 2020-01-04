package pk.lkarten;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;

public class EinzelantwortKarte extends Lernkarte implements ValidierbareKarte, CsvExportable, Serializable {

	private String antwort;
	private static final long serialVersionUID = 2074366230277156847L;

	public EinzelantwortKarte(String kategorie, String titel, String frage, String antwort) {
		super(kategorie, titel, frage);
		this.antwort = antwort;
	}

//	public String getAntwort() {
//		return antwort;
//	}

	public void zeigeVorderseite(OutputStream stream) throws IOException {
		String vorderseite = ("[" + super.getId() + ", " + super.getKategorie() + " ] " + super.getTitel() + ":" + "\n"
				+ super.getFrage() + "\n");
		OutputStreamWriter sw = new OutputStreamWriter(stream);
		sw.write(vorderseite.toCharArray());
		sw.flush();
	}

	public void zeigeRueckseite(OutputStream stream) throws IOException {
		String rueckseite = antwort + "\n";
		OutputStreamWriter sw = new OutputStreamWriter(stream);
		sw.write(rueckseite.toCharArray());
		sw.flush();
	}

	public void validiere() throws UngueltigeKarteException {
		String fehlerstring = "";
		try {
			super.validiere();
		}
		catch(UngueltigeKarteException exp) {
			fehlerstring+= exp.getMessage();
			if(this.antwort.isBlank()) {
				fehlerstring += "* Keine gültige Antwort gegeben!\n";
			}
			if(fehlerstring.isBlank()) {
				return;
			}throw new UngueltigeKarteException(fehlerstring);
		}
	}

	public String exportiereAlsCsv() {
		return (super.exportiereAlsCsv() + "," + antwort + "\n");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((antwort == null) ? 0 : antwort.hashCode());
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
		EinzelantwortKarte other = (EinzelantwortKarte) obj;
		if (antwort == null) {
			return other.antwort == null;
		} else return antwort.equals(other.antwort);
	}
	
}

package pk.lkarten;

public class EinzelantwortKarte extends Lernkarte implements ValidierbareKarte {

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

	public void validiere() throws UngueltigeKarteException {
		super.validiere();
		if(this.antwort == null || this.antwort.isBlank()) {
			throw new UngueltigeKarteException("* Keine gültige Antwort gegeben!\n");
		}

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

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
		// Abfangen, dass ID hochgez�hlt wird falls das Objekt schon im Hashset
		// vorhanden ist
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
		return id;
	}

	public abstract void zeigeVorderseite();

	public abstract void zeigeRueckseite();

	public void druckeKarte() {
		zeigeVorderseite();
		zeigeRueckseite();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((frage == null) ? 0 : frage.hashCode());
		// ID nicht vergleichbar, da fortlaufend
		// result = prime * result + id;
		result = prime * result + ((kategorie == null) ? 0 : kategorie.hashCode());
		result = prime * result + ((titel == null) ? 0 : titel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lernkarte other = (Lernkarte) obj;
		if (frage == null) {
			if (other.frage != null)
				return false;
		} else if (!frage.equals(other.frage))
			return false;
		if (id != other.id)
			return false;
		if (kategorie == null) {
			if (other.kategorie != null)
				return false;
		} else if (!kategorie.equals(other.kategorie))
			return false;
		if (titel == null) {
			if (other.titel != null)
				return false;
		} else if (!titel.equals(other.titel))
			return false;
		return true;
	}

}

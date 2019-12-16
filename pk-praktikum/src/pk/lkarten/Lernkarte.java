package pk.lkarten;


import java.io.IOException;
import java.io.OutputStream;

public abstract class Lernkarte implements ValidierbareKarte, CsvExportable {
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
        return id;
    }

    public abstract void zeigeVorderseite(OutputStream stream) throws IOException;

    public abstract void zeigeRueckseite(OutputStream stream) throws IOException;

    public void druckeKarte() throws IOException {
        zeigeVorderseite(System.out);
        zeigeRueckseite(System.out);
    }

    public void validiere() throws UngueltigeKarteException {
        String fehlerstring = "";
        if (this.kategorie.isBlank()) {
            fehlerstring += "* Keine gueltige Kategorie angegeben!\n";
        }
        if (this.frage.isBlank()) {
            fehlerstring += "* Keine gueltige Frage angegeben!\n";
        }
        if (this.titel.isBlank()) {
            fehlerstring += "* Keinen gueltigen Titel angegeben!\n";
        }
        throw new UngueltigeKarteException(fehlerstring);
    }

    public String exportiereAlsCsv() {
        return id + "," + kategorie + "," + titel + "," + frage;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((frage == null) ? 0 : frage.hashCode());
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
            return other.titel == null;
        } else
            return titel.equals(other.titel);
    }
}

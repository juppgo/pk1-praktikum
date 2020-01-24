package pk.lkarten;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.Arrays;

public class MehrfachantwortKarte extends Lernkarte implements ValidierbareKarte, CsvExportable, Serializable {

    boolean mehrereAntworten;
    private String[] moeglicheAntworten;
    private int[] richtigeAntworten;
    private static final long serialVersionUID = -2953423290824019453L;

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

    @Override
    public String getAntwortVorderseiteDialog() {
        String antwortVorderseite = "";
        int counter = 1;
        for (int i = 0; i < this.moeglicheAntworten.length; i++) {
            if(this.moeglicheAntworten[i] != null) {
                antwortVorderseite += counter + ": " + this.moeglicheAntworten[i] + "\n";
                counter++;
            }
        }
        return antwortVorderseite;
    }

    public String getAntwortRueckseiteDialog() {
        String antwortRueckseite = "Die richtigen Antworten sind: \n";
        for(int i = 0; i < this.richtigeAntworten.length; i++) {
            antwortRueckseite += richtigeAntworten[i]+1 + ": " + moeglicheAntworten[richtigeAntworten[i]] + "\n";
        }
        return antwortRueckseite;
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

    @Override
    public String toString() {
        return super.toString() + ", moeglicheAntworten=" + Arrays.toString(getMoeglicheAntworten()) + ", richtigeAntworten=" + Arrays.toString(getRichtigeAntworten()) + "]";
    }

    public void zeigeVorderseite(OutputStream stream) throws IOException {
        String vorderseite = ("[" + super.getId() + ", " + super.getKategorie() + " ] " + super.getTitel() + ":" + "\n"
                + super.getFrage() + "\n");
        OutputStreamWriter sw = new OutputStreamWriter(stream);
        sw.write(vorderseite.toCharArray());
        sw.write(Arrays.toString(moeglicheAntworten) + "\n");

        if (this.richtigeAntworten.length > 1) {
            sw.write(("(mehrere Antworten moeglich) \n").toCharArray());
        }
        sw.flush();
    }

    public String zeigeVorderseite() {
        String vorderseite = ("[" + super.getId() + ", " + super.getKategorie() + " ] " + super.getTitel() + ":" + "\n"
                + super.getFrage());
        int counter = 1;
        String antworten = "";
        for (int i = 0; i < moeglicheAntworten.length; i++) {
            antworten += counter + ": " + moeglicheAntworten[i];
        }
        // TODO Test der Array-To-String Methode als Alternative
        return vorderseite + antworten;
    }

    public void zeigeRueckseite(OutputStream stream) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(stream);
        String satz = ("Die richtigen Antworten sind:\n");
        sw.write(satz.toCharArray());
        for (int i = 0; i < this.richtigeAntworten.length; i++) {
            sw.write(richtigeAntworten[i] + 1 + ": " + moeglicheAntworten[richtigeAntworten[i]] + "\n");
        }
        sw.flush();
    }

    public String zeigeRueckseite() {
        String rueckseite = "";
        for(int i = 0; i < richtigeAntworten.length; i++) {
            rueckseite = richtigeAntworten[i]+1 + ": " + moeglicheAntworten[richtigeAntworten[i]];
        }
        return rueckseite;
    }

    public void validiere() throws UngueltigeKarteException {
        String fehlerstring = "";
        int counter = 0;
        try {
            super.validiere();
        } catch (UngueltigeKarteException exp) {
            fehlerstring = exp.getMessage();
            for (int i = 0; i < this.moeglicheAntworten.length; i++) {
                if (this.moeglicheAntworten[i].isBlank()) {
                    counter++;
                }
            }
            if (moeglicheAntworten.length < 2) {
                fehlerstring += "Zu wenige moegliche Antworten gegeben.";
            }
            if (fehlerstring.isBlank()) {
                return;
            }
            throw new UngueltigeKarteException(fehlerstring);
        }
    }

    public String exportiereAlsCsv() {
        return (super.exportiereAlsCsv() + "," + Arrays.toString(moeglicheAntworten) + "," + Arrays.toString(richtigeAntworten) + "\n");
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
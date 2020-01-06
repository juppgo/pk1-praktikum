package pk.lkarten.ui;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pk.lkarten.Lernkarte;

public abstract class ErfassungView extends Stage {

	private Lernkarte karte;

	public Lernkarte getKarte() {
		return karte;
	}

	public void setKarte(Lernkarte karte) {
		this.karte = karte;
	}
	
	public void showView() {
		Label labelKategorie = new Label("Kategorie: ");
		TextField tfKategorie = new TextField("");
		
		Label labelTitel = new Label("Titel: ");
		TextField tfTitel = new TextField("");
		
		Label labelFrage = new Label("Frage: ");
		TextField tfFrage = new TextField("");
	}
}

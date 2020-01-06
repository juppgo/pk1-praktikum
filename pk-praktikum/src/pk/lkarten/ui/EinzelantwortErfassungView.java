package pk.lkarten.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import pk.lkarten.EinzelantwortKarte;

public class EinzelantwortErfassungView extends ErfassungView {

	private EinzelantwortKarte karte;

	public EinzelantwortKarte getKarte() {
		return karte;
	}

	public void setKarte(EinzelantwortKarte karte) {
		this.karte = karte;
	}
	
	public void showView() {
		super.showView();
		
		Label labelAntwort = new Label("Antwort: ");
		TextArea taAntwort = new TextArea("");
		
		Button confirm = new Button("Ok");
		Button cancel = new Button("Abbrechen");
	}
}

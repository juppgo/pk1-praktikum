package pk.lkarten.ui;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import pk.lkarten.MehrfachantwortKarte;

public class MehrfachantwortKarteErfassungView extends ErfassungView {

	private MehrfachantwortKarte karte;

	public MehrfachantwortKarte getKarte() {
		return karte;
	}

	public void setKarte(MehrfachantwortKarte karte) {
		this.karte = karte;
	}
	
	public void showView() {
		super.showView();
		
		Label labelAntwort01 = new Label("Antwort 1: ");
		TextArea taAntwort01 = new TextArea("");
		CheckBox cbAntwort01 = new CheckBox();
		Label cblAntwort01 = new Label("Richtig?");
		
		Label labelAntwort02 = new Label("Antwort 2: ");
		TextArea taAntwort02 = new TextArea("");
		CheckBox cbAntwort02 = new CheckBox();
		Label cblAntwort02 = new Label("Richtig?");
		
		Label labelAntwort03 = new Label("Antwort 3: ");
		TextArea taAntwort03 = new TextArea("");
		CheckBox cbAntwort03 = new CheckBox();
		Label cblAntwort03 = new Label("Richtig?");
		
		Label labelAntwort04 = new Label("Antwort 4: ");
		TextArea taAntwort04 = new TextArea("");
		CheckBox cbAntwort04 = new CheckBox();
		Label cblAntwort04 = new Label("Richtig?");
		
		Label labelAntwort05 = new Label("Antwort 5: ");
		TextArea taAntwort05 = new TextArea("");
		CheckBox cbAntwort05 = new CheckBox();
		Label cblAntwort05 = new Label("Richtig?");
	}
}

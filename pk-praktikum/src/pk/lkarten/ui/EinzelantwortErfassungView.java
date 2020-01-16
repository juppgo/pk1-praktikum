package pk.lkarten.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pk.lkarten.EinzelantwortKarte;

public class EinzelantwortErfassungView extends ErfassungView {

	private EinzelantwortKarte karte;
	private TextArea taAntwort;

	public EinzelantwortErfassungView(Stage primaryStage, EinzelantwortKarte karte) {
		super(primaryStage, karte);
		primaryStage.setTitle("Erfassung einer Einzelantwortkarte");
		if (karte == null) {
			taAntwort = new TextArea();
		}
		else {
			taAntwort = new TextArea(karte.getAntwort());
		}
	}

	public EinzelantwortKarte getKarte() {
		return karte;
	}

	public void setKarte(EinzelantwortKarte karte) {
		this.karte = karte;
	}
	
	public void showView() {
		super.showView();
		Label labelAntwort = new Label("Antwort: ");
		gp.add(labelAntwort, 0, 3);
		gp.add(taAntwort, 1,3,1,1);
//		gp.add(controls, 1,4);
		Scene einzelantwortkarte = new Scene(gp);
		setScene(einzelantwortkarte);
		show();
	}
}

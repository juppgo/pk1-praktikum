package pk.lkarten.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pk.lkarten.Lernkarte;

public abstract class ErfassungView extends Stage {

//	private Lernkarte karte;

	protected GridPane gp;
	protected TextField tfKategorie;
	protected TextField tfTitel;
	protected TextField tfFrage;
	protected HBox controls;
	protected Button confirm;
	protected Button cancel;

	public ErfassungView(Stage primaryStage, Lernkarte karte) {
		this.initOwner(primaryStage);
		this.initModality(Modality.WINDOW_MODAL);
		if(karte == null) {
			tfKategorie = new TextField();
			tfTitel = new TextField();
			tfFrage = new TextField();

		}
		else {
			tfKategorie = new TextField(karte.getKategorie());
			tfTitel = new TextField(karte.getTitel());
			tfFrage = new TextField(karte.getFrage());
		}
		gp = new GridPane();
		confirm = new Button("Ok");
		cancel = new Button("Abbrechen");
		HBox controls = new HBox();
		controls.setPadding(new Insets(10,15,10,15));
		controls.setSpacing(10);
		controls.getChildren().addAll(confirm,cancel);
	}
	
	public void showView() {
		Label labelKategorie = new Label("Kategorie: ");
		Label labelTitel = new Label("Titel: ");
		Label labelFrage = new Label("Frage: ");

		gp.add(labelKategorie, 0,0);
		gp.add(labelTitel, 0,1);
		gp.add(labelFrage, 0,2);

		gp.add(tfKategorie,1,0);
		gp.add(tfTitel,1,1);
		gp.add(tfFrage,1,2);

	}
}

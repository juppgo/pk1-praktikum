package pk.lkarten.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import pk.lkarten.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class LernkartenApp extends Application {


	Lernkartei lernkartei = new Lernkartei();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws UngueltigeEingabeException, UngueltigeZahlException, UngueltigeKarteException, DateiBereitsVorhandenException, IOException {
//		EinzelantwortKarte e1 = new EinzelantwortKarte("Kategorie 01", "Titel 01", "Frage 01", "Antwort 01");
//		EinzelantwortErfassungView eview1 = new EinzelantwortErfassungView(primaryStage, e1);
//		eview1.setTitle("Erfassung einer Einzelantwortkarte");
//		eview1.showView();
//		EinzelantwortKarte e2 = new EinzelantwortKarte(null, null, null, null);
//		EinzelantwortErfassungView eview2 = new EinzelantwortErfassungView(primaryStage, e2);
//		eview2.setTitle("Erfassung einer Einzelantwortkarte");
//		eview2.showView();
//		MehrfachantwortKarte m1 = new MehrfachantwortKarte("Kategorie02", "Titel02", "Frage02", new String[]{"a1", "a2", "a3", "a4","a5","a6"}, new  int[]{1,3});
//		MehrfachantwortKarteErfassungView mview1 = new MehrfachantwortKarteErfassungView(primaryStage, m1);
//		mview1.setTitle("Erfassung einer Mehrfachantwortkarte");
//		mview1.showView();
//		MehrfachantwortKarte m2 = new MehrfachantwortKarte("Kategorie02", "Titel02", "Frage02", new String[]{"a1", "a2", "a3", "a4"}, new  int[]{1,3});
//		MehrfachantwortKarteErfassungView mview2 = new MehrfachantwortKarteErfassungView(primaryStage, m2);
//		mview2.setTitle("Erfassung einer Mehrfachantwortkarte");
//		mview2.showView();
//		MehrfachantwortKarte m3 = new MehrfachantwortKarte(null, null, null, new String[]{}, new  int[]{});
//		MehrfachantwortKarteErfassungView mview3 = new MehrfachantwortKarteErfassungView(primaryStage, m3);
//		mview3.setTitle("Erfassung einer Mehrfachantwortkarte");
//		mview3.showView();

		//MENU
		MenuBar menuBar = new MenuBar();
		Menu mDatei = new Menu("Datei");
		Menu mLernkartei = new Menu("Lernkartei");

		MenuItem laden = new MenuItem("Laden");
		MenuItem speichern = new MenuItem("Speichern");
		MenuItem csv = new MenuItem("CSV-Export");
		MenuItem beenden = new MenuItem("Beenden");

		MenuItem einzelkarte = new MenuItem("Einzelantwortkarte hinzufügen");
		MenuItem mehrfachkarte = new MenuItem("Mehrfachantwortkarte hinzufügen");

		mDatei.getItems().addAll(laden, speichern, new SeparatorMenuItem(), csv, new SeparatorMenuItem(), beenden);
		mLernkartei.getItems().addAll(einzelkarte, mehrfachkarte);

		menuBar.getMenus().add(mDatei);
		menuBar.getMenus().add(mLernkartei);

		//Hauptfenster
		ListView<String> listView = new ListView<>();
		ObservableList<Lernkarte> observableList = FXCollections.observableArrayList();

		Button learn = new Button("Lernen!");
		Spinner<Integer> spinner = new Spinner<>();
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(5,15);
		spinner.setValueFactory(valueFactory);
		HBox bottom = new HBox();
		bottom.setPadding(new Insets(10,15,10,15));
		bottom.setSpacing(10);
		bottom.getChildren().addAll(learn,spinner);

		BorderPane bp = new BorderPane(listView);
		bp.setTop(menuBar);
		bp.setBottom(bottom);



		Scene scene = new Scene(bp, 500.0, 500.0);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Lernkarten-App");
		primaryStage.show();

		// Event Handling
		laden.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				lernkartei.laden();
				observableList.addListener(new ListChangeListener<Lernkarte>() {
					@Override
					public void onChanged(Change<? extends Lernkarte> change) {
						while(change.next()) {
							if(change.wasAdded()) {
								List<? extends Lernkarte> sublist = change.getAddedSubList();
								for (Lernkarte a: sublist) {
									listView.getItems().add(a.toString());
								}
							}
						}
					}
				});
				// TODO Hashset in listView schreiben. Vll mit Iterator?
//				for (lernkartei l: karten) {
//					listView.getItems().add((String)lernkartei.getKarten());
//				}
//				while(lernkartei.getIterator() != null) {
//					listView.getItems().add(lernkartei.getIterator().toString());
//				}


			}
		});

		speichern.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				lernkartei.speichern();
			}
		});

		csv.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent)  {
				String datei = "";
				do {
					try {
						datei = DialogUtil.showInputDialog(null, "Bitte geben Sie einen Dateinamen ein:");
						if (datei == null) {
							datei = "";
							break;
						}
						if (datei.isBlank()) {
							throw new UngueltigeEingabeException("Bitte geben Sie einen gültigen Dateinamen ein.");
						}
						String pathcheck = "/home/chris/pk1-praktikum/" + datei + ".csv";
						Path path = Paths.get(pathcheck);
						if (Files.exists(path)) {
							throw new DateiBereitsVorhandenException("Wollen Sie die existierende Datei wirklich überschreiben?");
						}
					} catch (UngueltigeEingabeException e) {
						DialogUtil.showMessageDialog(null, e.getMessage());
					} catch (DateiBereitsVorhandenException e) {
						boolean result = DialogUtil.showConfirmDialog("Datei bereits vorhanden.", e.getMessage());
						if (result == false) {
							datei = "";
						} else {
							try {
								exportiere(datei);
							} catch (IOException ex) {
								ex.printStackTrace();
							}
						}
					}
				} while (datei.isBlank());
				try {
					exportiere(datei);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		beenden.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				Platform.exit();
			}
		});

		einzelkarte.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				EinzelantwortErfassungView eview = new EinzelantwortErfassungView(primaryStage, null);
				eview.setTitle("Erfassung einer Einzelantwortkarte");
				eview.showView();
				eview.confirm.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent actionEvent) {
						String kategorie = eview.tfKategorie.getText();
						String titel = eview.tfTitel.getText();
						String frage = eview.tfFrage.getText();
						String antwort = eview.taAntwort.getText();
						try {
							EinzelantwortKarte karte = new EinzelantwortKarte(kategorie, titel, frage, antwort);
							lernkartei.hinzufuegen(karte);
							listView.getItems().add(karte.toString());
							eview.close();
						} catch (UngueltigeKarteException e) {
							//TODO Exception entsprechend der Klasse Menü implementieren
							DialogUtil.showMessageDialog("Ungueltige Eingabe", e.getFehlerAusgabe());
						}
					}
				});
				eview.cancel.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent actionEvent) {
						eview.close();
					}
				});
			}
		});

		mehrfachkarte.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				MehrfachantwortKarteErfassungView mview = new MehrfachantwortKarteErfassungView(primaryStage, null);
				mview.setTitle("Erfassung einer Mehrfachantwortkarte");
				mview.showView();
				mview.confirm.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent actionEvent) {
						String kategorie = mview.tfKategorie.getText();
						String titel = mview.tfTitel.getText();
						String frage = mview.tfFrage.getText();
						String antwort01 = mview.taAntwort01.getText();
						String antwort02 = mview.taAntwort02.getText();
						String antwort03 = mview.taAntwort03.getText();
						String antwort04 = mview.taAntwort04.getText();
						String antwort05 = mview.taAntwort05.getText();
						String[] moeglicheantworten = {antwort01,antwort02,antwort03,antwort04,antwort05};
						ArrayList<Integer> hilfsArray = new ArrayList<>();
						if(mview.cbAntwort01.isSelected()) {
							hilfsArray.add(0);
						}
						if(mview.cbAntwort02.isSelected()) {
							hilfsArray.add(1);
						}
						if(mview.cbAntwort03.isSelected()) {
							hilfsArray.add(2);
						}
						if(mview.cbAntwort04.isSelected()) {
							hilfsArray.add(3);
						}
						if(mview.cbAntwort05.isSelected()) {
							hilfsArray.add(4);
						}
						int[] richtigeAntworten = new int[hilfsArray.size()];
						for(int i = 0; i < richtigeAntworten.length; i++) {
							richtigeAntworten[i] = hilfsArray.get(i);
						}
						try {
							MehrfachantwortKarte karte = new MehrfachantwortKarte(kategorie,titel,frage,moeglicheantworten,richtigeAntworten);
							lernkartei.hinzufuegen(karte);
							listView.getItems().add(karte.toString());
							mview.close();
						} catch (UngueltigeKarteException e) {
							DialogUtil.showMessageDialog("Ungueltige Eingabe", e.getFehlerAusgabe());
						}
					}
				});
				mview.cancel.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent actionEvent) {
						mview.close();
					}
				});
			}
		});
	}

	private void exportiere(String datei) throws IOException {
		lernkartei.exportiereEintraegeAlsCsv(new File(datei));
	}
}

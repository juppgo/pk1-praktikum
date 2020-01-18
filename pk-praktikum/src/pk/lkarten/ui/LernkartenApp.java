package pk.lkarten.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pk.lkarten.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

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
		//TODO Einbinden der Objekte in die List-View
		ListView<String> listView = new ListView<>();
//		for (String i: lernkarte) {
//			listView.getItems().add(i);
//		}

		BorderPane bp = new BorderPane(listView);
		bp.setTop(menuBar);

		Scene scene = new Scene(bp, 500.0, 500.0);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Lernkarten-App");
		primaryStage.show();

		// Event Handling
		laden.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				lernkartei.laden();
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
		}) ;

		beenden.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				Platform.exit();
				System.exit(0);
			}
		});

		einzelkarte.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				EinzelantwortErfassungView eview = new EinzelantwortErfassungView(primaryStage, null);
				eview.setTitle("Erfassung einer Einzelantwortkarte");
				eview.showView();
			}
		});

		mehrfachkarte.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				MehrfachantwortKarteErfassungView mview = new MehrfachantwortKarteErfassungView(primaryStage, null);
				mview.setTitle("Erfassung einer Mehrfachantwortkarte");
				mview.showView();

			}
		});
	}

	private void exportiere(String datei) throws IOException {
		lernkartei.exportiereEintraegeAlsCsv(new File(datei));
	}
}

package pk.lkarten.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import pk.lkarten.EinzelantwortKarte;
import pk.lkarten.MehrfachantwortKarte;


public class LernkartenApp extends Application {


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		EinzelantwortKarte e1 = new EinzelantwortKarte("Kategorie 01", "Titel 01", "Frage 01", "Antwort 01");
		EinzelantwortErfassungView eview1 = new EinzelantwortErfassungView(primaryStage, e1);
		eview1.setTitle("Erfassung einer Einzelantwortkarte");
		eview1.showView();
		EinzelantwortKarte e2 = new EinzelantwortKarte(null, null, null, null);
		EinzelantwortErfassungView eview2 = new EinzelantwortErfassungView(primaryStage, e2);
		eview2.setTitle("Erfassung einer Einzelantwortkarte");
		eview2.showView();
		MehrfachantwortKarte m1 = new MehrfachantwortKarte("Kategorie02", "Titel02", "Frage02", new String[]{"a1", "a2", "a3", "a4","a5","a6"}, new  int[]{1,3});
		MehrfachantwortKarteErfassungView mview1 = new MehrfachantwortKarteErfassungView(primaryStage, m1);
		mview1.setTitle("Erfassung einer Mehrfachantwortkarte");
		mview1.showView();
		MehrfachantwortKarte m2 = new MehrfachantwortKarte("Kategorie02", "Titel02", "Frage02", new String[]{"a1", "a2", "a3", "a4"}, new  int[]{1,3});
		MehrfachantwortKarteErfassungView mview2 = new MehrfachantwortKarteErfassungView(primaryStage, m2);
		mview2.setTitle("Erfassung einer Mehrfachantwortkarte");
		mview2.showView();
		MehrfachantwortKarte m3 = new MehrfachantwortKarte(null, null, null, new String[]{}, new  int[]{});
		MehrfachantwortKarteErfassungView mview3 = new MehrfachantwortKarteErfassungView(primaryStage, m3);
		mview3.setTitle("Erfassung einer Mehrfachantwortkarte");
		mview3.showView();
	}



}

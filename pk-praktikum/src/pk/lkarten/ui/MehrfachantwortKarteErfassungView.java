package pk.lkarten.ui;

import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import pk.lkarten.MehrfachantwortKarte;

public class MehrfachantwortKarteErfassungView extends ErfassungView {

    private MehrfachantwortKarte karte;

    private Label labelAntwort01;
    private Label labelAntwort02;
    private Label labelAntwort03;
    private Label labelAntwort04;
    private Label labelAntwort05;

    protected TextArea taAntwort01;
    protected TextArea taAntwort02;
    protected TextArea taAntwort03;
    protected TextArea taAntwort04;
    protected TextArea taAntwort05;

    protected CheckBox cbAntwort01;
    protected CheckBox cbAntwort02;
    protected CheckBox cbAntwort03;
    protected CheckBox cbAntwort04;
    protected CheckBox cbAntwort05;


    public MehrfachantwortKarteErfassungView(Stage primaryStage, MehrfachantwortKarte karte) {
        super(primaryStage, karte);
        primaryStage.setTitle("Erfassung einer Mehrfachantwortkarte");
        cbAntwort01 = new CheckBox("Richtig?");
        cbAntwort02 = new CheckBox("Richtig?");
        cbAntwort03 = new CheckBox("Richtig?");
        cbAntwort04 = new CheckBox("Richtig?");
        cbAntwort05 = new CheckBox("Richtig?");
        if(karte == null) {
            taAntwort01 = new TextArea("");
            taAntwort02 = new TextArea("");
            taAntwort03 = new TextArea("");
            taAntwort04 = new TextArea("");
            taAntwort05 = new TextArea("");
        }
        else {
            String[] moeglicheAntworten = karte.getMoeglicheAntworten();
            int[] richtigeAntworten = karte.getRichtigeAntworten();
            int i = 0;
            while (i <= 4) {
                switch (i) {
                    case 0:
                        if (moeglicheAntworten.length >= 1) {
                            taAntwort01 = new TextArea(moeglicheAntworten[i]);

                        } else {
                            taAntwort01 = new TextArea("");
                        }
                        break;
                    case 1:
                        if (moeglicheAntworten.length >= 2) {
                            taAntwort02 = new TextArea(moeglicheAntworten[i]);

                        } else {
                            taAntwort02 = new TextArea("");
                        }
                        break;
                    case 2:
                        if (moeglicheAntworten.length >= 3) {
                            taAntwort03 = new TextArea(moeglicheAntworten[i]);

                        } else {
                            taAntwort03 = new TextArea("");
                        }
                        break;
                    case 3:
                        if (moeglicheAntworten.length >= 4) {
                            taAntwort04 = new TextArea(moeglicheAntworten[i]);

                        } else {
                            taAntwort04 = new TextArea("");
                        }
                        break;
                    case 4:
                        if (moeglicheAntworten.length >= 5) {
                            taAntwort05 = new TextArea(moeglicheAntworten[i]);

                        } else {
                            taAntwort05 = new TextArea("");
                        }
                        break;
                }
                i++;
            }
            for (int j : richtigeAntworten) {
                if (j == 0) {
                    cbAntwort01.setSelected(true);
                }
                if (j == 1) {
                    cbAntwort02.setSelected(true);
                }
                if (j == 2) {
                    cbAntwort03.setSelected(true);
                }
                if (j == 3) {
                    cbAntwort04.setSelected(true);
                }
                if (j == 4) {
                    cbAntwort05.setSelected(true);
                }
            }
        }

    }


    public void showView() {
        super.showView();
        labelAntwort01 = new Label("Antwort 1: ");
        labelAntwort02 = new Label("Antwort 2: ");
        labelAntwort03 = new Label("Antwort 3: ");
        labelAntwort04 = new Label("Antwort 4: ");
        labelAntwort05 = new Label("Antwort 5: ");

        gp.add(labelAntwort01, 0, 3);
        gp.add(labelAntwort02, 0, 4);
        gp.add(labelAntwort03, 0, 5);
        gp.add(labelAntwort04, 0, 6);
        gp.add(labelAntwort05, 0, 7);

        gp.add(taAntwort01, 1, 3);
        gp.add(taAntwort02, 1, 4);
        gp.add(taAntwort03, 1, 5);
        gp.add(taAntwort04, 1, 6);
        gp.add(taAntwort05, 1, 7);

        gp.add(cbAntwort01, 2, 3);
        gp.add(cbAntwort02, 2, 4);
        gp.add(cbAntwort03, 2, 5);
        gp.add(cbAntwort04, 2, 6);
        gp.add(cbAntwort05, 2, 7);

        gp.add(controls, 1,8);

        Scene mehrfachkarte = new Scene(gp);
        setScene(mehrfachkarte);
        show();

    }
}

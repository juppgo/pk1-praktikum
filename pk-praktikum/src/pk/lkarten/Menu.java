package pk.lkarten;

import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Menu {

	Lernkartei lernkartei = new Lernkartei();
	Scanner sc = new Scanner(System.in);

	public void liesEingabe() throws UngueltigeEingabeException, UngueltigeZahlException, UngueltigeKarteException {
		// Anlegen einer Einzelantwortkarte, sowie Ausgabe der
		// gibAnzahlKarten()-Methode, um alle Methoden der Lernkartei aufzurufen
		lernkartei.hinzufuegen(new EinzelantwortKarte("Kategorie 01", "Titel 01", "Frage 01", "Antwort 01"));
		lernkartei.hinzufuegen(new EinzelantwortKarte("Kategorie 02", "Titel 02", "Frage 02", "Antwort 02"));
		lernkartei.hinzufuegen(new EinzelantwortKarte("Kategorie 03", "Titel 03", "Frage 03", "Antwort 03"));
		lernkartei.hinzufuegen(new EinzelantwortKarte("Kategorie 04", "Titel 04", "Frage 04", "Antwort 04"));
		System.out.println("Test der Klasse gibAnzahlKarten()");
		System.out.println(lernkartei.gibAnzahlKarten() + "\n");
		showMenu();
		while (true) {
			int i = 0;
			try {
				String s = sc.nextLine();
				if (s.matches("[0-9]")) {
					i = Integer.parseInt(s);
				} else
					throw new UngueltigeZahlException("Keine valide Zahl");
				if (i > 5 || i < 0) {
					throw new UngueltigeEingabeException("Fuer diese Zahl gibt es keinen Men�eintrag");
				}
			} catch (UngueltigeZahlException | UngueltigeEingabeException exp) {
				System.err.println(exp.getMessage());
				showMenu();
				continue;
			}
			switch (i) {
			case 1:
				ArrayList<Lernkarte> karten = lernkartei.erzeugeDeck(5);
				for (Lernkarte karte : karten) {
					karte.zeigeVorderseite();
					System.out.println("<Druecken Sie Enter, um die Rueckseite der Karte zu sehen.>");
					warteAufEnter();
					karte.zeigeRueckseite();
					System.out.println("<Druecken Sie Enter, um die naechste Karte zu sehen.>");
					warteAufEnter();
				}
				System.out.println("<Alle Karten betrachtet.>");
				showMenu();
				break;
			case 2:
				try {
					String kategorie = JOptionPane.showInputDialog(null, "Bitte geben Sie eine Kategorie ein:");
					String titel = JOptionPane.showInputDialog(null, "Bitte geben Sie einen Titel ein:");
					String frage = JOptionPane.showInputDialog(null, "Bitte geben Sie eine Frage ein:");
					String antwort = JOptionPane.showInputDialog(null, "Bitte geben Sie eine Antwort ein:");
					lernkartei.hinzufuegen(new EinzelantwortKarte(kategorie, titel, frage, antwort));
				} catch (UngueltigeKarteException exp) {
					JOptionPane.showMessageDialog(null, exp.getFehlerAusgabe(), "Warnung!", JOptionPane.WARNING_MESSAGE,
							null);
				}
				showMenu();
				break;
			case 3:
				lernkartei.druckeAlleKarten();
				showMenu();
				break;
			case 4:
				String auswahlKategorie = JOptionPane.showInputDialog(null,
						"Bitte geben Sie die gewuenschte Kategorie ein:");
				lernkartei.gibKartenZuKategorie(auswahlKategorie);
				for (Lernkarte karte : lernkartei.gibKartenZuKategorie("Kategorie 01")) {
					karte.druckeKarte();
				}
				showMenu();
				break;
			case 5:
				sc.close();
				return;
			}
		}
	}

	private void showMenu() {
		System.out.println(
				"Lernkarten-App\n" + "1. Lernen! \n" + "2. Einzelantwortkarte hinzufuegen\n" + "3. Drucke alle Karten\n"
						+ "4. Drucke Karten zu Kategorie\n" + "5. Beenden\n" + "Bitte Aktion waehlen:");
	}

	private void warteAufEnter() {
		try {
			System.in.read(new byte[2]);
		} catch (IOException e) {
			System.err.println("Fehler: " + e.getMessage());
		}
	}

}

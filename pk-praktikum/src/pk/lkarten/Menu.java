package pk.lkarten;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

	Lernkartei lernkartei = new Lernkartei();
	Scanner sc = new Scanner(System.in);

	public void liesEingabe() throws UngueltigeEingabeException, UngueltigeZahlException, UngueltigeKarteException, DateiBereitsVorhandenException {
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
				if (i > 6 || i < 0) {
					throw new UngueltigeEingabeException("Fuer diese Zahl gibt es keinen Menueeintrag");
				}
			} catch (UngueltigeZahlException | UngueltigeEingabeException e) {
				System.err.println(e.getMessage());
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
				for (Lernkarte karte : lernkartei.gibKartenZuKategorie(auswahlKategorie)) {
					karte.druckeKarte();
				}
				showMenu();
				break;
			case 5:
				String datei ="";
				do {
					try {
						datei = JOptionPane.showInputDialog(null, "Bitte geben Sie einen Dateinamen ein:");
						if(datei == null) {
							datei = "";
							break;
						}
						if(datei.isBlank()) {
							throw new UngueltigeEingabeException("Bitte geben Sie einen gültigen Dateinamen ein.");
						}
						String pathcheck = "/home/chris/pk1-praktikum/pk-praktikum/src/pk/lkarten/" + datei + ".csv";
						Path path = Paths.get(pathcheck);
						if(Files.exists(path)) {
							throw new DateiBereitsVorhandenException("Wollen Sie die existierende Datei wirklich überschreiben?");
						}
					}
					catch (UngueltigeEingabeException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					catch (DateiBereitsVorhandenException e) {
						int result = JOptionPane.showConfirmDialog(null, e.getMessage(), "Datei bereits vorhanden", JOptionPane.YES_NO_OPTION);
						if(result == JOptionPane.NO_OPTION) {
							datei ="";
						}
						else {
							exportiere(datei);
						}
					}
				} while(datei.isBlank());
				exportiere(datei);
				showMenu();
				break;
			case 6:
			sc.close();
			return;
			}
		}
	}

	private void exportiere(String datei) {
		lernkartei.exportiereEintraegeAlsCsv(new File(datei));
	}

	private void showMenu() {
		System.out.println(
				"Lernkarten-App\n" + "1. Lernen! \n" + "2. Einzelantwortkarte hinzufuegen\n" + "3. Drucke alle Karten\n"
						+ "4. Drucke Karten zu Kategorie\n" + "5. CSV-Export\n" + "6. Beenden\n" + "Bitte Aktion waehlen:");
	}

	private void warteAufEnter() {
		try {
			System.in.read(new byte[2]);
		} catch (IOException e) {
			System.err.println("Fehler: " + e.getMessage());
		}
	}

}

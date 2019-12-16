package pk.lkarten;

import java.io.IOException;

public class Anwendung {

	public static void main(String[] args) throws UngueltigeEingabeException, UngueltigeZahlException, UngueltigeKarteException, DateiBereitsVorhandenException, IOException {
		Menu menu = new Menu();
		menu.liesEingabe();
	}
}


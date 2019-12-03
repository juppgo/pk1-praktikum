package pk.lkarten;

public class Anwendung {

	public static void main(String[] args) throws UngueltigeEingabeException, UngueltigeZahlException, UngueltigeKarteException {
		Menu menu = new Menu();
		menu.liesEingabe();
	}
}


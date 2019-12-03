package pk.lkarten;

import java.util.ArrayList;
import java.util.Iterator;

public class UngueltigeKarteException extends Exception {

	private static ArrayList<String> fehler = new ArrayList<>();


	public UngueltigeKarteException() { super(); }
	public UngueltigeKarteException(String message) {
		super(message);
		fehler.add(message);
	}

//	public UngueltigeKarteException(String message, Throwable cause) {
//		fehler.add(message, cause);
//	}


	
	public String getFehlerAusgabe() {
		String fehlermeldung = "";
		Iterator<String> fehlerIterator = fehler.listIterator(0);
		while (fehlerIterator.hasNext()) {
			fehlermeldung += fehler.listIterator();
		}
		return fehlermeldung;
	}
}

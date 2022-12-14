package pk.lkarten;

import java.util.ArrayList;

public class UngueltigeKarteException extends Exception {

	private ArrayList<String> fehler = new ArrayList<>();

	public UngueltigeKarteException(String message) {
		super(message);
		fehler.add(message);
	}

	public String getFehlerAusgabe() {
		return String.join(", ", fehler);
	}
}

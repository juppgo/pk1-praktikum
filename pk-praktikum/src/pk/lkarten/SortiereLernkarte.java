package pk.lkarten;

import java.util.Comparator;

public class SortiereLernkarte implements Comparator<Lernkarte> {

	@Override
	public int compare(Lernkarte o1, Lernkarte o2) {
//		if(o1.getId() > o2.getId()) {
//			return -1;
//		}
//		else if (o1.getId() < o2.getId()) {
//			return 1;
//		}
//		else return 0;
		
		return -(Integer.compare(o1.getId(), o2.getId()));
	}
}

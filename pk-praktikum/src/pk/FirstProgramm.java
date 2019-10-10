package pk;

public class FirstProgramm {

	public static void main(String[] args) {
		maleTreppe(8, 3);
	}
// --- Kommentar hinzugefügt --- //
	public static void maleTreppe(int hoehe, int stufentiefe) {
		char[][] array = new char[hoehe][hoehe * stufentiefe + 1];
		int counter = stufentiefe;
		for (int i = 0; i < array.length; i++) {
			for (int j = 1; j < array[i].length; j++) {
				if (j <= stufentiefe) {
					array[i][j] = '*';
				} else
					array[i][j] = ' ';
			}
			stufentiefe += counter;
		}
		char[][] a = new char[array.length][array[0].length];
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[i].length; j++) {
				a[i][j] = array[i][array[i].length-j-1];
			}
		}
		
		for (int i = 0; i < a.length; i++) {
			System.out.println();
			for (int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j]);
			}
		}
	}
}

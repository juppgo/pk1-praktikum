package pk;

public class FirstProgramm {

	public static void main(String[] args) {
		maleTreppe(6, 2);
	}

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
		for (int i = 0; i < array.length; i++) {
			System.out.println();
			for (int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j]);
			}
		}
	}
}

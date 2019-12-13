package fourWayFour;

/**
 * Sammlung der Konsolen-Ausgaben
 */
public class OutputCollection {

	/**
	 * Ausgabe von String s mit Absatz
	 * 
	 * @param s
	 */
	protected static void outputWithNewLine(String s) {
		System.out.println(s);
	}

	/**
	 * Ausgabe von Absatz
	 */
	protected static void outputWithNewLine() {
		System.out.println();
	}

	/**
	 * Ausgabe von String s
	 * 
	 * @param s
	 */
	protected static void output(String s) {
		System.out.print(s);
	}
}

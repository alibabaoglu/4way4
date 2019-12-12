package fourWayFour;

/**
 * Spielbrett 4 Way 4
 */
public class GameBoard {
	private String[][] board; // charAt
	private int hoehe, breite;

	/**
	 * Konstruktor mit zwei Parameter für Zeile und Spalte
	 * 
	 * @param hoehe
	 * @param breite
	 */
	private GameBoard(int hoehe, int breite) {
		this.hoehe = (hoehe + 2); // +2 für den Rahmen
		this.breite = (breite + 2); // +2 für den Rahmen
		this.board = new String[this.hoehe][this.breite];
	}

	/**
	 * Factory Methode zum erstellen eines Spielbretts
	 * 
	 * @param hoehe
	 * @param breite
	 * @return das neu erstellte GameBoard
	 */
	public static GameBoard createBoard(int hoehe, int breite) {

		if (hoehe >= 7 && hoehe <= 10 && breite >= 7 && breite <= 10) {
			GameBoard gb = new GameBoard(hoehe, breite);

			// 4 Leere Ecken fuer das Koordinatensystem
			gb.board[0][0] = "  ";
			gb.board[gb.hoehe - 1][0] = "  ";
			gb.board[0][gb.breite - 1] = "  ";
			gb.board[gb.hoehe - 1][gb.breite - 1] = "  ";

			// Koordinatensystem des Boards bestehend aus Buchstaben und Zahlen
			for (int i = 0; i < gb.board.length; i++) {
				if (gb.board[i][0] == null) {

					if (hoehe == 10 && i == 1) {
						gb.board[1][0] = "10";
						gb.board[1][gb.board[1].length - 1] = "10";
					} else {
						gb.board[i][0] = "" + (1 + gb.board.length - 2 - i) + " ";
						gb.board[i][gb.board[i].length - 1] = "" + (1 + gb.board.length - 2 - i) + " ";
					}
				}
				for (int j = 0; j < gb.board[i].length; j++) {
					if (gb.board[i][j] == null) {
						if (i == 0 || i == gb.board.length - 1)
							gb.board[i][j] = "" + (char) ('a' + j - 1);
					}
				}

			}
			for (int i = 0; i < gb.board.length; i++) {
				for (int j = 0; j < gb.board[i].length; j++) {
					if (gb.board[i][j] == null) {
						gb.board[i][j] = " ";
					}
				}
			}
			return gb;
		} else {
			throw new GameException("Spielbrettgroesse stimmt nicht!");
		}
	}

	/**
	 * Erstellt ein Rahmengitter und gibt das Board aus
	 */
	@Override
	public void printBoard() {
		Ausgabensammlung.ausgabeMitAbsatz();
		String rahmen = " ";

		for (int i = 0; i < this.breite; i++) {
			rahmen += "͞͞ ͞ ͞ ͞ ͞ ͞ ͞ ͞|";
		}

		for (int i = 0; i < this.board.length; i++) {
			Ausgabensammlung.ausgabeMitAbsatz(rahmen);
			for (int j = 0; j < this.board[i].length; j++) {
				if (j == this.board[i].length - 1)
					Ausgabensammlung.ausgabe("   " + this.board[i][j] + "  |");
				else
					Ausgabensammlung.ausgabe("   " + this.board[i][j] + "   |");
			}
			Ausgabensammlung.ausgabeMitAbsatz();
		}
		Ausgabensammlung.ausgabeMitAbsatz(rahmen);
	}

	/**
	 * Setzt den Stein (X/O) auf das Spielbrett mithilfe der übergebenen
	 * "Koordinaten"
	 * 
	 * @param spieler
	 * @param nummer
	 * @param buchstabe
	 */
	public void setStein(char spieler, int nummer, char buchstabe) {
		char richtung;
		if (buchstabe == 'a') {
			richtung = 'r';
		}
		if (buchstabe == (char) ('a' + this.breite - 3)) {
			richtung = 'l';
		}
		if (nummer == 1) {
			richtung = 'u';
		} else { // (nummer == gewählte höhe)
			richtung = 'd';
		}
		setStein(spieler, nummer, buchstabe, richtung);
	}

	/**
	 * Setzt den Stein (X/O) auf das Spielbrett mithilfe der übergebenen
	 * "Koordinaten" und der Richtung in die der Stein fallen soll
	 * 
	 * @param spieler
	 * @param nummer
	 * @param buchstabe
	 * @param richtung
	 */
	public void setStein(char spieler, int nummer, char buchstabe, char richtung) {
		int x = this.hoehe - (nummer + 1);
		int y = buchstabe - 96;
		this.board[x][y] = "" + spieler;
//		this.board = shiftBoard(richtung);
		this.printBoard();
	}
}

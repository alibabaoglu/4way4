package fourWayFour;

/**
 * Spielbrett 4 Way 4
 * 
 * @author Ali Babaoglu
 * @version 22.11.2019 Prototyp 1
 */
public class GameBoard {
	private char[][] board;
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
		this.board = new char[this.hoehe][this.breite];
	}

	/**
	 * Factory Methode zum erstellen eines Spielbretts
	 * 
	 * @param hoehe
	 * @param breite
	 * @return das neu erstellte GameBoard
	 */
	public static GameBoard createBoard(int hoehe, int breite) {

		GameBoard gb = new GameBoard(hoehe, breite);

		// 4 Leere Ecken für das Koordinatensystem
		gb.board[0][0] = ' ';
		gb.board[gb.hoehe - 1][0] = ' ';
		gb.board[0][gb.breite - 1] = ' ';
		gb.board[gb.hoehe - 1][gb.breite - 1] = ' ';

		// Koordinatensystem des Boards bestehend aus Buchstaben und Zahlen
		for (int i = 0; i < gb.board.length; i++) {
			if (gb.board[i][0] == 0) {

				gb.board[i][0] = (char) ('1' + gb.board.length - 2 - i);
				gb.board[i][gb.board[i].length - 1] = (char) ('1' + gb.board.length - 2 - i);
			}
			for (int j = 0; j < gb.board[i].length; j++) {
				if (gb.board[i][j] == 0) {
					if (i == 0 || i == gb.board.length - 1)
						gb.board[i][j] = (char) ('a' + j - 1);
				}
			}
		}
		return gb;
	}

	public void setStein(int x, char b) {
		b -= '0' * 2;
		int y = b;
		int i = 0;

		char zeile = (char) (x + '0');

		while (this.board[i][0] != zeile)
			i++;

		this.board[i][y] = 'O';

	}

	/**
	 * Erstellt ein Rahmengitter und gibt das Board aus
	 */
	public void printBoard() {
		Ausgabensammlung.ausgabeMitAbsatz();
		String rahmen = " ";

		for (int i = 0; i < this.breite - 1; i++) {
			rahmen += "͞͞ ͞ ͞|͞ ͞͞͞ ͞ ͞ ";
		}
		for (int i = 0; i < this.board.length; i++) {
			Ausgabensammlung.ausgabeMitAbsatz(rahmen + "͞ |");
			for (int j = 0; j < this.board[i].length; j++) {
				if (j == this.board[i].length - 1)
					Ausgabensammlung.ausgabe(this.board[i][j] + " |");
				else
					Ausgabensammlung.ausgabe(this.board[i][j] + "  |   ");
			}
			Ausgabensammlung.ausgabeMitAbsatz();
		}
		Ausgabensammlung.ausgabeMitAbsatz(rahmen);
	}

	public static void main(String[] args) {
		GameBoard gb = GameBoard.createBoard(7, 9);
		gb.setStein(1, 'd');
		gb.printBoard();
	}
}

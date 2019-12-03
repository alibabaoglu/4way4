package fourWayFour;

/**
 * Spielbrett 4 Way 4
 * 
 * @author Ali Babaoglu
 * @version 03.12.2019 Prototyp 2
 */
public class GameBoard {
	private char[][] board;
	private int hoehe, breite;
	private boolean up, down, left, right;

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

	// verschiebe alle Steine in die angegebene Richtung bekommt als param die
	// Koordinaten

	private void verschiebeSteine(int x, int y) {
//TODO: Anpassen an das Board Algorithmus stimmt aber wurde erstellt nur für eine zeile.
		if (up) {
			int i, j;
			for (int z = 1; z < board[0].length - 2; z++) {
				i = 1;
				while (i < board.length - 2 && board[i][z] == 0) {
					i++;
				}
				if (board[i][z] != 0) {

					board[i][z] = 0;
					j = i;
					while (board[j][z] == 0 && j > 0)
						if (board[j - 1][z] == 0)
							j--;
						else
							break;
					board[j][z] = 'X';

				}

			}
			}
	
		
	}
	//Bestimmt in welche Richtung sich die Steine verschieben.

	private void direction(int x, int y) {
		int maxCol = board[0].length - 2;
		int maxRow = board.length - 2;

		up = false;
		down = false;
		left = false;
		right = false;

		if (x != 1 && x != maxRow) {
			if (y == 1) {
				right = true;
			} else if (y == maxCol) {
				left = true;
			}
		}
		if (y != 1 && y != maxCol) {
			if (x == maxRow) {
				up = true;
			} else if (x == 1) {
				down = true;
			}
		}

	}

	// Prüft ob ein Zug gültig ist. Nicht gültig wenn Reihe/Spalte voll.
	private boolean isValid(int x, int y) {
		direction(x, y);

		if (up) {
			for (int i = x; i > 0; i--) {
				if (board[i][y] == 0)
					return true;
			}
		} else if (down) {
			for (int i = x; i < board.length; i++) {
				if (board[i][y] == 0)
					return true;
			}
		} else if (left) {
			for (int i = y; i > 0; y--) {
				if (board[x][i] == 0)
					return true;
			}
		} else if (right) {
			for (int i = y; i < board[0].length; y++) {
				if (board[x][i] == 0)
					return true;
			}
		}

		return false;
	}
	//setzt ein Stein

	public void setStein(int x, char b) {
		b -= '0' * 2;
		int y = b;
		int i = 0;

		char zeile = (char) (x + '0');

		while (this.board[i][0] != zeile)
			i++;
		if (this.board[i][y] == 0)
			board[i][y] = 'X';
		
		if (isValid(i, y)) {
		verschiebeSteine(i, y);

		} else
			throw new IllegalArgumentException("Zug nicht gültig");

	}

	/**
	 * Erstellt ein Rahmengitter und gibt das Board aus
	 */
	public void printBoard() {
		System.out.println();
		String rahmen = " ";

		for (int i = 0; i < this.breite - 1; i++) {
			rahmen += "͞͞ ͞ ͞|͞ ͞͞͞ ͞ ͞ ";
		}
		for (int i = 0; i < this.board.length; i++) {
			System.out.println(rahmen + "͞ |");
			for (int j = 0; j < this.board[i].length; j++) {
				if (j == this.board[i].length - 1)
					System.out.print(this.board[i][j] + " |");
				else
					System.out.print(this.board[i][j] + "  |   ");
			}
			System.out.println();
		}
		System.out.println(rahmen);
	}

	public static void main(String[] args) {
		GameBoard gb = GameBoard.createBoard(7, 9);
		gb.setStein(1, 'b');
	
		
		
		
		gb.printBoard();
	}
}

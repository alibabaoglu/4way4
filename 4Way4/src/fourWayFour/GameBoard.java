package ki2;

/**
 * Spielbrett von 4 Way 4
 */
public class GameBoard {
	protected String[][] board;
	protected int height, width;

	/**
	 * Konstruktor mit zwei Parameter für Zeile und Spalte
	 * 
	 * @param hight
	 * @param width
	 */
	private GameBoard(int hight, int width) {
		this.height = (hight + 2); // +2 für den Rahmen
		this.width = (width + 2); // +2 für den Rahmen
		this.board = new String[this.height][this.width];
	}

	/**
	 * Factory Methode zum erstellen eines Spielbretts
	 * 
	 * @param height
	 * @param width
	 * @return das neu erstellte Spielbrett
	 */
	public static GameBoard createBoard(int height, int width) {

		if (height >= 7 && height <= 10 && width >= 7 && width <= 10) {
			GameBoard gb = new GameBoard(height, width);

			// 4 Leere Ecken fuer das Koordinatensystem
			gb.board[0][0] = "  ";
			gb.board[gb.height - 1][0] = "  ";
			gb.board[0][gb.width - 1] = "  ";
			gb.board[gb.height - 1][gb.width - 1] = "  ";

			// Koordinatensystem des Spielbretts bestehend aus Buchstaben und Zahlen
			for (int i = 0; i < gb.board.length; i++) {
				if (gb.board[i][0] == null) {

					if (height == 10 && i == 1) {
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
	 * Erstellt ein Rahmengitter und gibt es gemeinsam mit dem Spielbrett aus
	 */
	public void printBoard() {
		OutputCollection.outputWithNewLine();
		String frame = " ";

		for (int i = 0; i < this.width; i++) {
			frame += "͞͞ ͞ ͞ ͞ ͞ ͞ ͞ ͞|";
		}

		for (int i = 0; i < this.board.length; i++) {
			OutputCollection.outputWithNewLine(frame);
			for (int j = 0; j < this.board[i].length; j++) {
				if (j == this.board[i].length - 1)
					OutputCollection.output("   " + this.board[i][j] + "  |");
				else
					OutputCollection.output("   " + this.board[i][j] + "   |");
			}
			OutputCollection.outputWithNewLine();
		}
		OutputCollection.outputWithNewLine(frame);
		OutputCollection.outputWithNewLine();
	}

	/**
	 * Clont das GameBoard
	 * 
	 * @return gibt ein neues GameBoard mit den selben Werten zurueck
	 */
	public GameBoard cloneBoard() {
		GameBoard clone = GameBoard.createBoard(this.height - 2, this.width - 2);

		for (int i = 0; i < this.height; i++) {
			for (int j = 1; j < this.width; j++) {
				clone.board[i][j] = this.board[i][j];
			}
		}
		return clone;
	}
}

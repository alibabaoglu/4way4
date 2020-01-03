package fourWayFour;

/**
 * Das gesamte Spiel
 *
 */
public class GameEngine implements Requirements {
	private GameBoard gb;
	private Game game;
	private KI com;
	private int counter, mode;
	private String beginner;

	/**
	 * Setzt den Stein (X/O) auf das Spielbrett
	 * 
	 * @param height
	 * @param width
	 * @param mode
	 * @param beginner
	 */
	public GameEngine(int height, int width, int mode, String... beginner) {
		GameBoard gb = GameBoard.createBoard(height, width);
		this.gb = gb;
		game = new Game(gb);
		this.mode = mode;
		if (mode == 1) {
			this.beginner = beginner[0];
			if (this.beginner.equals("KI"))
				this.com = new KI(3, gb, "X");
			else
				this.com = new KI(3, gb, "O");
		}
	}

	/**
	 * Soll den eigenen Zug mitteilen
	 * 
	 * @param input
	 */
	@Override
	public void myMove(String input) {
		if (mode == 2) {
			if (counter % 2 == 0) {
				game.setStone("X", input);
			} else {
				game.setStone("O", input);
			}

		} else if (mode == 1) {
			if (beginner.equals("KI")) {
				if (counter % 2 == 0) {
					game.setStone("X", com.move(gb));
				} else
					game.setStone("O", input);

			} else {
				if (counter % 2 == 0) {
					game.setStone("X", input);
				} else
					game.setStone("O", com.move(gb));
			}
		}
		counter++;
	}

	/**
	 * Gibt den letzten Zug zurueck
	 * 
	 * @return letzten validen Zug
	 */
	@Override
	public String yourMove() {
		return game.lastTurn;
	}

	/**
	 * Sagt ob das Spiel noch laeuft
	 * 
	 * @return true (laeuft noch) / false (laeuft nicht mehr)
	 */
	@Override
	public boolean isRunning() {
		return game.isRunning();
	}

	/**
	 * Sagt wer gewonnen hat
	 * 
	 * @return true (Spieler 1) / false (Spieler 2)
	 */
	@Override
	public boolean whoWon() {
		return game.whoWon();
	}

	/**
	 * Gibt das aktuelle Spielfeld aus
	 */
	@Override
	public void printBoard() {
		gb.printBoard();
	}

	/**
	 * Ueberprüft ob der eingegebene String (input) zulaessig ist (Form und
	 * Machbarkeit)
	 * 
	 * @param input
	 * @return Ist zulaessig?
	 */
	@Override
	public boolean isVaildMove(String input) {
		return game.isValid(input) == true;
	}
}

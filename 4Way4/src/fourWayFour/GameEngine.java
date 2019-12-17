package fourWayFour;

public class GameEngine implements Requirements {
	private static GameBoard gb;
	private static Game spiel;
	private static Player p1,p2;
	private static KI com;

	/**
	 * Setzt den Stein (X/O) auf das Spielbrett
	 * 
	 * @param input
	 */

	public GameEngine(int height, int width) {
		GameEngine.gb = GameBoard.createBoard(height, width);
		spiel = new Game(gb);
	}

	@Override
	public void myMove(String input) {

		spiel.setStone("X", input);
	}

	@Override
	public String yourMove() {

		return null;
	}

	@Override
	public boolean isRunning() {
		return spiel.isRunning();
	}

	@Override
	public boolean whoWon() {
		return spiel.whoWon();
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
		return spiel.isValid(input) == true;
	}
}

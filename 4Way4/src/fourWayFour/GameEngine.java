package fourWayFour;

public class GameEngine implements Requirements {
	private GameBoard gb;
	private Game spiel;
	private Player p1, p2;
	private KI com;
	private int counter;

	/**
	 * Setzt den Stein (X/O) auf das Spielbrett
	 * 
	 * @param input
	 */

	public GameEngine(int height, int width) {
		GameBoard gb = GameBoard.createBoard(height, width);
		spiel = new Game(gb);
	}
	
	/**
	 * Soll den eigenen Zug mitteilen
	 * 
	 * @param String eingabe
	 * @return void
	 */
	@Override
	public void myMove(String eingabe) {
		if (counter % 2 == 0) {
			spiel.setStone("X", eingabe);
		} else {
			spiel.setStone("O", eingabe);
		}
	}

	/**
	 * Ließt den Zug des Gegners ein
	 * 
	 * @param
	 * @return String Ausgabe
	 */
	@Override
	public String yourMove() {
		return spiel.ausgabe;
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

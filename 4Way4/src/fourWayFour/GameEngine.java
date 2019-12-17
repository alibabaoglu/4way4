package fourWayFour;

public class GameEngine implements Requirements {
	private GameBoard gb;
	private Game spiel;
	private int counter;
	private int modus, beginner;

	/**
	 * Setzt den Stein (X/O) auf das Spielbrett
	 * 
	 * @param input
	 */

	public GameEngine(int height, int width, int modus, int... beginner) {
		GameBoard gb = GameBoard.createBoard(height, width);
		spiel = new Game(gb);
		this.modus = modus;
		if(modus==1)
		this.beginner = beginner[0];
	}

	@Override
	/**
	 * Soll den eigenen Zug mitteilen
	 * 
	 * @param String eingabe
	 * @return void
	 */
	public void myMove(String eingabe) {
		if (modus == 2) {
			if (counter % 2 == 0) {
				spiel.setStone( "X",eingabe);
			} else {
				spiel.setStone("O",eingabe);
			}
		}
		counter++;
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

package fourWayFour;

public class GameEngine implements Requirements {

	GameBoard gb = GameBoard.createBoard(7, 7);

	/**
	 * Setzt den Stein (X/O) auf das Spielbrett
	 * 
	 * @param input
	 */
	@Override
	public void myMove(String input) {
		new GameMove(this.gb).setStone("X", input);
	}

	@Override
	public String yourMove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRunning() {
		// TODO
		return false;
	}

	@Override
	public boolean whoWon() {
		// TODO Auto-generated method stub
		return false;
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
		return (new GameMove(this.gb).isValid(input) == true);
	}
}

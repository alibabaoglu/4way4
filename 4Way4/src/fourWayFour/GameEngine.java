package fourWayFour;

/**
 * Das gesamte Spiel
 *
 */
public class GameEngine implements Requirements {

	final static int COMPUTER = 1;
	final static int PLAYER = 2;

	private GameBoard gb;
	private Game game;
	private KI com;
	private int counter = 0;
	private int mode;
	private String beginner;

	// TODO Konstruktor KI von außen

	/**
	 * Konstruktor fuer Spieler-Spieler
	 * 
	 * @param height
	 * @param width
	 * @param mode
	 */
	public GameEngine(int height, int width, int mode) {
		if (mode == PLAYER) {
			this.gb = GameBoard.createBoard(height, width);
			this.game = new Game(this.gb);
			this.mode = mode;
		} else {
			throw new GameException("Ungueltige Parameter");
		}
	}

	/**
	 * Konstruktor fuer Spieler-KI
	 * 
	 * @param height
	 * @param width
	 * @param mode
	 * @param difficulty
	 * @param beginner
	 */
	public GameEngine(int height, int width, int mode, int difficulty, String beginner) {
		if (mode == COMPUTER && difficulty >= 1 && difficulty <= 3) {
			this.gb = GameBoard.createBoard(height, width);
			if (beginner.equals("KI")) {
				this.com = new KI(difficulty, this.gb, "X");
			} else if (beginner.equals("Spieler")) {
				this.com = new KI(difficulty, this.gb, "O");
			} else {
				throw new GameException("Ungueltige Parameter");
			}
			this.game = new Game(this.gb);
			this.mode = mode;
			this.beginner = beginner;
		} else {
			throw new GameException("Ungueltige Parameter");
		}
	}

	/**
	 * Soll den eigenen Zug mitteilen
	 * 
	 * @param input
	 */
	@Override
	public void myMove(String input) {
		this.counter++;
		if (this.mode == PLAYER) {
			if (this.counter % 2 != 0) {
				this.game.setStone("X", input);
			} else {
				this.game.setStone("O", input);
			}
		} else if (this.mode == COMPUTER) {
			if (this.beginner.equals("KI")) {
				if (this.counter % 2 != 0) {
					this.game.setStone("X", this.com.move());
				} else
					this.game.setStone("O", input);
			} else {
				if (this.counter % 2 != 0) {
					this.game.setStone("X", input);
				} else
					this.game.setStone("O", this.com.move());
			}
		}
	}

	/**
	 * Gibt den letzten Zug zurueck
	 * 
	 * @return letzten validen Zug
	 */
	@Override
	public String yourMove() {
		return this.game.lastTurn;
	}

	/**
	 * Sagt ob das Spiel noch laeuft
	 * 
	 * @return true (laeuft noch) / false (laeuft nicht mehr)
	 */
	@Override
	public boolean isRunning() {
		return this.game.isRunning();
	}

	/**
	 * Sagt wer gewonnen hat
	 * 
	 * @return true (Spieler 1) / false (Spieler 2)
	 */
	@Override
	public boolean whoWon() {
		return this.game.whoWon();
	}

	/**
	 * Gibt das aktuelle Spielfeld aus
	 */
	@Override
	public void printBoard() {
		this.gb.printBoard();
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
		return this.game.isValid(input) == true;
	}
}

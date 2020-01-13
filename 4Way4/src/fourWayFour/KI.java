package fourTheWin;

import java.util.List;

public class KI {

	final static int EASY = 1;
	final static int MEDIUM = 2;
	final static int HARD = 3;

	private static int counter = 0;

	private GameBoard board;
	private String computersMove;
	private String kiToken, playerToken;
	private int difficulty,rounds;

	public KI(int schwierigkeit, GameBoard gb, String kiToken) {
		this.kiToken = kiToken;
		this.board = gb;
		this.difficulty = schwierigkeit;
		if (kiToken.equals("X"))
			this.playerToken = "O";
		else
			this.playerToken = "X";
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * 
	 * @return
	 */
	public String move() {
		if (this.difficulty == EASY)
			return this.easyMove();

		else if (this.difficulty == MEDIUM)
			return this.mediumMove();

		else if (this.difficulty == HARD)
			return this.hardMove();

		else
			throw new GameException("Kein Schwierigkeitsgrad");
	}

//----------------------------------------------------------------------------------
	/**
	 * 
	 * @return
	 */
	private String easyMove() {
		// In welche Richtung soll eingeworfen werden?
		boolean ecke = false;
		int spalte = 0, zeile = 0;
		String richtung = "";
		int rnd = (int) (Math.random() * 4 + 1);
		// Unten
		if (rnd == 1) {
			spalte = (int) (Math.random() * (this.board.width - 2) + 1);
			zeile = this.board.height - 2;
			if (spalte == 1 || spalte == this.board.width - 2)
				ecke = true;
			richtung = "d";
		}

		// oben
		if (rnd == 2) {
			spalte = (int) (Math.random() * (this.board.width - 2) + 1);
			zeile = 1;
			if (spalte == 1 || spalte == this.board.width - 2)
				ecke = true;
			richtung = "u";

		}
		// links
		if (rnd == 3) {
			spalte = this.board.width - 2;
			zeile = (int) (Math.random() * (this.board.height - 2) + 1);
			if (zeile == 1 || zeile == this.board.height - 2)
				ecke = true;
			richtung = "l";
		}
		// rechts
		if (rnd == 4) {
			spalte = 1;
			zeile = (int) (Math.random() * (this.board.height - 2) + 1);
			if (zeile == 1 || zeile == this.board.height - 2)
				ecke = true;
			richtung = "r";
		}
		if (ecke) {
			String zug = "" + zeile + (char) (spalte + 96) + richtung;

			return zug;
		} else {
			String zug = "" + zeile + (char) (spalte + 96);
			return zug;
		}

	}

//----------------------------------------------------------------------------------------
	/**
	 * 
	 * @return
	 */
	private String mediumMove() {
		KI.counter++;
		if (KI.counter % 3 != 0) {
			return this.hardMove();
		} else {
			return this.easyMove();
		}
	}

//---------------------------------------------------------------------------------------
	/**
	 * 
	 * @param gb
	 * @return
	 */
	private String hardMove() {
		rounds++;
		if(rounds==1) {
		return easyMove();
		}
		else {
		minimax(this.board.cloneBoard(), 2, 1);
		return this.computersMove;
		}
	}
	

//------------------------------------------------------------------------------------------
	/**
	 * Rekursive Funkton welches einen Baum simuliert. Baum kann bis zu 28^depth
	 * Spielstände enthalten. Die Funktion bewertet die Spielstände. Der Spielstand
	 * mit der besten bewertung wird nachkonstruiert.
	 * 
	 * @param gb
	 * 
	 * @param depth
	 * 
	 * @param turn
	 * 
	 * @return score
	 */
	private int minimax(GameBoard gb, int depth, int turn) {

		Game spiel = new Game(gb);
		List<String> valid_locations = spiel.get_valid_locations(gb);// Speichere alle gültigen Züge.

		// Falls jemand gewinnt wird direkt ein Score übergeben, wenn nicht und die
		// Tiefe 0 ist dann wird der jeweilige Spielstand bewertet.
		if (depth == 0 || !spiel.isRunning()) {
			if (!spiel.isRunning()) {
				if (spiel.symbolWon(kiToken))
					return +1000000000;
				else if (spiel.symbolWon(playerToken))
					return -1000000000;
			} else if (depth == 0)
				return spiel.scoreBoard(gb, kiToken);
		}

		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		// Iteriere durch alle gültigen Züge und simuliere Spielzüge(abwechselnd KI und
		// Spieler).
		for (int i = 0; i < valid_locations.size(); i++) {

			String point = valid_locations.get((i));
			var new_board = gb.cloneBoard();
			Game g = new Game(new_board);
			// Maximierender Spieler
			if (turn == 1) {
				g.setStone(kiToken, point);
				int currentScore = minimax(new_board, depth - 1, 2); // Rekur. Aufruf gibt Score zurück
				// Wenn Score besser als die andere Scores dann speichere Score und merke Zug in
				// computersMove.
				if (currentScore > max) {
					max = currentScore;
					computersMove = point;
				}
			}
			// Minimierender Spieler
			else {
				g.setStone(playerToken, point);
				int currentScore = minimax(new_board, depth - 1, 1);
				if (currentScore < min)
					min = currentScore;
			}
		}
		return turn == 1 ? max : min;
	}
}

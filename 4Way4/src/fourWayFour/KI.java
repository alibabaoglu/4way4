package fourWayFour;


import java.util.List;

public class KI {
	private boolean hard, medium, easy;
	private GameBoard board;


	private String  kiToken, playerToken;

	public KI(int schwierigkeit, GameBoard gb, String token) {
		this.kiToken = token;

		if (kiToken.equals("X"))
			this.playerToken = "O";
		else
			this.playerToken = "X";

		this.board = gb;
		switch (schwierigkeit) {
		case 1:
			this.easy = true;
			break;
		case 2:
			this.medium = true;
			break;
		case 3:
			this.hard = true;
			break;
		}
	}

	public String move() {

		return easyMove();
	}

	public String move(GameBoard gb) {
	

		return hardMove(gb);
	}

	private GameBoard cloneGameBoard(GameBoard gb) {
		GameBoard tmp = GameBoard.createBoard(gb.height - 2, gb.width - 2);

		for (int i = 0; i < gb.height; i++) {
			for (int j = 1; j < gb.width; j++) {
				tmp.board[i][j] = gb.board[i][j];
			}
		}

		return tmp;
	}

	private String easyMove() {
		// In welche Richtung soll eingeworfen werden?
		boolean ecke = false;
		int spalte = 0, zeile = 0;
		String richtung = "";
		int rnd = (int) (Math.random() * 4 + 1);
		// Unten
		if (rnd == 1) {
			spalte = (int) (Math.random() * (board.width - 2) + 1);
			zeile = board.height - 2;
			if (spalte == 1 || spalte == board.width - 2)
				ecke = true;
			richtung = "d";
		}

		// oben
		if (rnd == 2) {
			spalte = (int) (Math.random() * (board.width - 2) + 1);
			zeile = 1;
			if (spalte == 1 || spalte == board.width - 2)
				ecke = true;
			richtung = "u";

		}
		// links
		if (rnd == 3) {
			spalte = board.width - 2;
			zeile = (int) (Math.random() * (board.height - 2) + 1);
			if (zeile == 1 || zeile == board.height - 2)
				ecke = true;
			richtung = "l";
		}
		// rechts
		if (rnd == 4) {
			spalte = 1;
			zeile = (int) (Math.random() * (board.height - 2) + 1);
			if (zeile == 1 || zeile == board.height - 2)
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

	private String hardMove(GameBoard gb) {

		System.out.println(minimax(cloneGameBoard(gb), 2, 1));
		return computersMove;
	}

	public String computersMove;

	/*
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
	public int minimax(GameBoard gb, int depth, int turn) {

		Game spiel = new Game(gb);
		List<String> valid_locations = spiel.get_valid_locations(gb);// Speichere alle gültigen Züge.

		// Falls jemand gewinnt wird direkt ein Score übergeben, wenn nicht und die
		// Tiefe 0 ist dann wird der jeweilige Spielstand bewertet.
		if (depth == 0 || !spiel.isRunning()) {
			if (!spiel.isRunning()) {
				if (spiel.hasXWon()) {
					if (kiToken.equals("X"))
						return +1000000000;
					else
						return -1000000000;
				} else if (spiel.hasOWon()) {
					if (kiToken.equals("O"))
						return +1000000000;
					else
						return -1000000000;
				} else
					return 0; // Spiel ist vorbei, keine gültigen Züge mehr
			} else if (depth == 0)
				return spiel.scoreBoard(gb, kiToken);
		}

		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		// Iteriere durch alle gültigen Züge und simuliere Spielzüge(abwechselnd KI und
		// Spieler).
		for (int i = 0; i < valid_locations.size(); i++) {

			String point = valid_locations.get((i));
			var new_board = cloneGameBoard(gb);
			Game g = new Game(new_board);
			//Maximierender Spieler
			if (turn == 1) {
				g.setStone(kiToken, point);
				int currentScore = minimax(new_board, depth - 1, 2); // Rekur. Aufruf gibt Score zurück
				// Wenn Score besser als die andere Scores dann speichere Score und merke Zug in
				// computersMove.
				if (currentScore > max) {
					max = currentScore;
					computersMove = point;
					if (depth == 3)
						System.out.println("Score for position " + computersMove + " = " + currentScore);
				}
			//Minimierender Spieler
			} else {
				g.setStone(playerToken, point);
				int currentScore = minimax(new_board, depth - 1, 1);
				if (currentScore < min)
					min = currentScore;

			}
		}
		return turn == 1 ? max : min;
	}
}
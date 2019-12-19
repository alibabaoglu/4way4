package fourWayFour;

public class KI {
	private boolean hard, medium, easy;
	private GameBoard gb;

	public KI(int difficulty, GameBoard gb) {
		this.gb = gb;
		switch (difficulty) {
		case 1:
			easy = true;
			break;
		case 2:
			medium = true;
			break;
		case 3:
			hard = true;
			break;
		}
	}

	public String move() {
		if (easy)
			return easyMove();
		else if (medium)
			return mediumMove();
		else if (hard)
			return hardMove();
		else
			throw new GameException("Kein Schwierigkeitsgrad");
	}

	private String easyMove() {
		// In welche Richtung soll eingeworfen werden?
		boolean corner = false;
		int column = 0, line = 0;
		String direction = "";
		int rnd = (int) (Math.random() * 4 + 1);
		// Unten
		if (rnd == 1) {
			column = (int) (Math.random() * (gb.width - 2) + 1);
			line = gb.height - 2;
			if (column == 1 || column == gb.width - 2)
				corner = true;
			direction = "d";
		}
		// oben
		if (rnd == 2) {
			column = (int) (Math.random() * (gb.width - 2) + 1);
			line = 1;
			if (column == 1 || column == gb.width - 2)
				corner = true;
			direction = "u";
		}
		// links
		if (rnd == 3) {
			column = gb.width - 2;
			line = (int) (Math.random() * (gb.height - 2) + 1);
			if (line == 1 || line == gb.height - 2)
				corner = true;
			direction = "l";
		}
		// rechts
		if (rnd == 4) {
			column = 1;
			line = (int) (Math.random() * (gb.height - 2) + 1);
			if (line == 1 || line == gb.height - 2)
				corner = true;
			direction = "r";
		}
		if (corner)
			return "" + line + (char) (column + 96) + direction;
		else
			return "" + line + (char) (column + 96);
	}

	private String mediumMove() {
		// TODO:implement Method
		return null;
	}

	private String hardMove() {
		// TODO: implement Method
		return null;
	}
}

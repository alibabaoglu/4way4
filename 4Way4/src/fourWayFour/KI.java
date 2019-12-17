package fourWayFour;

public class KI {
	private boolean hard, medium, easy;
	private GameBoard gb;

	public KI(int schwierigkeit, GameBoard gb) {
		this.gb = gb;
		switch (schwierigkeit) {
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
		boolean ecke = false;
		int spalte = 0, zeile = 0;
		String richtung = "";
		int rnd = (int) (Math.random() * 4 + 1);
		// Unten
		if (rnd == 1) {
			spalte = (int) (Math.random() * (gb.width - 2) + 1);
			zeile = gb.height - 2;
			if (spalte == 1 || spalte == gb.width - 2)
				ecke = true;
			richtung = "d";
		}

		// oben
		if (rnd == 2) {
			spalte = (int) (Math.random() * (gb.width - 2) + 1);
			zeile = 1;
			if (spalte == 1 || spalte == gb.width - 2)
				ecke = true;
			richtung = "u";

		}
		// links
		if (rnd == 3) {
			spalte = gb.width - 2;
			zeile = (int) (Math.random() * (gb.height - 2) + 1);
			if (zeile == 1 || zeile == gb.height - 2)
				ecke = true;
			richtung = "l";
		}
		// rechts
		if (rnd == 4) {
			spalte = 1;
			zeile = (int) (Math.random() * (gb.height - 2) + 1);
			if (zeile == 1 || zeile == gb.height - 2)
				ecke = true;
			richtung = "r";
		}
		if (ecke)
			return "" + zeile + (char) (spalte + 96) + richtung;
		else
			return "" + zeile + (char) (spalte + 96);

	}

	public String mediumMove() {
		//TODO:implement Method
		return null;
	}

	public String hardMove() {
		//TODO: implement Method
		return null;
	}
}

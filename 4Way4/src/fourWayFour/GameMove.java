package fourWayFour;

/**
 * Ein Spielzug
 */
public class GameMove {
	private int nummer;
	private char buchstabe, richtung;
	private GameBoard gb;

	/**
	 * Konstruktor des Spielzugs
	 * 
	 * @param gb
	 */
	public GameMove(GameBoard gb) {
		this.gb = gb;
	}

//-------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Setzt den Stein (X/O) auf das Spielbrett mithilfe der gegebenen "Koordinaten"
	 * und Richtung in die der Stein fallen soll
	 * 
	 * @param symbol
	 * @param eingabe
	 */
	public void setStone(String symbol, String eingabe) {

		boolean besetzt = false;

		if (this.isValid(eingabe) == true) {

			int num = this.gb.height - (this.nummer + 1);
			int buch = this.buchstabe - 96;

			if (this.gb.board[num][buch] == " ") {
				this.gb.board[num][buch] = "" + symbol;
			} else {
				besetzt = true;
			}
			this.shiftGameBoard(symbol, num, buch, besetzt);
			this.gb.printBoard();
		} else {
			throw new GameException("Ungültiger Zug: " + eingabe);
		}
	}

//-------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Verschiebt alle Steine in die angegebene Richtung bekommt als Parameter die
	 * Koordinaten
	 * 
	 * @param symbol  (X/O)
	 * @param x-Wert
	 * @param y-Wert
	 * @param besetzt
	 */
	
	public boolean notShiftable(int i, int z) {
		if (richtung == 'u') {
			return (i < this.gb.board.length - 2
					&& (this.gb.board[i][z] == " " || this.gb.board[i][z] != " " && this.gb.board[(i - 1)][z] != " "));
		} else if (richtung == 'd') {
			return (i > 1
					&& (this.gb.board[i][z] == " " || this.gb.board[i][z] != " " && this.gb.board[i + 1][z] != " "));
		} else if (richtung == 'r') {
			return (i > 1
					&& (this.gb.board[z][i] == " " || this.gb.board[z][i] != " " && this.gb.board[z][i + 1] != " "));
		} else
			return (i < this.gb.board[0].length - 2
					&& (this.gb.board[z][i] == " " || this.gb.board[z][i] != " " && this.gb.board[z][i - 1] != " "));

	}

	private boolean counterIsValid(int i) {
		if (richtung == 'u') {
			return i < this.gb.board.length - 2;
		} else if (richtung == 'd' || richtung == 'r')
			return i > 1;
		else
			return i < this.gb.board[0].length - 2;

	}

	private int range() {
		if (richtung == 'u' || richtung == 'd') {
			return this.gb.board[0].length - 2;
		} else
			return this.gb.board.length - 1;
	}

	private void shiftGameBoard(String symbol, int x, int y, boolean besetzt) {

		String stein;
		int valueI = 0, j = 0;

		if (richtung == 'u' || richtung == 'l') {
			valueI = 1;
		}
		if (richtung == 'd') {
			valueI = this.gb.board.length - 2;
		}
		if (richtung == 'r') {
			valueI = this.gb.board[0].length - 2;
		}

		for (int z = 1; z <= range(); z++) {
			int i = valueI;

			while (counterIsValid(i)) {
				while (notShiftable(i, z)) {
					if (richtung == 'u' || richtung == 'l')
						i++;
					else
						i--;
				}

				j = i;

				if (richtung == 'u' || richtung == 'd') {
					stein = this.gb.board[i][z];
					this.gb.board[i][z] = " ";

					if (richtung == 'u') {
						while (j > valueI)
							if (this.gb.board[j - 1][z] == " ")
								j--;
							else
								break;
						this.gb.board[j][z] = stein;
						if (z == y && i == this.gb.board.length - 2 && besetzt == true) {
							this.gb.board[(j + 1)][(z)] = symbol;
							besetzt = false;
						}

					} else if (richtung == 'd') {
						while (j < valueI)
							if (this.gb.board[j + 1][z] == " ")
								j++;
							else
								break;
						this.gb.board[j][z] = stein;
						if (z == y && i == 1 && besetzt == true) {
							this.gb.board[(j - 1)][(z)] = symbol;
							besetzt = false;
						}
					}

				} else {
					stein = this.gb.board[z][i];
					this.gb.board[z][i] = " ";
					if (richtung == 'r') {
						while (j < this.gb.board.length)
							if (this.gb.board[z][j + 1] == " ")
								j++;
							else
								break;
						this.gb.board[z][j] = stein;
						if (z == x && i == 1 && besetzt == true) {
							this.gb.board[z][(j - 1)] = symbol;
							besetzt = false;
						}

					} else if (richtung == 'l') {
						while (j > 1)
							if (this.gb.board[z][j - 1] == " ")
								j--;
							else
								break;
						this.gb.board[z][j] = stein;
						if (z == x && i == this.gb.board[0].length - 2 && besetzt == true) {
							this.gb.board[z][(j + 1)] = symbol;
							besetzt = false;
						}
					}

				}
			}
		}
	}

//-------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Überprüft ob ein Zug gültig ist
	 * 
	 * @param input der zu ueberpruefende Zug
	 * @return Ist der Zug gueltig?
	 */
	protected boolean isValid(String input) {
		return (this.isValidString(input) == this.isValidSpace() == true);
	}

//-----------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Ueberprueft ob ein Zugeingabe-String x zulaessig ist und speichert die
	 * Variablen (Nummer num, Buchstabe buch und Richtung rich)
	 * 
	 * @param zugeingabe zu uerpruefender String
	 * @return ist gueltige Eingabe?
	 */
	private boolean isValidString(String zugeingabe) {
		// Laenge des Strings x, x<2 oder 3<x
		if (zugeingabe.length() > 3 || zugeingabe.length() < 2) {

			// Laenge des Strings x=4
			if (zugeingabe.length() == 4) {

				// x="10a"rich oder x="10"buch(letzter Buchstabe)rich
				if ((zugeingabe.charAt(0) == '1') && (zugeingabe.charAt(1) == '0')
						&& ((zugeingabe.charAt(2) == 'a') || (zugeingabe.charAt(2) == (char) ('a' + gb.width - 3)))) {
					this.nummer = 10;
					this.buchstabe = zugeingabe.charAt(2);
				}
				// x="a10"rich oder x=buch(letzter Buchstabe)"10"rich
				else if ((zugeingabe.charAt(1) == '1') && (zugeingabe.charAt(2) == '0')
						&& ((zugeingabe.charAt(0) == 'a') || (zugeingabe.charAt(0) == (char) ('a' + gb.width - 3)))) {
					this.nummer = 10;
					this.buchstabe = zugeingabe.charAt(0);
				}
				// x=4 aber num und/oder buch ungueltig
				else {
					return false;
				}
				// rich=d oder x="10ar", "a10r" oder x="10"buch(letzter Buchstabe)"l" ,
				// buch(letzter Buchstabe)"10l"
				if (zugeingabe.charAt(3) == 'd'
						|| zugeingabe.charAt(3) == 'l' && this.buchstabe == (char) ('a' + gb.width - 3)
						|| zugeingabe.charAt(3) == 'r' && this.buchstabe == 'a') {
					this.richtung = zugeingabe.charAt(3);
					return true;
				}
				// Laenge x=4 aber rich ungueltig
				else {
					return false;
				}
			}
			// Laenge x, 2<=x<=3
			else {
				return false;
			}
			// Laenge x, 2<=x<=3
		} else {

			// x=3
			if (zugeingabe.length() == 3) {

				// x = "1"buch rich oder num(letzte Nummer) buch rich
				if (zugeingabe.charAt(0) == '1' || zugeingabe.charAt(0) == (char) ('1' + gb.height - 3)) {
					this.nummer = Integer.parseInt(("" + zugeingabe.charAt(0)));
					this.buchstabe = zugeingabe.charAt(1);
				}
				// x = buch"1"rich oder buch num(letzte Nummer) rich
				else if (zugeingabe.charAt(1) == '1' || zugeingabe.charAt(1) == (char) ('1' + gb.height - 3)) {
					this.nummer = Integer.parseInt(("" + zugeingabe.charAt(1)));
					this.buchstabe = zugeingabe.charAt(0);
				}
				// Laenge x = 3, num ungueltig
				else {
					return false;
				}
				// Laenge x = 3, num gueltig
				this.richtung = zugeingabe.charAt(2);

				// x = String mit "a" und "1" und entweder "r" oder "u"
				if ((this.richtung == 'r' || this.richtung == 'u') && this.nummer == 1 && this.buchstabe == 'a') {
					return true;
				}
				// x = String mit dem letzter Buchstaben und "1" und entweder "u" oder "l"
				else if ((this.richtung == 'u' || this.richtung == 'l') && this.nummer == 1
						&& this.buchstabe == (char) ('a' + gb.width - 3)) {
					return true;
				}
				// x = String mit dem letzter Buchstaben und der letzten Zahl und entweder "l"
				// oder "d"
				else if ((this.richtung == 'l' || this.richtung == 'd') && this.nummer == (1 + gb.height - 3)
						&& this.buchstabe == (char) ('a' + gb.width - 3)) {
					return true;
				}
				// x = String mit "a" und der letzten Zahl und entweder "d" oder "r"
				else if ((this.richtung == 'd' || this.richtung == 'r') && this.nummer == (1 + gb.height - 3)
						&& this.buchstabe == 'a') {
					return true;
				}
				// Laenge x = 3 aber rich und/oder buch ungueltig
				else {
					return false;
				}
			}
			// x=2
			else {

				// x = num buch
				if ((zugeingabe.charAt(0) >= '1' && zugeingabe.charAt(0) <= (char) ('1' + gb.height - 3))
						&& (zugeingabe.charAt(1) >= 'a' && zugeingabe.charAt(1) <= (char) ('a' + gb.width - 3))) {

					this.nummer = Integer.parseInt(("" + zugeingabe.charAt(0)));
					this.buchstabe = zugeingabe.charAt(1);
				}
				// x = buch num
				else if ((zugeingabe.charAt(1) >= '1' && zugeingabe.charAt(1) <= (char) ('1' + gb.height - 3))
						&& (zugeingabe.charAt(0) >= 'a' && zugeingabe.charAt(0) <= (char) ('a' + gb.width - 3))) {

					this.nummer = Integer.parseInt(("" + zugeingabe.charAt(1)));
					this.buchstabe = zugeingabe.charAt(0);
				}
				// x = 2, num und/oder buch ungueltig
				else {
					return false;
				}
				// x = String mit "1" und NICHT "a" oder dem letzten Buchstaben
				if (this.nummer == 1 && this.buchstabe != 'a' && this.buchstabe != (char) ('a' + gb.width - 3)) {
					this.richtung = 'u';
					return true;
				}
				// x = String mit der letzten Zahl und NICHT "a" oder dem letzten Buchstaben
				else if (this.nummer == (1 + gb.height - 3) && this.buchstabe != 'a'
						&& this.buchstabe != (char) ('a' + gb.width - 3)) {
					this.richtung = 'd';
					return true;
				}
				// x = String mit "a" und NICHT "1" oder der letzten Zahl
				else if (this.buchstabe == 'a' && this.nummer != 1 && this.nummer != (1 + gb.height - 3)) {
					this.richtung = 'r';
					return true;
				}
				// x = String mit dem letzten Buchstaben und NICHT "1" oder der letzten Zahl
				else if (this.buchstabe == (char) ('a' + gb.width - 3) && this.nummer != 1
						&& this.nummer != (1 + gb.height - 3)) {
					this.richtung = 'l';
					return true;
				}
				// x = 2, num zusammen mit buch ungueltig
				else {
					return false;
				}
			}
		}
	}

//---------------------------------------------------------------------------------------------------------
	/**
	 * Ueberprueft ob das zu belegende Feld frei ist
	 * 
	 * @return ist gueltiger Platz?
	 */
	private boolean isValidSpace() {
		int x = gb.height - (this.nummer + 1);
		int y = this.buchstabe - 96;

		if (this.richtung == 'u') {
			for (int i = (gb.height - 2); i >= 1; i--) {
				if (gb.board[i][y] == " ")
					return true;
			}
		} else if (this.richtung == 'd') {
			for (int i = x; i <= (gb.height - 2); i++) {
				if (gb.board[i][y] == " ")
					return true;
			}
		} else if (this.richtung == 'l') {
			for (int i = (gb.width - 2); i >= 1; i--) {
				if (gb.board[x][i] == " ")
					return true;
			}
		} else if (this.richtung == 'r') {
			for (int i = y; i <= (gb.width - 2); i++) {
				if (gb.board[x][i] == " ")
					return true;
			}
		}
		return false;
	}
}

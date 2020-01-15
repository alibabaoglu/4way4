package fourTheWin;

import java.util.*;

/**
 * Ein Spielzug
 */
public class Game {
	private int number;
	private char character, direction;
	private GameBoard gb;
	private boolean winner; // true == Spieler 1, false == Spieler 2
	private int turn = 0;
	protected String lastTurn = "noch kein Zug vorhanden";

	/**
	 * Konstruktor des Spielzugs
	 * 
	 * @param gbF
	 */
	public Game(GameBoard gb) {
		this.gb = gb;
	}

//-------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Setzt den Stein (X/O) auf das Spielbrett mithilfe der gegebenen "Koordinaten"
	 * und Richtung in die der Stein fallen soll
	 * 
	 * @param symbol zusetzendes Zeichen
	 * @param entry  Eingabestring
	 */
	public void setStone(String symbol, String entry) {
		boolean occupied = false;

		if (this.isValid(entry) == true) {

			this.turn++;
			this.lastTurn = entry;

			int x = this.gb.height - (this.number + 1);
			int y = this.character - 96;

			if (this.gb.board[x][y] == " ") {
				this.gb.board[x][y] = symbol;
			} else {
				occupied = true;
			}
			this.shiftGameBoard(symbol, x, y, occupied);
		} else {
			OutputCollection.outputWithNewLine("Ungültiger Zug " + entry);
		}

	}

//-------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Prueft ob im Feld an der Stelle ein verschiebarer Stein vorhanden ist
	 * 
	 * @param i
	 * @param z
	 * @return true (kein verschiebbarer Stein) / false (Stein verschiebbar)
	 */
	public boolean notShiftable(int i, int z) {
		if (direction == 'u') {
			return (i < this.gb.board.length - 2
					&& (this.gb.board[i][z] == " " || this.gb.board[i][z] != " " && this.gb.board[(i - 1)][z] != " "));
		} else if (direction == 'd') {
			return (i > 1
					&& (this.gb.board[i][z] == " " || this.gb.board[i][z] != " " && this.gb.board[i + 1][z] != " "));
		} else if (direction == 'r') {
			return (i > 1
					&& (this.gb.board[z][i] == " " || this.gb.board[z][i] != " " && this.gb.board[z][i + 1] != " "));
		} else
			return (i < this.gb.board[0].length - 2
					&& (this.gb.board[z][i] == " " || this.gb.board[z][i] != " " && this.gb.board[z][i - 1] != " "));

	}

//-------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Ueberprueft ob der Zaehler einer Schleife noch im Gueltigkeitsbereich liegt
	 * 
	 * @param counter Zaehler
	 * @return true wenn gültig sonst false
	 */
	private boolean counterIsValid(int counter) {
		if (direction == 'u') {
			return counter < this.gb.board.length - 2;
		} else if (direction == 'd' || direction == 'r')
			return counter > 1;
		else
			return counter < this.gb.board[0].length - 2;

	}

//-------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Bestimmt wie oft die aeussere Schleife (for-schleife) laufen soll
	 * 
	 * @return Anzahl der Iterationen
	 */
	private int range() {
		if (direction == 'u' || direction == 'd') {
			return this.gb.board[0].length - 2;
		} else
			return this.gb.board.length - 1;
	}

//-------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Verschiebt alle Steine in die angegebene Richtung bekommt als Parameter die
	 * Koordinaten
	 * 
	 * @param symbol   (X/O)
	 * @param          x-Wert
	 * @param          y-Wert
	 * @param occupied
	 */

	private void shiftGameBoard(String symbol, int x, int y, boolean occupied) {

		String newStone;
		int valueI = 0, j = 0;

		if (direction == 'u' || direction == 'l') {
			valueI = 1;
		}
		if (direction == 'd') {
			valueI = this.gb.board.length - 2;
		}
		if (direction == 'r') {
			valueI = this.gb.board[0].length - 2;
		}

		// Ausserer Schleife steht bei der Richtung UP,DOWN für Zeile, und bei
		// LEFT,RIGHT
		// für Spalte.
		for (int z = 1; z <= range(); z++) {
			int i = valueI;
			// Schleife laeuft bis an die letzte Stelle von Zeile / Spalte.
			while (counterIsValid(i)) {
				// Schleife wird solange durchlaufen bis verschiebarer Stein gefunden wurde.
				while (notShiftable(i, z)) {
					if (direction == 'u' || direction == 'l')
						i++;
					else
						i--;
				}

				j = i;

				if (direction == 'u' || direction == 'd') {
					newStone = this.gb.board[i][z];// Merke Symbol X/O fürs neu setzen
					this.gb.board[i][z] = " ";// Feld wo zuvor ein Stein war wird entfernt, da Stein verschoben wird

					if (direction == 'u') {
						// Weitere Schleife Zaehler "j" zum zuruecklaufen der Felder um dort den Stein
						// der
						// gefunden wurde an der Richtigen Postition zu setzen
						while (j > valueI)
							if (this.gb.board[j - 1][z] == " ")
								j--;
							else
								break;
						this.gb.board[j][z] = newStone;
						// Falls zuvor beim Spielzug das Feld auf dem gesetzt werden sollte belegt war
						// wird vor dem zuletzt verschobenen Stein der zu setzende Stein gesetzt.
						if (z == y && i == this.gb.board.length - 2 && occupied == true) {
							this.gb.board[(j + 1)][(z)] = symbol;
							occupied = false;
						}

						// dito wie UP nur mit der Richtung DOWN
					} else if (direction == 'd') {
						while (j < valueI)
							if (this.gb.board[j + 1][z] == " ")
								j++;
							else
								break;
						this.gb.board[j][z] = newStone;
						if (z == y && i == 1 && occupied == true) {
							this.gb.board[(j - 1)][(z)] = symbol;
							occupied = false;
						}
					}

					// dito wie UP/DOWN nur mit den Richtungen LEFT/RIGHT
				} else {
					newStone = this.gb.board[z][i];
					this.gb.board[z][i] = " ";
					if (direction == 'r') {
						while (j < this.gb.board[0].length - 1)
							if (this.gb.board[z][j + 1] == " ")
								j++;
							else
								break;
						this.gb.board[z][j] = newStone;
						if (z == x && i == 1 && occupied == true) {
							this.gb.board[z][(j - 1)] = symbol;
							occupied = false;
						}

					} else if (direction == 'l') {
						while (j > 1)
							if (this.gb.board[z][j - 1] == " ")
								j--;
							else
								break;
						this.gb.board[z][j] = newStone;
						if (z == x && i == this.gb.board[0].length - 2 && occupied == true) {
							this.gb.board[z][(j + 1)] = symbol;
							occupied = false;
						}
					}
				}
			}
		}
	}

//-------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Ueberprueft ob ein Zug gueltig ist
	 * 
	 * @param input der zu ueberpruefende Zug
	 * @return Ist der Zug gueltig?
	 */
	protected boolean isValid(String input) {

		return this.isValidString(input) && this.isValidSpace();
	}

//-----------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Ueberprueft ob ein Zugeingabe-String x zulaessig ist und speichert die
	 * Variablen (Nummer num, Buchstabe buch und Richtung rich)
	 * 
	 * @param moveEntry zu uerpruefender String
	 * @return ist gueltige Eingabe?
	 */
	private boolean isValidString(String moveEntry) {
		// Laenge des Strings x, x<2 oder 3<x
		if (moveEntry.length() > 3 || moveEntry.length() < 2) {

			// Laenge des Strings x=4
			if ((this.gb.height - 2) == 10 && moveEntry.length() == 4) {

				// x="10a"rich oder x="10"buch(letzter Buchstabe)rich
				if ((moveEntry.charAt(0) == '1') && (moveEntry.charAt(1) == '0') && ((moveEntry.charAt(2) == 'a')
						|| (moveEntry.charAt(2) == (char) ('a' + this.gb.width - 3)))) {
					this.number = 10;
					this.character = moveEntry.charAt(2);
				}
				// x="a10"rich oder x=buch(letzter Buchstabe)"10"rich
				else if ((moveEntry.charAt(1) == '1') && (moveEntry.charAt(2) == '0') && ((moveEntry.charAt(0) == 'a')
						|| (moveEntry.charAt(0) == (char) ('a' + this.gb.width - 3)))) {
					this.number = 10;
					this.character = moveEntry.charAt(0);
				}
				// x=4 aber num und/oder buch ungueltig
				else {
					return false;
				}
				// rich=d oder x="10ar", "a10r" oder x="10"buch(letzter Buchstabe)"l" ,
				// buch(letzter Buchstabe)"10l"
				if (moveEntry.charAt(3) == 'd'
						|| moveEntry.charAt(3) == 'l' && this.character == (char) ('a' + this.gb.width - 3)
						|| moveEntry.charAt(3) == 'r' && this.character == 'a') {
					this.direction = moveEntry.charAt(3);
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
			if (moveEntry.length() == 3) {

				//
				if ((this.gb.height - 2) == 10 && moveEntry.charAt(0) == '1' && moveEntry.charAt(1) == '0'
						&& moveEntry.charAt(2) > 'a' && moveEntry.charAt(2) < ('a' + this.gb.width - 3)) {
					this.number = 10;
					this.character = moveEntry.charAt(2);
					this.direction = 'd';
					return true;
				}
				//
				if ((this.gb.height - 2) == 10 && moveEntry.charAt(1) == '1' && moveEntry.charAt(2) == '0'
						&& moveEntry.charAt(0) > 'a' && moveEntry.charAt(0) < ('a' + this.gb.width - 3)) {
					this.number = 10;
					this.character = moveEntry.charAt(0);
					this.direction = 'd';
					return true;
				}
				// x = "1"buch rich oder num(letzte Nummer) buch rich
				if (moveEntry.charAt(0) == '1' || moveEntry.charAt(0) == (char) ('1' + this.gb.height - 3)) {
					this.number = Integer.parseInt(("" + moveEntry.charAt(0)));
					this.character = moveEntry.charAt(1);
				}
				// x = buch"1"rich oder buch num(letzte Nummer) rich
				else if (moveEntry.charAt(1) == '1' || moveEntry.charAt(1) == (char) ('1' + this.gb.height - 3)) {
					this.number = Integer.parseInt(("" + moveEntry.charAt(1)));
					this.character = moveEntry.charAt(0);
				}
				// Laenge x = 3, num ungueltig
				else {
					return false;
				}
				// Laenge x = 3, num gueltig
				this.direction = moveEntry.charAt(2);

				// x = String mit "a" und "1" und entweder "r" oder "u"
				if ((this.direction == 'r' || this.direction == 'u') && this.number == 1 && this.character == 'a') {
					return true;
				}
				// x = String mit dem letzter Buchstaben und "1" und entweder "u" oder "l"
				else if ((this.direction == 'u' || this.direction == 'l') && this.number == 1
						&& this.character == (char) ('a' + this.gb.width - 3)) {
					return true;
				}
				// x = String mit dem letzter Buchstaben und der letzten Zahl und entweder "l"
				// oder "d"
				else if ((this.direction == 'l' || this.direction == 'd') && this.number == (1 + this.gb.height - 3)
						&& this.character == (char) ('a' + this.gb.width - 3)) {
					return true;
				}
				// x = String mit "a" und der letzten Zahl und entweder "d" oder "r"
				else if ((this.direction == 'd' || this.direction == 'r') && this.number == (1 + this.gb.height - 3)
						&& this.character == 'a') {
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
				if ((moveEntry.charAt(0) >= '1' && moveEntry.charAt(0) <= (char) ('1' + this.gb.height - 3))
						&& (moveEntry.charAt(1) >= 'a' && moveEntry.charAt(1) <= (char) ('a' + this.gb.width - 3))) {

					this.number = Integer.parseInt(("" + moveEntry.charAt(0)));
					this.character = moveEntry.charAt(1);
				}
				// x = buch num
				else if ((moveEntry.charAt(1) >= '1' && moveEntry.charAt(1) <= (char) ('1' + this.gb.height - 3))
						&& (moveEntry.charAt(0) >= 'a' && moveEntry.charAt(0) <= (char) ('a' + this.gb.width - 3))) {

					this.number = Integer.parseInt(("" + moveEntry.charAt(1)));
					this.character = moveEntry.charAt(0);
				}
				// x = 2, num und/oder buch ungueltig
				else {
					return false;
				}
				// x = String mit "1" und NICHT "a" oder dem letzten Buchstaben
				if (this.number == 1 && this.character != 'a' && this.character != (char) ('a' + this.gb.width - 3)) {
					this.direction = 'u';
					return true;
				}
				// x = String mit der letzten Zahl und NICHT "a" oder dem letzten Buchstaben
				else if (this.number == (1 + this.gb.height - 3) && this.character != 'a'
						&& this.character != (char) ('a' + this.gb.width - 3)) {
					this.direction = 'd';
					return true;
				}
				// x = String mit "a" und NICHT "1" oder der letzten Zahl
				else if (this.character == 'a' && this.number != 1 && this.number != (1 + this.gb.height - 3)) {
					this.direction = 'r';
					return true;
				}
				// x = String mit dem letzten Buchstaben und NICHT "1" oder der letzten Zahl
				else if (this.character == (char) ('a' + this.gb.width - 3) && this.number != 1
						&& this.number != (1 + this.gb.height - 3)) {
					this.direction = 'l';
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
		int x = this.gb.height - (this.number + 1);
		int y = this.character - 96;

		// bei Richtung hoch bzw. runter die gesamte Spalte testen, ob es noch einen
		// freien Platz gibt
		if (this.direction == 'u') {
			for (int columnI = (this.gb.height - 2); columnI >= 1; columnI--) {
				if (gb.board[columnI][y] == " ")
					return true;
			}
		} else if (this.direction == 'd') {
			for (int columnII = x; columnII <= (this.gb.height - 2); columnII++) {
				if (this.gb.board[columnII][y] == " ")
					return true;
			}
		}
		// bei Richtung links bzw. rechts die gesamte Zeile testen, ob es noch einen
		// freien Platz gibt
		else if (this.direction == 'l') {
			for (int lineI = (this.gb.width - 2); lineI >= 1; lineI--) {
				if (this.gb.board[x][lineI] == " ")
					return true;
			}
		} else if (this.direction == 'r') {
			for (int lineII = y; lineII <= (this.gb.width - 2); lineII++) {
				if (this.gb.board[x][lineII] == " ")
					return true;
			}
		}
		// Wenn es keinen freien Platz mehr gibt
		return false;
	}

//------------------------------------------------------------------------------------
	/**
	 * Prueft ob das Spiel noch laeuft
	 * 
	 * @return true (es laeuft noch) / false (es laeuft nicht mehr)
	 */
	public boolean isRunning() {
		int x = 0;
		int o = 0;
		int line;
		int column;
		boolean player1 = false;
		boolean player2 = false;

		// Pruefen auf 4er-Ketten waagerecht "-"
		column = 1;
		while ((column < this.gb.height - 1) && (player1 == false || player2 == false)) {
			line = 1;
			while ((line < this.gb.width - 1) && (player1 == false || player2 == false)) {
				if (this.gb.board[column][line].equals("X")) {
					x++;
					o = 0;
				} else if (this.gb.board[column][line].equals("O")) {
					o++;
					x = 0;
				} else {
					x = 0;
					o = 0;
				}
				// hat x/o 4er-Kette?
				if (x == 4) {
					player1 = true;
				} else if (o == 4) {
					player2 = true;
				}
				line++;
			}
			x = 0;
			o = 0;
			column++;
		}
		// ----------------------------------------------------------------------------------------------------------------------------
		// Pruefen auf 4er-Ketten senkrecht "|"
		line = 1;
		while ((line < this.gb.width - 1) && (player1 == false || player2 == false)) {
			column = 1;
			while ((column < this.gb.height - 1) && (player1 == false || player2 == false)) {
				if (this.gb.board[column][line].equals("X")) {
					x++;
					o = 0;
				} else if (this.gb.board[column][line].equals("O")) {
					o++;
					x = 0;
				} else {
					x = 0;
					o = 0;
				}
				// hat x/o 4er-Kette?
				if (x == 4) {
					player1 = true;
				} else if (o == 4) {
					player2 = true;
				}
				column++;
			}
			x = 0;
			o = 0;
			line++;
		}
		// ----------------------------------------------------------------------------------------------------------------------------
		// Pruefen auf 4er-Ketten diagonal "/"
		column = 4;
		while ((column < this.gb.height - 1) && (player1 == false || player2 == false)) {
			line = 1;
			while ((line < this.gb.width - 4) && (player1 == false || player2 == false)) {
				if (this.gb.board[column][line].equals("X") && this.gb.board[column - 1][line + 1].equals("X")
						&& this.gb.board[column - 2][line + 2].equals("X")
						&& this.gb.board[column - 3][line + 3].equals("X")) {

					player1 = true;

				} else if (this.gb.board[column][line].equals("O") && this.gb.board[column - 1][line + 1].equals("O")
						&& this.gb.board[column - 2][line + 2].equals("O")
						&& this.gb.board[column - 3][line + 3].equals("O")) {

					player2 = true;
				}
				line++;
			}
			column++;
		}
		// ----------------------------------------------------------------------------------------------------------------------------
		// Pruefen auf 4er-Ketten diagonal "\"
		column = 1;
		while ((column < this.gb.height - 4) && (player1 == false || player2 == false)) {
			line = 1;
			while ((line < this.gb.width - 4) && (player1 == false || player2 == false)) {
				if (this.gb.board[column][line].equals("X") && this.gb.board[column + 1][line + 1].equals("X")
						&& this.gb.board[column + 2][line + 2].equals("X")
						&& this.gb.board[column + 3][line + 3].equals("X")) {

					player1 = true;

				} else if (this.gb.board[column][line].equals("O") && this.gb.board[column + 1][line + 1].equals("O")
						&& this.gb.board[column + 2][line + 2].equals("O")
						&& this.gb.board[column + 3][line + 3].equals("O")) {

					player2 = true;
				}
				line++;
			}
			column++;
		}
		// -------------------------------------------------------------------------------------------------------------------------------------
		// Wenn beide Spieler eine 4er-Kette haben gewinnt der Ziehende
		if (player1 == true && player2 == true) {
			if (this.turn % 2 == 0) {
				this.winner = false;
			} else {
				this.winner = true;
			}
			return false;
		}
		// Wenn genau ein Spieler eine 4er-Kette hat, gewinnt dieser
		else if (player1 == true || player2 == true) {
			this.winner = player1; // true == Spieler 1, false == Spieler 2
			return false;
		}
		// Wenn keiner der beiden Spieler eine 4er-Kette haben gewinnt der Letzte der
		// ziehen konnte
		else if (player1 == false && player2 == false && this.turn == (this.gb.height - 2) * (this.gb.width - 2)) {
			if (this.turn % 2 == 0) {
				this.winner = false;
			} else {
				this.winner = true;
			}
			return false;
		}
		return true;
	}

// ----------------------------------------------------------------------------------------------------------------------------
	/**
	 * Gibt den Gewinner zurueck, wenn das Spiel schon vorbei ist
	 * 
	 * @return true (fuer Spieler 1) / false (fuer Spieler 2)
	 */
	public boolean whoWon() {
		if (this.isRunning() == false) {
			return this.winner;
		} else {
			throw new GameException("Das Spiel laeuft noch!");
		}
	}

//-------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Bewertet param. board und gibt score zürück.
	 * 
	 * @param board
	 * @param kiToken
	 * @return score
	 */
	public int scoreBoard(GameBoard board, String kiToken) {

		int line;
		int column;
		int score = 0;
		String playerToken;

		if (kiToken.equals("X"))
			playerToken = "O";
		else
			playerToken = "X";

		// Pruefen waagerecht "-"
		column = 1;
		while (column < this.gb.height - 1) {

			int freeSpaces = 0;

			// Anzahl der freien Plaetze in der Zeile
			for (int lineSpace = 1; lineSpace < this.gb.width - 1; lineSpace++) {
				if (this.gb.board[column][lineSpace].equals(" ")) {
					freeSpaces++;
				}
			}
			line = 1;

			while (line < this.gb.width - 1) {

				// KI
				// "...XXX(!O)"||"(!X&&!O)XXX..."
				if (freeSpaces >= 1 && (this.gb.board[column][line].equals(kiToken)
						&& this.gb.board[column][line + 1].equals(kiToken)
						&& this.gb.board[column][line + 2].equals(kiToken)
						&& (!this.gb.board[column][line + 3].equals(playerToken)
								|| !this.gb.board[column][line - 1].equals(playerToken)
										&& !this.gb.board[column][line - 1].equals(kiToken)))) {
					line += 2;
					score += 5;
				}
				// "...XX "||"(!X&&!O)XX..."
				else if (freeSpaces >= 1 && (this.gb.board[column][line].equals(kiToken)
						&& this.gb.board[column][line + 1].equals(kiToken)
						&& (!this.gb.board[column][line + 2].equals(kiToken)
								&& !this.gb.board[column][line + 2].equals(playerToken)
								|| !this.gb.board[column][line - 1].equals(playerToken)
										&& !this.gb.board[column][line - 1].equals(kiToken)))) {
					line += 1;
					score += 2;
				}
				// Gegner
				// "...OOO(!X)"||"(!X&&!O)OOO..."
				if (freeSpaces >= 1 && (this.gb.board[column][line].equals(playerToken)
						&& this.gb.board[column][line + 1].equals(playerToken)
						&& this.gb.board[column][line + 2].equals(playerToken)
						&& (!this.gb.board[column][line + 3].equals(kiToken)
								|| !this.gb.board[column][line - 1].equals(kiToken)
										&& !this.gb.board[column][line - 1].equals(playerToken)))) {
					line += 2;
					score -= 100;
				}
				// "...OO "||"(!X&&!O)OO..."
				else if (freeSpaces >= 1 && (this.gb.board[column][line].equals(playerToken)
						&& this.gb.board[column][line + 1].equals(playerToken)
						&& (!this.gb.board[column][line + 2].equals(kiToken)
								&& !this.gb.board[column][line + 2].equals(playerToken)
								|| !this.gb.board[column][line - 1].equals(kiToken)
										&& !this.gb.board[column][line - 1].equals(playerToken)))) {
					line += 1;
					score -= 1;
				}
				line++;
			}
			column++;
		}

		// ----------------------------------------------------------------------------------------------------------------------------
		// Pruefen senkrecht "|"
		line = 1;
		while (line < this.gb.width - 1)

		{
			int freeSpaces = 0;
			for (int columnSpace = 1; columnSpace < this.gb.height - 1; columnSpace++) {
				if (this.gb.board[columnSpace][line].equals(" ")) {
					freeSpaces++;
				}
			}
			column = 1;
			while (column < this.gb.height - 1) {

				// KI
				// ... ||"(!X&&!O)"
				// "X" || "X"
				// "X" || "X"
				// "X" || "X"
				// "(!O)"|| ...
				if (freeSpaces >= 1 && (this.gb.board[column][line].equals(kiToken)
						&& this.gb.board[column + 1][line].equals(kiToken)
						&& this.gb.board[column + 2][line].equals(kiToken)
						&& (!this.gb.board[column + 3][line].equals(playerToken)
								|| !this.gb.board[column - 1][line].equals(playerToken)
										&& !this.gb.board[column - 1][line].equals(kiToken)))) {
					column += 2;
					score += 5;
				}
				// ""||""
				// ""||""
				// ""||""
				else if (freeSpaces >= 1 && (this.gb.board[column][line].equals(kiToken)
						&& this.gb.board[column + 1][line].equals(kiToken)
						&& (!this.gb.board[column + 2][line].equals(kiToken)
								&& !this.gb.board[column + 2][line].equals(playerToken)
								|| !this.gb.board[column - 1][line].equals(playerToken)
										&& !this.gb.board[column - 1][line].equals(kiToken)))) {
					column += 1;
					score += 2;
				}
				// Gegner
				// ""||""
				// ""||""
				// ""||""
				// ""||""
				if (freeSpaces >= 1 && (this.gb.board[column][line].equals(playerToken)
						&& this.gb.board[column + 1][line].equals(playerToken)
						&& this.gb.board[column + 2][line].equals(playerToken)
						&& (!this.gb.board[column + 3][line].equals(kiToken)
								|| !this.gb.board[column - 1][line].equals(kiToken)
										&& !this.gb.board[column - 1][line].equals(playerToken)))) {
					column += 2;
					score -= 100;
				}
				// ""||""
				// ""||""
				// ""||""
				else if (freeSpaces >= 1 && (this.gb.board[column][line].equals(playerToken)
						&& this.gb.board[column + 1][line].equals(playerToken)
						&& (!this.gb.board[column + 2][line].equals(kiToken)
								&& !this.gb.board[column + 2][line].equals(playerToken)
								|| !this.gb.board[column - 1][line].equals(kiToken)
										&& !this.gb.board[column - 1][line].equals(playerToken)))) {
					column += 1;
					score -= 1;
				}
				column++;
			}
			line++;
		}
		return score;
	}

//-------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * 
	 * @param gb
	 * @return liste mit allen gültigen Zügen
	 */
	protected ArrayList<String> get_valid_locations(GameBoard gb) {
		ArrayList<String> fields = new ArrayList<>();

		fields.add("1ar");
		fields.add("1au");
		fields.add(gb.height - 2 + "ad");
		fields.add(gb.height - 2 + "ar");
		fields.add("1" + (char) ('a' + gb.width - 3) + "l");
		fields.add("1" + (char) ('a' + gb.width - 3) + "u");
		fields.add("7" + (char) ('a' + gb.width - 3) + "l");
		fields.add("7" + (char) ('a' + gb.width - 3) + "d");

		// 1x
		for (int i = 1; i < gb.width - 3; i++)
			fields.add(("1" + (char) ('a' + i)));

		// x1
		for (int i = 2; i < gb.height - 2; i++)
			fields.add(("a" + i));

		// x7
		for (int i = 2; i < gb.height - 2; i++)
			fields.add((i + "" + (char) ('a' + gb.width - 3)));

		// 7x
		for (int i = 1; i < gb.width - 3; i++)
			fields.add((gb.height - 2 + "" + (char) ('a' + i)));

		ArrayList<String> validMoves = new ArrayList<>();

		for (int i = 0; i < fields.size(); i++) {
			if (isValid(((String) fields.get(i))))
				validMoves.add(fields.get(i));
		}

		return validMoves;
	}

//-------------------------------------------------------------------------------------------------------------------------------------------
	protected boolean symbolWon(String symbol) {
		if (this.isRunning() == false) {
			if (this.winner == true && symbol.equals("X") || this.winner == false && symbol.equals("O")) {
				return true;
			} else {
				return false;
			}

		}
		return false;
	}
}

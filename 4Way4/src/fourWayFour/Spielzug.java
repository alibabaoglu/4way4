package fourWayFour;

public class Spielzug {
//	private String[][] board; // charAt
	private int breite;
	private int hoehe;
	private int nummer;
	private char buchstabe;
	private char richtung;
//	private String winner;
//	private GameBoard gb = GameBoard.createBoard(10, 9);
//	

	/*
	 * Ueberprueft ob ein Zugeingabe-String x zulaessig ist und speichert die
	 * Variablen (Nummer num, Buchstabe buch und Richtung rich)
	 */
	public boolean isValidMove(String zugeingabe) {

		// Laenge des Strings x, x<2 oder 3<x
		if (zugeingabe.length() > 3 || zugeingabe.length() < 2) {

			// Laenge des Strings x=4
			if (zugeingabe.length() == 4) {

				// x="10a"rich oder x="10"buch(letzter Buchstabe)rich
				if ((zugeingabe.charAt(0) == '1') && (zugeingabe.charAt(1) == '0') && ((zugeingabe.charAt(2) == 'a')
						|| (zugeingabe.charAt(2) == (char) ('a' + this.breite - 3)))) {
					this.nummer = 10;
					this.buchstabe = zugeingabe.charAt(2);
				}
				// x="a10"rich oder x=buch(letzter Buchstabe)"10"rich
				else if ((zugeingabe.charAt(1) == '1') && (zugeingabe.charAt(2) == '0')
						&& ((zugeingabe.charAt(0) == 'a')
								|| (zugeingabe.charAt(0) == (char) ('a' + this.breite - 3)))) {
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
						|| zugeingabe.charAt(3) == 'l' && this.buchstabe == (char) ('a' + this.breite - 3)
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
				if (zugeingabe.charAt(0) == '1' || zugeingabe.charAt(0) == (char) ('1' + this.hoehe - 3)) {
					this.nummer = Integer.parseInt(("" + zugeingabe.charAt(0)));
					this.buchstabe = zugeingabe.charAt(1);
				}
				// x = buch"1"rich oder buch num(letzte Nummer) rich
				else if (zugeingabe.charAt(1) == '1' || zugeingabe.charAt(1) == (char) ('1' + this.hoehe - 3)) {
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
						&& this.buchstabe == (char) ('a' + this.breite - 3)) {
					return true;
				}
				// x = String mit dem letzter Buchstaben und der letzten Zahl und entweder "l"
				// oder "d"
				else if ((this.richtung == 'l' || this.richtung == 'd') && this.nummer == (1 + this.hoehe - 3)
						&& this.buchstabe == (char) ('a' + this.breite - 3)) {
					return true;
				}
				// x = String mit "a" und der letzten Zahl und entweder "d" oder "r"
				else if ((this.richtung == 'd' || this.richtung == 'r') && this.nummer == (1 + this.hoehe - 3)
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
				if ((zugeingabe.charAt(0) >= '1' && zugeingabe.charAt(0) <= (char) ('1' + this.hoehe - 3))
						&& (zugeingabe.charAt(1) >= 'a' && zugeingabe.charAt(1) <= (char) ('a' + this.breite - 3))) {

					this.nummer = Integer.parseInt(("" + zugeingabe.charAt(0)));
					this.buchstabe = zugeingabe.charAt(1);
				}
				// x = buch num
				else if ((zugeingabe.charAt(1) >= '1' && zugeingabe.charAt(1) <= (char) ('1' + this.hoehe - 3))
						&& (zugeingabe.charAt(0) >= 'a' && zugeingabe.charAt(0) <= (char) ('a' + this.breite - 3))) {

					this.nummer = Integer.parseInt(("" + zugeingabe.charAt(1)));
					this.buchstabe = zugeingabe.charAt(0);
				}
				// x = 2, num und/oder buch ungueltig
				else {
					return false;
				}
				// x = String mit "1" und NICHT "a" oder dem letzten Buchstaben
				if (this.nummer == 1 && this.buchstabe != 'a' && this.buchstabe != ('a' + this.breite - 3)) {
					this.richtung = 'u';
					return true;
				}
				// x = String mit der letzten Zahl und NICHT "a" oder dem letzten Buchstaben
				else if (this.nummer == (1 + this.hoehe - 3) && this.buchstabe != 'a'
						&& this.buchstabe != ('a' + this.breite - 3)) {
					this.richtung = 'd';
					return true;
				}
				// x = String mit "a" und NICHT "1" oder der letzten Zahl
				else if (this.buchstabe == 'a' && this.nummer != 1 && this.nummer != (1 + this.hoehe - 3)) {
					this.richtung = 'r';
					return true;
				}
				// x = String mit dem letzten Buchstaben und NICHT "1" oder der letzten Zahl
				else if (this.buchstabe == ('a' + this.breite - 3) && this.nummer != 1
						&& this.nummer != (1 + this.hoehe - 3)) {
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
//	// Pr�ft ob ein Zug g�ltig ist. Nicht g�ltig wenn Reihe/Spalte voll.
//	private boolean isValid(int x, int y) {
//		direction(x, y);
//
//		if (up) {
//			for (int i = x; i > 0; i--) {
//				if (board[i][y] == 0)
//					return true;
//			}
//		} else if (down) {
//			for (int i = x; i < board.length; i++) {
//				if (board[i][y] == 0)
//					return true;
//			}
//		} else if (left) {
//			for (int i = y; i > 0; y--) {
//				if (board[x][i] == 0)
//					return true;
//			}
//		} else if (right) {
//			for (int i = y; i < board[0].length; y++) {
//				if (board[x][i] == 0)
//					return true;
//			}
//		}
//
//		return false;
//	}
//---------------------------------------------------------------------------------------------------------
//	public void setStein(int x, char b) {
//	b -= '0' * 2;
//	int y = b;
//	int x = 0;
//
//	char zeile = (char) (x + '0');
//
//	while (this.board[x][0] != zeile)
//		i++;
//	if (this.board[x][y] == 0)
//		board[x][y] = 'X';
//
//	if (isValid(x, y)) {
//		verschiebeSteine(x, y);
//
//	} else
//		throw new IllegalArgumentException("Zug nicht gültig");
//}
//---------------------------------------------------------------------------------------------------------
//public boolean isRunning() {
//if(end==true) {
//	return false;
//}
//return true;
//}
//---------------------------------------------------------------------------------------------------------
//public boolean whoWon() {
//if (this.winner.equals("spieler1")) {
//	return true;
//}else {
//	return false;
//}
//}
//---------------------------------------------------------------------------------------------------------
//public int move(char spieler, String zugeingabe) {
//	if (isValidMove(zugeingabe) == false) {
//		return -1; // ungültiger Zug
//	}
//	if(zugeingabe.length()==3||zugeingabe.length()==4) {
//		setStein(spieler, nummer, buchstabe, richtung)
//	}
//	if(zugeingabe.length()==2) {
//		setStein(spieler, nummer, buchstabe)
//	}
////	
//	return 1; // letzter Zug
//	// return=0; //normaler Zug
//}

//------------------------------------------------------------------------------------------------------------
//	protected String[][] gameMove(char richtung) {
//		if (richtung == 'u') {
//			// Für die Länge der Zeilen, Spaltenweise nach oben rücken von O/X, rekursiv
//		}
//		if (richtung == 'd') {
//			// Für die Länge der Zeilen, Spaltenweise nach unten rücken von O/X, rekursiv
//		}
//		if (richtung == 'l') {
//			// Für die Länge der Spalten, werden die O/X der Zeilen nach links gerückt,
//			// rekursiv
//		}
//		if (richtung == 'r') {
//			// Für die Länge der Spalten, werden die O/X der Zeilen nach rechts gerückt,
//			// rekursiv
//		}
//	}
//---------------------------------------------------------------------------------------------------------
////	private void verschiebeSteine(int x, int y) {
////		// TODO: Anpassen an das Board Algorithmus stimmt aber wurde erstellt nur für
////		// eine zeile.
////		if (up) {
////			int i, j;
////			for (int z = 1; z < board[0].length - 2; z++) {
////				i = 1;
////				while (i < board.length - 2 && board[i][z] == 0) {
////					i++;
////				}
////				if (board[i][z] != 0) {
////
////					board[i][z] = 0;
////					j = i;
////					while (board[j][z] == 0 && j > 0)
////						if (board[j - 1][z] == 0)
////							j--;
////						else
////							break;
////					board[j][z] = 'X';
////
////				}
////
////			}
////		}
////
////	}
//---------------------------------------------------------------------------------------------------------
//	// Bestimmt in welche Richtung sich die Steine verschieben.
//
////	private void direction(int x, int y) {
////		int maxCol = board[0].length - 2;
////		int maxRow = board.length - 2;
////
////		up = false;
////		down = false;
////		left = false;
////		right = false;
////
////		if (x != 1 && x != maxRow) {
////			if (y == 1) {
////				right = true;
////			} else if (y == maxCol) {
////				left = true;
////			}
////		}
////		if (y != 1 && y != maxCol) {
////			if (x == maxRow) {
////				up = true;
////			} else if (x == 1) {
////				down = true;
////			}
////		}
////
////	}
//---------------------------------------------------------------------------------------------------------
//verschiebe alle Steine in die angegebene Richtung bekommt als param die
//Koordinaten
//
//	private void verschiebeSteine(int x, int y) {
////TODO: Anpassen an das Board Algorithmus stimmt aber wurde erstellt nur f�r eine zeile.
//		if (up) {
//			int i, j;
//			for (int z = 1; z < board[0].length - 2; z++) {
//				i = 1;
//				while (i < board.length - 2 && board[i][z] == 0) {
//					i++;
//				}
//				if (board[i][z] != 0) {
//
//					board[i][z] = 0;
//					j = i;
//					while (board[j][z] == 0 && j > 0)
//						if (board[j - 1][z] == 0)
//							j--;
//						else
//							break;
//					board[j][z] = 'X';
//
//				}
//
//			}
//			}	
//	}
//---------------------------------------------------------------------------------------------------------
//	//Bestimmt in welche Richtung sich die Steine verschieben.
//
//	private void direction(int x, int y) {
//		int maxCol = board[0].length - 2;
//		int maxRow = board.length - 2;
//
//		up = false;
//		down = false;
//		left = false;
//		right = false;
//
//		if (x != 1 && x != maxRow) {
//			if (y == 1) {
//				right = true;
//			} else if (y == maxCol) {
//				left = true;
//			}
//		}
//		if (y != 1 && y != maxCol) {
//			if (x == maxRow) {
//				up = true;
//			} else if (x == 1) {
//				down = true;
//			}
//		}
//
//	}
//---------------------------------------------------------------------------------------------------------
//	//setzt ein Stein
//
//	public void setStein(int x, char b) {
//		b -= '0' * 2;
//		int y = b;
//		int i = 0;
//
//		char zeile = (char) (x + '0');
//
//		while (this.board[i][0] != zeile)
//			i++;
//		if (this.board[i][y] == 0)
//			board[i][y] = 'X';
//		
//		if (isValid(i, y)) {
//		verschiebeSteine(i, y);
//
//		} else
//			throw new IllegalArgumentException("Zug nicht g�ltig");
//
//	}
//---------------------------------------------------------------------------------------------------------
//* Verschiebung: Erste Freie Stelle Merken, wenn keine da zur nächsten
//* Zeile/Spalte Erstes Zeichen suchen, wenn keins nach erster freien Stelle, zur
//* nächsten Zeile/Spalte Zeichen Merken und Löschen und an die erste freie
//* Stelle setzen Wenn alle Zeilen/Spalten durch, Spielbrett ausgeben
}

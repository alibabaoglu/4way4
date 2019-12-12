package fourWayFour;

public class Spielzug {
//	private String[][] board; // charAt
	private int nummer;
	private int hoehe;
	private int breite;
	private char buchstabe;
	private char richtung;
//	private String winner;
//	private GameBoard gb = GameBoard.createBoard(10, 9);
//	

	/*
	 * String länge und int bestimmen->char und int auf richtigkeit überprüfen,
	 * richtung auf richtigkeit überprüfen
	 */
	@SuppressWarnings("unused")
	private boolean isValidMove(String zugeingabe) { // Speicherung nummer, buchstabe (undd richtung)
		if (zugeingabe.length() > 3 || zugeingabe.length() < 2) {// Laenge x, x<2 oder 3<x
			if (zugeingabe.length() == 4) { // Ausnahme 10
				if ((zugeingabe.charAt(0) == '1') && (zugeingabe.charAt(1) == '0')
						&& ((zugeingabe.charAt(2) == 'a') || (zugeingabe.charAt(2) == (char) ('a' + this.breite - 3)))) {
					
					this.nummer = 10;
					this.buchstabe = zugeingabe.charAt(2);
				
				} else if ((zugeingabe.charAt(1) == '1') && (zugeingabe.charAt(2) == '0')
						&& ((zugeingabe.charAt(0) == 'a') || (zugeingabe.charAt(0) == (char) ('a' + this.breite - 3)))) {
					
					this.nummer = 10;
					this.buchstabe = zugeingabe.charAt(0);
				
				} else {
					return false;
				}
				if (zugeingabe.charAt(3) == 'd'
						|| zugeingabe.charAt(3) == 'l' && this.buchstabe == (char) ('a' + this.breite - 3)
						|| zugeingabe.charAt(3) == 'r' && this.buchstabe == 'a') {
				
					this.richtung = zugeingabe.charAt(3);
					return true;
				
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {// Laenge x, 2<=x<=3
			if(zugeingabe.length()==3) {
				if((zugeingabe.charAt(0) == '1'||zugeingabe.charAt(0) == (char)('1'+this.hoehe-3))&&(zugeingabe.charAt(1) == 'a'||zugeingabe.charAt(1) == (char) ('a' + this.breite - 3))) {
					
				}
			}
		}
		// Integer.parseInt("123");
		return true;
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

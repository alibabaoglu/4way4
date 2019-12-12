package fourWayFour;

import java.util.*;

public class Menu {

	final static int SPIEL_BEENDEN = 0;
	final static int COMPUTER = 1;
	final static int SPIELER = 2;
	final static char SPIELER_1 = 'X';
	final static char SPIELER_2 = 'O';

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		String spieler1 = null;
		String spieler2 = null;
		int gspieler;
		int spielzug = 0;
		int zeilenlaenge;
		int spaltenlaenge;
		int wiederholen;
		boolean gueltigeEingabe;
		GameBoard gb;
		char spieler;

		do {

			// Auswahl Spielmodus
			do {
				Ausgabensammlung.ausgabeMitAbsatz("Druecken Sie die '1' fuer ein Spiel gegen den Computer" + "\n\t"
						+ "oder die '2' fuer ein Spiel gegen einen anderen Spieler.");
				gspieler = in.nextInt();
				if (gspieler != COMPUTER && gspieler != SPIELER) {
					Ausgabensammlung.ausgabeMitAbsatz("Ungueltige Eingabe!");
					gueltigeEingabe = false;
				} else {
					gueltigeEingabe = true;
				}
			} while (gueltigeEingabe == false);

			// Auswahl Hoehe des Spielbretts
			do {
				Ausgabensammlung
						.ausgabeMitAbsatz("Geben Sie eine Spaltenlaenge fuer das Spielbrett zwischen 7 und 10 ein.");
				spaltenlaenge = in.nextInt();
				if (spaltenlaenge < 7 || spaltenlaenge > 10) {
					Ausgabensammlung.ausgabeMitAbsatz("Ungueltige Eingabe!");
					gueltigeEingabe = false;
				} else {
					gueltigeEingabe = true;
				}
			} while (gueltigeEingabe == false);

			// Auswahl Breite des Spielbretts
			do {
				Ausgabensammlung
						.ausgabeMitAbsatz("Geben Sie eine Zeilenlaenge fuer das Spielbrett zwischen 7 und 10 ein.");
				zeilenlaenge = in.nextInt();
				if (zeilenlaenge < 7 || spaltenlaenge > 10) {
					Ausgabensammlung.ausgabeMitAbsatz("Ungueltige Eingabe!");
					gueltigeEingabe = false;
				} else {
					gueltigeEingabe = true;
				}
			} while (gueltigeEingabe == false);

			gb = GameBoard.createBoard(spaltenlaenge, zeilenlaenge);
			gb.printBoard();
//			if (gspieler == COMPUTER) {
//				Ausgabensammlung.ausgabe("Wenn KI anfangen soll, dann druecke die 3");
//				if (in.nextInt()==3) {
//					spieler1="Computer";
//					spieler2="Spieler";
//				}
//				else {
//					spieler1="Spieler";
//					spieler2="Computer";
//				}
//
//			} else {
////			Auswahl Spieler 1/2?
//			}
			while (gb.isRunning() == true) {
				if (spielzug % 2 == 0) {
					Ausgabensammlung.ausgabe("Spieler 1 ist dran: ");
					spieler=SPIELER_1;
				} else {
					Ausgabensammlung.ausgabe("Spieler 2 ist dran: ");
					spieler=SPIELER_1;
				}
//				Spielzug.move (spieler,in.next()); //mithilfe spielzug-string spielzug-methode aufrufen
//				gb.setStein(spieler,1,'d','l');
				spielzug++;
			}
			spielzug = 0;
//			if (gb.whoWon()==true) {
//				Ausgabensammlung.ausgabeMitAbsatz(spieler1+" hat gewonnen");
//			}else{
//				Ausgabensammlung.ausgabeMitAbsatz(spieler2+" hat gewonnen");
//			}
			Ausgabensammlung.ausgabeMitAbsatz(
					"Wenn Sie das Menu beenden wollen druecken Sie die 0, um ein neues Spiel zu starten druecken Sie eine beliebige andere Zahl");
			wiederholen = in.nextInt();
		} while (wiederholen != SPIEL_BEENDEN);
		in.close();
	}
}

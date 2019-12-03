package fourWayFour;

import java.util.*;

public class Menue {

	final static int SPIEL_BEENDEN = 0;
	final static int COMPUTER = 1;
	final static int SPIELER_2 = 2;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int spieler1;
		int spieler2;
		int gspieler;
		int zeilenlaenge;
		int spaltenlaenge;
		int wiederholen;
		boolean gueltigeEingabe;
		GameBoard gb;

		do {

			// Auswahl Spielmodus
			do {
				Ausgabensammlung.ausgabeMitAbsatz("Drücken Sie die '1' für ein Spiel gegen den Computer" + "\n\t"
						+ "oder die '2' für ein Spiel gegen einen anderen Spieler.");
				gspieler = in.nextInt();
				if (gspieler != COMPUTER && gspieler != SPIELER_2) {
					Ausgabensammlung.ausgabeMitAbsatz("Ungültige Eingabe!");
					gueltigeEingabe = false;
				} else {
					gueltigeEingabe = true;
				}
			} while (gueltigeEingabe == false);

			// Auswahl Höhe des Spielbretts
			do {
				Ausgabensammlung
						.ausgabeMitAbsatz("Geben Sie eine Spaltenlänge für das Spielbrett zwischen 7 und 10 ein.");
				spaltenlaenge = in.nextInt();
				if (spaltenlaenge < 7 || spaltenlaenge > 10) {
					Ausgabensammlung.ausgabeMitAbsatz("Ungültige Eingabe!");
					gueltigeEingabe = false;
				} else {
					gueltigeEingabe = true;
				}
			} while (gueltigeEingabe == false);

			// Auswahl Breite des Spielbretts
			do {
				Ausgabensammlung
						.ausgabeMitAbsatz("Geben Sie eine Zeilenlänge für das Spielbrett zwischen 7 und 10 ein.");
				zeilenlaenge = in.nextInt();
				if (zeilenlaenge < 7 || spaltenlaenge > 10) {
					Ausgabensammlung.ausgabeMitAbsatz("Ungültige Eingabe!");
					gueltigeEingabe = false;
				} else {
					gueltigeEingabe = true;
				}
			} while (gueltigeEingabe == false);

			gb = GameBoard.createBoard(spaltenlaenge, zeilenlaenge);
			gb.printBoard();
//			if (gspieler == COMPUTER) {
//				Ausgabensammlung.ausgabe("Wenn KI anfangen soll, dann drücke die 3");
//				if (in.nextInt()==3) {
//					spieler1=COMPUTER;
//					spieler2=SPIELER_2;
//				}
//				else {
//					spieler1=SPIELER_2;
//					spieler2=COMPUTER;
//				}
//
//			} else {
////			Auswahl Spieler 1/2?
//			}
			Ausgabensammlung.ausgabeMitAbsatz(
					"Wenn Sie das Spiel beenden wollen drücken Sie die 0, um ein neues Spiel zu starten drücken Sie eine beliebige andere Zahl");
			wiederholen = in.nextInt();
		} while (wiederholen != SPIEL_BEENDEN);
		in.close();
	}
}

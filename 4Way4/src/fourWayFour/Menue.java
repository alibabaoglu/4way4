package fourWayFour;

import java.util.*;

public class Menue {

	final static int COMPUTER = 1;
	final static int SPIELER_2 = 2;
	final static boolean spiel_beenden = false;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int gspieler;
		int zeilenlaenge;
		int spaltenlaenge;
		boolean guetligeEingabe = true;

		Ausgabensammlung.ausgabe(
				"Druecken Sie '1' für ein Spiel gegen Computer, '2' für ein Spiel gegen einen anderen Spieler.");
		gspieler = in.nextInt();
		if (gspieler != COMPUTER || gspieler != SPIELER_2) {
//			TODO trycatch
		}
		Ausgabensammlung.ausgabe("Geben Sie die Spaltenlaenge zwischen 7 und 10 ein.");
		spaltenlaenge = in.nextInt();
		if (spaltenlaenge < 7 || spaltenlaenge > 10) {
//			TODO ERROR
		}
		Ausgabensammlung.ausgabe("Geben Sie die Zeilenlaenge zwischen 7 und 10 ein.");
		zeilenlaenge = in.nextInt();
		if (zeilenlaenge < 7 || spaltenlaenge > 10) {
//			TODO ERROR
		}
		Ausgabensammlung.ausgabe("Eingabe: ");

		if (gspieler == COMPUTER) {
			Ausgabensammlung.ausgabe("Wenn KI anfangen soll, dann druecke die 3");

		} else {
//			Auswahl Spieler 1/2?
			in.close();
		}
	}
}
package fourWayFour;

import java.util.*;

public class Menu {
	final static int COMPUTER = 1;
	final static int SPIELER = 2;
	final static int END = 5;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		GameEngine engine;
		boolean validInput = false, validMove = true;
		String move = "", erster, zweiter;
		int row, col, modus;

		do {
			do {
				System.out.println("Wählen Sie einen Spielmouds:\n \"1\" "
						+ "fuer ein Spiel gegen KI\n \"2\"fuer ein Spiel gegen einen anderen Spieler");

				modus = sc.nextInt();
				if (modus == COMPUTER || modus == SPIELER)
					validInput = true;
				else
					System.out.println("Ungültige Eingabe:" + modus);
			} while (!validInput);

			do {
				System.out.println("Geben Sie eine Spaltenlaenge fuer das Spielbrett zwischen 7 und 10 ein.");
				col = sc.nextInt();
				if (col < 7 || col > 10) {
					System.out.println("Ungueltige Eingabe: " + col);
					validInput = false;
				} else
					validInput = true;

			} while (!validInput);

			do {
				System.out.println("Geben Sie eine Zeilenlaenge fuer das Spielbrett zwischen 7 und 10 ein.");
				row = sc.nextInt();
				if (row < 7 || row > 10) {
					System.out.println("Ungueltige Eingabe:" + row);
					validInput = false;
				} else
					validInput = true;

			} while (!validInput);

			if (modus == COMPUTER) {
				System.out.println("Wenn KI anfangen soll, dann druecke die \"3\" ,sonst die \"1\" ");
				if (sc.nextInt() == 3) {
					erster = "KI";
					zweiter = "Spieler";
				} else {
					erster = "Spieler";
					zweiter = "KI";
				}

			} else {
				erster = "Spieler-1";
				zweiter = "Spieler-2";
			}
			engine = new GameEngine(row, col, modus, erster);

			while (engine.isRunning()) {

				do {
					if (!validMove)
						System.out.println("Ungueltiger Zug");

					System.out.println(erster + " ist am Zug:");
					if (!erster.equals("KI")) {
						move = sc.next();
						validMove = engine.isVaildMove(move);
					} else
						break;

				} while (!validMove);

				engine.myMove(move);

				if (engine.isRunning()) {
					do {
						if (!validMove)
							System.out.println("Ungueltiger Zug");

						System.out.println(zweiter + "ist am Zug:");
						if (!zweiter.equals("KI")) {
							move = sc.next();
							validMove = engine.isVaildMove(move);
						} else
							break;

					} while (!(validMove = engine.isVaildMove(move)));
					engine.myMove(move);
				}
			}
			if (!engine.isRunning()) {
				if (engine.whoWon())
					System.out.println("Spieler-1 hat gewonnen");
				else
					System.out.println("Spieler-2 hat gewonnen");
			}
			System.out.println("Wenn Sie aufhören wollen drücken Sie die 5, andernfalls drücken Sie eine andere Zahl");
		} while (sc.nextInt() != END);
		sc.close();
	}
}

package fourWayFour;

import java.util.*;

public class Menu {
	final static int COMPUTER = 1;
	final static int SPIELER = 2;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		GameEngine engine;
		boolean validInput = false, validMove = true;
		String move;
		int row, col, modus, beginner;

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
			System.out.println("Wenn KI anfangen soll, dann druecke die \"3\"");
			if (sc.nextInt() == 3)
				beginner = COMPUTER;
			else
				beginner = SPIELER;

			engine = new GameEngine(row, col, COMPUTER, beginner);
		} else
			engine = new GameEngine(row, col, SPIELER);

		while (engine.isRunning()) {

			do {
				if (!validMove)
					System.out.println("Ungueltiger Zug");

				System.out.println("Spieler-1 ist am Zug:");
				move = sc.next();
				validMove = engine.isVaildMove(move);
			} while (!validMove);

			engine.myMove(move);
			

			do {
				if (!validMove)
					System.out.println("Ungueltiger Zug");

				System.out.println("Spieler-2 ist am Zug:");
				move = sc.next();

			} while (!(validMove = engine.isVaildMove(move)));
			engine.myMove(move);


		}
		if (!engine.isRunning()) {
			if (engine.whoWon())
				System.out.println("Spieler-1 hat gewonnen");
			else
				System.out.println("Spieler-2 hat gewonnen");
		}

	}

}


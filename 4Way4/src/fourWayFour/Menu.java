package fourWayFour;

import java.util.*;

public class Menu {
	final static int COMPUTER = 1;
	final static int PLAYER = 2;
	final static int END = 0;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		GameEngine engine;

		boolean validInput = false;
		String move = "";
		String first = "";
		String second = "";
		int mode;
		int col;
		int row;
		int starter = 0;
		int difficulty;
		int counter = 0;

		// ein ganzes Spiel
		do {
//			// Auswahl ob Spieler-Spieler oder Spieler-KI
			do {
				OutputCollection.outputWithNewLine(
						"Waehlen Sie einen Spielmodus:\n   \"1\": Spieler   VS KI\n   \"2\": Spieler-1 VS Spieler-2");
				mode = sc.nextInt();
				if (mode == COMPUTER || mode == PLAYER)
					validInput = true;
				else
					OutputCollection.outputWithNewLine("Ungueltige Eingabe: " + mode + "\n");
			} while (!validInput);

//			// Auswahl der Hoehe des Spielbretts
			do {
				OutputCollection.outputWithNewLine(
						"Geben Sie die Spaltenlaenge fuer das Spielbrett zwischen \"7\" und \"10\" ein.");
				col = sc.nextInt();
				if (col < 7 || col > 10) {
					OutputCollection.outputWithNewLine("Ungueltige Eingabe: " + col + "\n");
					validInput = false;
				} else
					validInput = true;
			} while (!validInput);

//			// Auswahl der Breite des Spielbretts
			do {
				OutputCollection.outputWithNewLine(
						"Geben Sie die Zeilenlaenge fuer das Spielbrett zwischen \"7\" und \"10\" ein.");
				row = sc.nextInt();
				if (row < 7 || row > 10) {
					OutputCollection.outputWithNewLine("Ungueltige Eingabe: " + row + "\n");
					validInput = false;
				} else
					validInput = true;
			} while (!validInput);

//			// Wenn Spieler-KI dann wird festgelegt wer anfaengt
			if (mode == COMPUTER) {
				do {
					OutputCollection.outputWithNewLine("Wer faengt an?:\n   \"1\": KI\n   \"2\": Sie");
					starter = sc.nextInt();
					if (starter == COMPUTER) {
						first = "KI";
						second = "Spieler";
						validInput = true;
					} else if (starter == PLAYER) {
						first = "Spieler";
						second = "KI";
						validInput = true;
					} else {
						OutputCollection.outputWithNewLine("Ungueltige Eingabe: " + starter + "\n");
						validInput = false;
					}
				} while (!validInput);
			}

//			// Wenn Spieler-Spieler dann faengt Spieler-1 an
			else {
				first = "Spieler-1";
				second = "Spieler-2";
			}

//			// Wenn Spieler-KI, Auswahl der Schwierigkeit der KI und erstellen des Spiels
			if (mode == COMPUTER) {
				do {
					OutputCollection.outputWithNewLine(
							"Waehlen Sie den Schwierigkeitsgrad der KI:\n   \"1\": leichte KI\n   \"2\": mittlere KI\n   \"3\": schwere KI");
					difficulty = sc.nextInt();
					if (difficulty >= 1 && difficulty <= 3) {
						validInput = true;
					} else {
						validInput = false;
						OutputCollection.outputWithNewLine("Ungueltige Eingabe: " + difficulty + "\n");
					}
				} while (!validInput);
				engine = new GameEngine(row, col, mode, difficulty, first);// TODO
			}

//			// Wenn Spieler-Spieler erstellen des Spiels
			else {
				engine = new GameEngine(row, col, mode);// TODO
			}

//			// Der Spielablauf
			OutputCollection.outputWithNewLine("Spielfeld:");
			engine.printBoard();
			while (engine.isRunning()) {
				counter++;

				// Eingabe des Spielzugs
				do {

					if (counter % 2 != 0) {
						OutputCollection.outputWithNewLine(first + " ist am Zug");
					} else {
						OutputCollection.outputWithNewLine(second + " ist am Zug");
					}

					if (mode == COMPUTER) {
						if ((starter == COMPUTER && counter % 2 != 0) || (starter == PLAYER && counter % 2 == 0)) {
							move = "";
							// OutputCollection.outputWithNewLine("Zugeingabe:\n");// TODO
						} else {
							OutputCollection.outputWithNewLine("Zugeingabe:");
							move = sc.next();
						}
					}
					if (mode == PLAYER) {
						OutputCollection.outputWithNewLine("Zugeingabe:");
						move = sc.next();
					}
					validInput = engine.isVaildMove(move);
					if (!validInput && !(mode == COMPUTER && ((starter == COMPUTER && counter % 2 != 0)
							|| (starter == PLAYER && counter % 2 == 0)))) {
						OutputCollection.outputWithNewLine("Ungueltige Eingabe: " + move + "\n");
					}

				} while (!validInput && !(mode == COMPUTER
						&& ((starter == COMPUTER && counter % 2 != 0) || (starter == PLAYER && counter % 2 == 0))));

				engine.myMove(move);
				engine.printBoard();
			}

//			// Spielende
			counter = 0;
			if (!engine.isRunning()) {
				if (engine.whoWon())
					OutputCollection.outputWithNewLine(first + " hat gewonnen");
				else
					OutputCollection.outputWithNewLine(second + " hat gewonnen");
			}

			OutputCollection.outputWithNewLine(
					"Wenn Sie aufhören wollen drücken Sie die \"0\", andernfalls drücken Sie eine andere Zahl");
		} while (sc.nextInt() != END);
		OutputCollection.outputWithNewLine("Das Spiel wurde beendet.");
		sc.close();
	}
}

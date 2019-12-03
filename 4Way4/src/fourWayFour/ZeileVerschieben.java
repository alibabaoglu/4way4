package fourWayFour;
/*
 * TEST!!!!
 * ALGORITHMUS NUR FÜR EINE ZEILE 
 * VERSCHIEBUNG UP
 */
public class ZeileVerschieben {
	static char board[][] = new char[10][1];

	public static void verschiebe() {
		int i = 0;
		int j;
		while (i < board.length - 1) {
			while (board[i][0] == '-' || board[i][0] != '-' && i == 0
					|| board[i][0] != '-' && board[(i - 1)][0] != '-') {
				i++;
			}

			board[i][0] = '-';
			j = i;
			while (board[j][0] == '-' && j > 0)
				if (board[j - 1][0] == '-')
					j--;
				else
					break;
			board[j][0] = 'X';

		}

	}

	public static void main(String[] args) {
		board[0][0] = 'X';
		board[1][0] = 'X';
		board[2][0] = 'X';
		board[5][0] = 'X';
		board[7][0] = 'X';
		board[9][0] = 'X';

		for (int i = 0; i < board.length; i++) {
			if (board[i][0] == 0)
				board[i][0] = '-';

		}

		for (int i = 0; i < board.length; i++) {
			System.out.println(board[i][0]);
		}
		verschiebe();
		System.out.println("");
		for (int i = 0; i < board.length; i++) {
			System.out.println(board[i][0]);

		}

	}
}

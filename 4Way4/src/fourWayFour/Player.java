package fourWayFour;

public class Player {

	private static int number = 0;

	protected int playerNumber = 0;
	protected String symbol;

	public Player() {
		Player.number++;
		this.playerNumber = (Player.number % 2 == 0 ? 2 : 1);
		this.symbol = (this.playerNumber == 1 ? "X" : "O");
	}

	public void myMove(String input) {
		// TODO Auto-generated method stub
	}

	public String yourMove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return ("Spieler " + this.playerNumber);
	}
}

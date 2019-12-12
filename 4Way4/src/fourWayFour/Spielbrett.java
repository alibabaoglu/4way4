package fourWayFour;

public interface Spielbrett {

	public boolean isRunning();
	public boolean whoWon();
	public void printBoard();
	public boolean isVaildMove(String eingabe);
}

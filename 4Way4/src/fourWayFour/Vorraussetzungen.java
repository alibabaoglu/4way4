package fourWayFour;

public interface Vorraussetzungen {

	public void myMove(String eingabe);
	public String yourMove();
	public boolean isRunning();
	public boolean whoWon();
	public void printBoard();
	public boolean isVaildMove(String eingabe);
}

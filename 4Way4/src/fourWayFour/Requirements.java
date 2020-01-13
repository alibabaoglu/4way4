package fourTheWin;

/**
 * Anforderungen der "Staatssekretäre"
 */
public interface Requirements {

	public String yourMove();

	public boolean isRunning();

	public boolean whoWon();

	public boolean isVaildMove(String input);

	public void printBoard();

//	public void setStart(boolean starter);

	public void myMove(String input);
}

package ki2;

/**
 * Anforderungen der "Staatssekretäre"
 */
public interface Requirements {

	public void myMove(String input);

	public String yourMove();

	public boolean isRunning();

	public boolean whoWon();

	public void printBoard();

	public boolean isVaildMove(String input);
}

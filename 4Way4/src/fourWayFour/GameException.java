package fourWayFour;

/**
 * Fehler wegen falscher Eingabe bein Spiel
 */
@SuppressWarnings("serial")
public class GameException extends IllegalArgumentException {

	public GameException(String msg) {
		super(msg);
	}
}

package fourWayFour;

public class TestGameMove {
	public static void main(String[] args) {
		
		Player s1= new Player();
		Player s2= new Player();
		
		
		GameBoard gb= GameBoard.createBoard(7, 9);
		GameMove sz = new GameMove(gb);
		//Testspiel Orpheas vs Ali
		sz.setStone(s1.symbol,"1c");
		sz.setStone(s2.symbol,"1au");
		sz.setStone(s1.symbol,"b7");
		sz.setStone(s2.symbol,"b7");
		sz.setStone(s1.symbol,"3a");
		sz.setStone(s2.symbol,"h7");
		sz.setStone(s1.symbol,"f1");
		sz.setStone(s2.symbol,"g1");
		sz.setStone(s1.symbol,"g1");
		sz.setStone(s2.symbol,"f7");
		
		
		}
	}


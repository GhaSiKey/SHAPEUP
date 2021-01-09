package cardgame;

import java.util.Random;

/**
 * 
 * The class HardComputer implements the interface Strategy.
 * Hard computer can do all the same like player.
 *
 */
public class HardComputer implements Strategy {


	/**
	 * The computer choose the strategy it will use.
	 * 
	 * @param chessboard The chessboard it is playing
	 * 
	 * @see Chessboard
	 */
	@Override
	public int choose(Chessboard chessboard) {
		int choose = 0;
		if (chessboard.GetCardNumber() > 5) {
			Random random = new Random();
			choose= random.nextInt(3);
		}
		return choose;
	}

}

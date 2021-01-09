package cardgame;

import java.util.Random;

public class HardComputer implements Strategy {

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

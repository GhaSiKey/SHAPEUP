package cardgame;
/**
 *Strategy mode interface.
 *
 */
public interface Strategy {
	/**
	 * The method of choosing the strategy.
	 * @param chessboard the chessboard it is playing
	 * @return the number of the strategy.
	 */
	public int choose(Chessboard chessboard);
}

package cardgame;

/**
 * It is the class of game modes.
 * 
 *
 */
public class GameRegle {
	
	private int people;
	private int level;
	private int map;

	/**
	 * This is the constructor of GameRegle with parameter.
	 * It contains the level, the shape of chessboard and number of players.
	 * @param p The number of people
	 * @param l The level you want to play
	 * @param m the shape of chess board you wants to play
	 */
	public GameRegle(int p,int l,int m) {
		this.people = p;
		this.level = l;
		this.map = m;
	}
	
	/**
	 * Set the number of players.
	 * @param p The number of people
	 */
	public void SetPeople(int p) {
		this.people = p;
	}
	
	/**
	 * Set the level of the game.
	 * @param l The level you want to play
	 */
	public void SetpLevel(int l) {
		this.level = l;
	}
	
	/**
	 * Set the shape of the chessboard.
	 * @param m the shape of chess board you wants to play
	 */
	public void SetMap(int m) {
		this.map = m;
	}
	
	/**
	 * 
	 * @return the number of players.
	 */
	public int GetPeople() {
		return this.people;
	}
	
	/**
	 * 
	 * @return the level you have chosen.
	 */
	public int GetLevel() {
		return this.level;
	}
	
	
	/**
	 * 
	 * @return the shape of the chessboard you have chosen.
	 */
	public int GetMap() {
		return this.map;
	}
	
	/**
	 * We use String toString() methode to show the mode you have chosen to play.
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Nbplayer:"+people+" Level:"+level+" Map:"+map);
		return sb.toString(); 
	}
}

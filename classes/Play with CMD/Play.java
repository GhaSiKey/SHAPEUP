package cardgame;

import java.util.LinkedList;

/**
 * Class Play is used to create player.
 * And we use LinkedList for storing the handcards
 * 
 *@see Card
 */
public abstract class Play { 
	
	private String name;                    //玩家名字
	private int totalScore;                //游戏获得的总分
	protected LinkedList<Card> handCards;     //手牌
	private Card vectoryCard;                  //胜利牌
	
	
	/**
	 * This is the constructor of play with parameter.
	 * @param name The name of player
	 * 
	 * @see Card
	 */
	public Play(String name) {           //构造一个玩家（玩家/电脑）
		this.name = name;
		this.totalScore = 0;
		handCards = new LinkedList<Card>(); 
	}
	
	/**
	 * Set the victory card of the player or the computer.
	 * @param card The first card we draw
	 * 
	 * @see Card
	 */
	public void setVectoryCard(Card card) {    //设定该玩家的胜利牌
		this.vectoryCard = card;
	}
	
	
	/**
	 * The method of drawing cards, the first card of the handcard is the victory card.
	 * 
	 * @param card The card we will draw
	 * 
	 * @see Card
	 */
	public void DrawCard(Card card) {     //抽一张卡加入到手牌当中
		if (vectoryCard == null) {        //第一张牌是胜利牌
			this.vectoryCard = card;
		}
		else {
			handCards.add(card);	
		}
	}
	
	
	/**
	 * We get the name of the players.
	 * 
	 * @return the name of the player
	 */
	public String getName() {
		return this.name;
	}
	
	/**We get the final score of the players or compueters
	 * 
	 * @return the final score
	 */
	public int getTotalScore() {
		return this.totalScore;
	}
	
	/**
	 * We show out the victory card.
	 * 
	 * @return the victory card
	 * 
	 * @see Card
	 */
	public Card VectoryCard() {
		return this.vectoryCard;
	}
	
	/**
	 * We use String toString() method to show the information of the players.
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(name+":"+handCards+" VectoyCard:"+vectoryCard);
		return sb.toString(); 
	}
	
	/**
	 * Method of putting the card in the chess board.
	 * @param chessboard The chessboard the player is playing 
	 * 
	 * @see Chessboard
	 */
	public abstract void PlayCard(Chessboard chessboard);
	
	
	/**
	 * Method of moving the card.
	 * @param chessboard The chessboard the player is playing
	 * 
	 * @see Chessboard
	 */
	public abstract void MoveCard(Chessboard chessboard);
}

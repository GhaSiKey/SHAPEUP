package cardgame;

import java.util.LinkedList;
import java.util.Observable;

@SuppressWarnings("deprecation")
public abstract class Play extends Observable{ 
	
	private String name;                    //玩家名字
	private int totalScore;                //游戏获得的总分
	public LinkedList<Card> handCards;     //手牌
	private Card vectoryCard;                  //胜利牌
	
	public Play(String name) {           //构造一个玩家（玩家/电脑）
		this.name = name;
		this.totalScore = 0;
		handCards = new LinkedList<Card>(); 
	}
	
	public void setVectoryCard(Card card) {    //设定该玩家的胜利牌
		this.vectoryCard = card;
	}
	 
	public void DrawCard(Card card) {     //抽一张卡加入到手牌当中
		if (vectoryCard == null) {        //第一张牌是胜利牌
			this.vectoryCard = card;
		}
		else {
			handCards.add(card);
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getTotalScore() {
		return this.totalScore;
	}
	
	public Card VectoryCard() {
		return this.vectoryCard;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(name+":"+handCards+" VectoyCard:"+vectoryCard);
		return sb.toString(); 
	}
	
	public abstract void PlayCard(Chessboard chessboard);
	
	public abstract void MoveCard(Chessboard chessboard);
}

package cardgame;

import java.util.LinkedList;
import java.util.Observable;

@SuppressWarnings("deprecation")
public abstract class Play extends Observable{ 
	
	private String name;                    //�������
	private int totalScore;                //��Ϸ��õ��ܷ�
	public LinkedList<Card> handCards;     //����
	private Card vectoryCard;                  //ʤ����
	
	public Play(String name) {           //����һ����ң����/���ԣ�
		this.name = name;
		this.totalScore = 0;
		handCards = new LinkedList<Card>(); 
	}
	
	public void setVectoryCard(Card card) {    //�趨����ҵ�ʤ����
		this.vectoryCard = card;
	}
	 
	public void DrawCard(Card card) {     //��һ�ſ����뵽���Ƶ���
		if (vectoryCard == null) {        //��һ������ʤ����
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

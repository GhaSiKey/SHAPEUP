package cardgame;

import java.util.LinkedList;

public class Deck {
	
	public final int number_of_cards=COLOR.values().length*SHAPE.values().length*FILLED.values().length;
	private LinkedList<Card> deck;                       //�ƿ�ı�����deck
	private Card hiddenCard;                             //ÿ�ֱ�����ʼ��������
	private int count = 1;                                    //�������ƶ���ڼ�����

	public Deck() {                                      //�ƿ�Ĺ��캯�����½�һ��˳���ƿ�
		deck = new LinkedList<Card>();
		for(COLOR c : COLOR.values()) {
			for(SHAPE s : SHAPE.values()) {
				for(FILLED f : FILLED.values()){
					Card card = new Card(c, s, f);        //����һ��card��ʵ��
					deck.add(card);                       //��card�ӵ��ƿ�deck������
				}
			}
		}
	}
	
	public void ShuffleDeck() {                            //ϴ�ƿ⣬ϴ��
		for(int i = 0; i < number_of_cards; i++) {
			int position = (int)Math.round((number_of_cards - 1) * Math.random());
			Card card = deck.pop();
			deck.add(position,card);
		}
	}
	
	public Card MoveTheTopCard() {      //�Ƴ��ƶ����һ����
		Card card = deck.pop();
		card.setNumber(count);         //��ÿһ�ų��ȥ���Ƽ����ƺ�
		count++;                        //������+1
		return card;
	}

	public void setHiddenCard() {                 //����������
		Card card = this.MoveTheTopCard();
		this.hiddenCard = card;
	}
	
	public Card getHiddeCard() {
		return hiddenCard;
	}
	
/*	public Card TirerCard() {          //���ƶ��������ȡһ����
		int position = (int)Math.round((number_of_cards - 1) * Math.random());
		return deck.remove(position);
	}*/
	
	public boolean IsEmpty() {     //�ƿ��Ƿ�����
		return deck.isEmpty();
	}

	public String toString() {        //����ƿ�
		return deck.toString();    
	}
}

package cardgame;

import java.util.LinkedList;
/**
 * Class Deck is the place of card where player draw the card,we use linkedlist for storing deck
 * 
 * 
 *@see Card
 */
public class Deck {
	/**
	 * It count how many card rest in the deck
	 * 
	 */
	public final static int number_of_cards=COLOR.values().length*SHAPE.values().length*FILLED.values().length;
	private LinkedList<Card> deck;                       //�ƿ�ı�����deck
	private Card hiddenCard;                             //ÿ�ֱ�����ʼ��������
	private int count = 1;                                    //�������ƶ���ڼ�����
	
	
	/**
	 * The constructor of the deck, create a new sequential deck
	 * 
	 */
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
	/**
	 * We shuffle the deck using for loop and random for shuffling the order of the cards
	 */
	public void ShuffleDeck() {                            //ϴ�ƿ⣬ϴ��
		for(int i = 0; i < number_of_cards; i++) {
			int position = (int)Math.round((number_of_cards - 1) * Math.random());
			Card card = deck.pop();
			deck.add(position,card);
		}
	}
	
	/**
	 * Remove the top card from the deck, we used linkedlist.pop() method for the action of remove and we mark down each card drawn
	 * 
	 * @return the top card of the deck we have removed
	 * 
	 * @see Card
	 */
	public Card MoveTheTopCard() {      //�Ƴ��ƶ����һ����
		Card card = deck.pop();
		card.setNumber(count);         //��ÿһ�ų��ȥ���Ƽ����ƺ�
		count++;                        //������+1
		return card;
	}
	
	/**We set the hidden card as the top card we removed from the deck for the first time 
	 * 
	 * @see Card
	 */
	public void setHiddenCard() {                 //����������
		Card card = this.MoveTheTopCard();
		this.hiddenCard = card;
	}
	
	/**
	 * 
	 * @return the hidden card
	 * 
	 * @see Card
	 */
	public Card getHiddeCard() {
		return hiddenCard;
	}
	
/*	public Card TirerCard() {          //���ƶ��������ȡһ����
		int position = (int)Math.round((number_of_cards - 1) * Math.random());
		return deck.remove(position);
	}*/
	
	/**
	 * 
	 * @return boolean for checking if the deck is empty
	 */
	public boolean IsEmpty() {     //�ƿ��Ƿ�����
		return deck.isEmpty();
	}
	
	/**
	 * We use method String toString() to export deck
	 */
	public String toString() {        //����ƿ�
		return deck.toString();    
	}
}

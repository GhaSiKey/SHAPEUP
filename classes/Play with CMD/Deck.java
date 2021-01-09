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
	private LinkedList<Card> deck;                       //牌库的变量是deck
	private Card hiddenCard;                             //每局比赛开始的隐藏牌
	private int count = 1;                                    //计数，牌堆里第几张牌
	
	
	/**
	 * The constructor of the deck, create a new sequential deck
	 * 
	 */
	public Deck() {                                      //牌库的构造函数，新建一个顺序牌库
		deck = new LinkedList<Card>();
		for(COLOR c : COLOR.values()) {
			for(SHAPE s : SHAPE.values()) {
				for(FILLED f : FILLED.values()){
					Card card = new Card(c, s, f);        //创建一张card的实例
					deck.add(card);                       //把card加到牌库deck队列中
				}
			}
		}
	}
	/**
	 * We shuffle the deck using for loop and random for shuffling the order of the cards
	 */
	public void ShuffleDeck() {                            //洗牌库，洗牌
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
	public Card MoveTheTopCard() {      //移除牌堆最顶上一张牌
		Card card = deck.pop();
		card.setNumber(count);         //给每一张抽出去的牌记上牌号
		count++;                        //计数器+1
		return card;
	}
	
	/**We set the hidden card as the top card we removed from the deck for the first time 
	 * 
	 * @see Card
	 */
	public void setHiddenCard() {                 //设置隐藏牌
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
	
/*	public Card TirerCard() {          //从牌堆中随机抽取一张牌
		int position = (int)Math.round((number_of_cards - 1) * Math.random());
		return deck.remove(position);
	}*/
	
	/**
	 * 
	 * @return boolean for checking if the deck is empty
	 */
	public boolean IsEmpty() {     //牌库是否抽空了
		return deck.isEmpty();
	}
	
	/**
	 * We use method String toString() to export deck
	 */
	public String toString() {        //输出牌库
		return deck.toString();    
	}
}

package cardgame;

import java.util.Random;

/**
 * The class PlayerVirtual inherit the class Play
 * It contains targetCard next to which card the player wants to put.
 * And we use the Strategy mode to realize the virtual player.
 * 
 *@see Play
 */
public class PlayerVirtual extends Play{
	
	private int targetCard;                        //玩家想要放在哪张牌的旁边()
	private int movedCard;                        
	private String direction;                     //玩家放在目标牌targetcard的哪个方向（up/down/left/right）
	private String[] directions = {"up","down","left","right"};
	
	private Strategy strategy;
	
	/**
	 * The constructor of PlayerVirtual
	 * @param name Name of the virtual player
	 * @param strategy the Strategy we will use
	 */
	public PlayerVirtual(String name, Strategy strategy) {
		super(name);
		this.strategy = strategy;
	}
	/**
	 * We have three ways to play the turn, so the computer also has three ways to play the turn.
	 * It can draw a card and put the card.
	 * It can move a card, draw a card and put the card.
	 * It can draw a card, put the card and move a card.
	 * @param chessboard The chessboard it is playing
	 * @param deck The deck it draws the card
	 * 
	 * @see Card
	 * @see Chessboard
	 * @see Deck
	 */
	public void RobotPlay(Chessboard chessboard,Deck deck) {
		
		int choose = 0;
		choose = strategy.choose(chessboard);
		
		switch (choose) {
		case 0: {
			//playcard
			System.out.println("Computer choose to playcard");
			DrawCard(deck.MoveTheTopCard());
			PlayCard(chessboard);
			break;
		}
		case 1:{
			//movecard  and playcard
			System.out.println("Computer choose to movecard and playcard");
			MoveCard(chessboard);
			DrawCard(deck.MoveTheTopCard());
			PlayCard(chessboard);
			break;
		}
		case 2:{
			//playcard and movecard
			System.out.println("Computer choose to playcard and movecard");
			DrawCard(deck.MoveTheTopCard());
			PlayCard(chessboard);
			MoveCard(chessboard);
			break;
		}
		}
	}
	
	
	/**
	 * It is the method how the computer put the card in the chess board.
	 * It put the card randomly but we also have to think about the size of chess board.
	 * So we will compare the Abscissa x with column and ordinate y with row to see if we can put the card.
	 * And we decide the chess board is 3*5 or 5*3 when we put the card.
	 * 
	 * @param chessboard The chessboard it is playing
	 * @see Chessboard
	 * @see Play
	 * @see Card
	 */
	public void PlayCard(Chessboard chessboard) {           
		if(chessboard.GetCardNumber()==3) {                  //第一张牌
			chessboard.setCard(6, 6, handCards.pop());	
			System.out.println("Because it is the first card, place it at will.");
			chessboard.setCardNumber(4);
		}
		else {
			while(true) {
				Random random = new Random();
				int n = chessboard.GetCardNumber();
				int m = 4;
				this.targetCard = random.nextInt(n-m+1) + m;
				if(targetCard>3 && targetCard<=chessboard.GetCardNumber()) {
					if (targetCard == movedCard) {
						continue;
					}
				}else {
					continue;
				}
			//}
			
			//while(true) {
				//Random random = new Random();
				int pick = random.nextInt(4);
				this.direction = directions[pick];
				int x = chessboard.getCardPosition_X(targetCard);    //当前目标牌targetcard在二维数组中的x(行数ligne,从0开始）
				int y = chessboard.getCardPosition_Y(targetCard);    //当前目标牌taegetvard在二维数组中的y（列数colone，从0开始）
				chessboard.CountSide();
				switch (direction) {
					case "up": {		
						if(chessboard.getCard(x-1, y)!=null) {
							continue;	
						}
						else {
							if ((chessboard.ligne_length == 5 && chessboard.colone_length <= 3)||(chessboard.ligne_length == 3 && chessboard.colone_length > 3)) {								
								if ((x-1) < chessboard.small_ligne) {
									continue;
								}
							}
						}
						break;
					}
					case "right": {
						if(chessboard.getCard(x, y+1)!=null) {
							continue;	
						}
						else {
							if ((chessboard.ligne_length > 3 && chessboard.colone_length == 3)||(chessboard.ligne_length <=3 && chessboard.colone_length == 5)) {
								if ((y+1) > chessboard.big_colone) {
									continue;
								}
							}
						}
						break;
					}
					case "down": {
						if(chessboard.getCard(x+1, y)!=null) {
							continue;	
						}
						else {
							if ((chessboard.ligne_length == 5 && chessboard.colone_length <= 3)||(chessboard.ligne_length == 3 && chessboard.colone_length > 3)) {
								if ((x+1) > chessboard.big_ligne) {
									continue;
								}
							}
						}
					    break;
					}
					case "left": {
						if(chessboard.getCard(x, y-1)!=null) {
							continue;	
						}
						else {
							if ((chessboard.ligne_length > 3 && chessboard.colone_length == 3)||(chessboard.ligne_length <=3 && chessboard.colone_length == 5)) {
								if ((y-1) < chessboard.small_colone) {
									continue;
								}
							}
						}
						break;
					}
					default:{
						continue;	
					}
				}
				break;
			}
			System.out.println("》He choose to put "+handCards.peek()+" at: "+targetCard+"'s "+direction);
			chessboard.InputCard(handCards.pop(),targetCard,direction);
		}
	}

	/**
	 * It's the method how the computer move the card.
	 * The computer move the card randomly to the random position and we set the original card position as null.
	 * 
	 * @param chessboard The chessboard it is playing
	 * 
	 * @see Chessboard
	 * @see Play
	 * @see Card
	 */
	public void MoveCard(Chessboard chessboard) {		
		while(true) {
			Random random = new Random();
			int n = chessboard.GetCardNumber();
			int m = 4;
			this.movedCard = random.nextInt(n-m+1) + m;    //随机获取要移的牌号
			
			if (movedCard>3 && movedCard<=chessboard.GetCardNumber()) {
				Card movingCard = chessboard.getCard(movedCard,true);      //获取targetCard处的牌，并把那个位置设为空
				handCards.add(movingCard);
				System.out.println("He moved this card"+movingCard);
				PlayCard(chessboard);	
				chessboard.setCardNumber(chessboard.GetCardNumber()-1);
				break;
			}
		}	
	}

}

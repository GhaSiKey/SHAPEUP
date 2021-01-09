package cardgame;

import java.util.Scanner;

/**
 * The class PlayerReal inherit the class Play
 * It contains targetCard next to which card the player wants to put.
 * 
 *@see Play
 */

public class PlayerReal extends Play {

	private int targetCard;                        //玩家想要放在哪张牌的旁边()
	private String direction;                     //玩家放在目标牌targetcard的哪个方向（up/down/left/right）
	
	/**
	 * The Constructor of PlayerReal, inheriting the constructor of Play
	 * @param name the name of player
	 * 
	 * @see Play
	 */
	public PlayerReal(String name) {
		super(name);
	}
	
	/**
	 * It is the method how the real player put the card in the chess board.
	 * The chess boar won't be decided until the number of rows or columns is larger than three.
	 * So we will compare the Abscissa x with column and ordinate y with row to see if we can put the card.
	 * And we decide the chess board is 3*5 or 5*3 when we put the card.
	 * 
	 * @param chessboard The chess board we are playing 
	 * 
	 * @see Chessboard
	 * @see Play
	 * @see Card
	 * 
	 *
	 */
	public void PlayCard(Chessboard chessboard) {           
		if(chessboard.GetCardNumber()==3) {                  //第一张牌
			chessboard.setCard(6, 6, handCards.pop());	
			System.out.println("Because it is the first card, place it at will.");
			chessboard.setCardNumber(4);
		}
		else {
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			while(true) {
				System.out.println("You pick a card:"+handCards.peek());
				System.out.print("Enter which card you want to place next to(enter number!!):");
				this.targetCard = input.nextInt();
				if(targetCard>3 && targetCard<=chessboard.GetCardNumber()) {
					break;
				}
				else {
					System.out.println("WrongInput,Plese enter the number of card.");
				}
			}
			while(true) {
				System.out.print("Which direction do you want to put this card:");
				this.direction = input.next();
				int x = chessboard.getCardPosition_X(targetCard);    //当前目标牌targetcard在二维数组中的x(行数ligne,从0开始）
				int y = chessboard.getCardPosition_Y(targetCard);    //当前目标牌taegetvard在二维数组中的y（列数colone，从0开始）
				chessboard.CountSide();
				switch (direction) {
					case "up": {		
						if(chessboard.getCard(x-1, y)!=null) {
							System.out.println("WrongInput,There are cards in this position");continue;	
						}
						else {
							if ((chessboard.ligne_length == 5 && chessboard.colone_length <= 3)||(chessboard.ligne_length == 3 && chessboard.colone_length > 3)) {								
								if ((x-1) < chessboard.small_ligne) {
									System.out.print("WrongInput,inside 3X5");
									continue;
								}
							}
						}
						break;
					}
					case "right": {
						if(chessboard.getCard(x, y+1)!=null) {
							System.out.print("WrongInput,There are cards in this position");continue;	
						}
						else {
							if ((chessboard.ligne_length > 3 && chessboard.colone_length == 3)||(chessboard.ligne_length <=3 && chessboard.colone_length == 5)) {
								if ((y+1) > chessboard.big_colone) {
									System.out.print("WrongInput,inside 3X5");
									continue;
								}
							}
						}
						break;
					}
					case "down": {
						if(chessboard.getCard(x+1, y)!=null) {
							System.out.print("WrongInput,There are cards in this position");continue;	
						}
						else {
							if ((chessboard.ligne_length == 5 && chessboard.colone_length <= 3)||(chessboard.ligne_length == 3 && chessboard.colone_length > 3)) {
								if ((x+1) > chessboard.big_ligne) {
									System.out.print("WrongInput,inside 3X5");
									continue;
								}
							}
						}
					    break;
					}
					case "left": {
						if(chessboard.getCard(x, y-1)!=null) {
							System.out.print("WrongInput,There are cards in this position");continue;	
						}
						else {
							if ((chessboard.ligne_length > 3 && chessboard.colone_length == 3)||(chessboard.ligne_length <=3 && chessboard.colone_length == 5)) {
								if ((y-1) < chessboard.small_colone) {
									System.out.print("WrongInput,inside 3X5");
									continue;
								}
							}
						}
						break;
					}
					default:{
						System.out.println("WrongInput,Please enter [up/down/left/right]");
						continue;	
					}
				}
				break;
			}
			chessboard.InputCard(handCards.pop(),targetCard,direction);
		}
	}
	
	/**
	 * It's the method how the real player move the card.
	 * The player move the card to the position he wants and we set the original card position as null.
	 * 
	 * @param chessboard The chess board we are playing 
	 * 
	 * @see Chessboard
	 * @see Play
	 * @see Card
	 */
	public void MoveCard(Chessboard chessboard) {
		int number;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);		
		while(true) {
			System.out.print("Which card do you want to move(enter number!!):");
			number = input.nextInt();
			if (number>3 && number<=chessboard.GetCardNumber()) {
				Card movingCard = chessboard.getCard(number,true);      //获取number处的牌，并把那个位置设为空
				handCards.add(movingCard);
				PlayCard(chessboard);	
				chessboard.setCardNumber(chessboard.GetCardNumber()-1);
				break;
			}
			else {
				System.out.println("WrongInput,Plese enter the number of card.");
			}
		}	
	}
	
	
}

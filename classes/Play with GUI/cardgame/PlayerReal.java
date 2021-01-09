package cardgame;

import java.util.Scanner;

public class PlayerReal extends Play {

	private int targetCard;                        //玩家想要放在哪张牌的旁边()
	private String direction;                     //玩家放在目标牌targetcard的哪个方向（up/down/left/right）
	
	public PlayerReal(String name) {
		super(name);
	}

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
	
	public void PlayCard(Chessboard chessboard, int x, int y) { 
		
	}
	
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

package cardgame;

import java.util.Random;

public class PlayerVirtual extends Play{
	
	private int targetCard;                        //�����Ҫ���������Ƶ��Ա�()
	private int movedCard;                        
	private String direction;                     //��ҷ���Ŀ����targetcard���ĸ�����up/down/left/right��
	private String[] directions = {"up","down","left","right"};
	
	private Strategy strategy;
	
	public PlayerVirtual(String name, Strategy strategy) {
		super(name);
		this.strategy = strategy;
	}
	
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
		 
	public void PlayCard(Chessboard chessboard) {
		if(chessboard.GetCardNumber()==3) {                  //��һ����
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
				int x = chessboard.getCardPosition_X(targetCard);    //��ǰĿ����targetcard�ڶ�ά�����е�x(����ligne,��0��ʼ��
				int y = chessboard.getCardPosition_Y(targetCard);    //��ǰĿ����taegetvard�ڶ�ά�����е�y������colone����0��ʼ��
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
			System.out.println("��He choose to put "+handCards.peek()+" at: "+targetCard+"'s "+direction);
			chessboard.InputCard(handCards.pop(),targetCard,direction);
		}
	}

	public void MoveCard(Chessboard chessboard) {		
		while(true) {
			Random random = new Random();
			int n = chessboard.GetCardNumber();
			int m = 4;
			this.movedCard = random.nextInt(n-m+1) + m;    //�����ȡҪ�Ƶ��ƺ�
			
			if (movedCard>3 && movedCard<=chessboard.GetCardNumber()) {
				Card movingCard = chessboard.getCard(movedCard,true);      //��ȡtargetCard�����ƣ������Ǹ�λ����Ϊ��
				handCards.add(movingCard);
				System.out.println("He moved this card"+movingCard);
				PlayCard(chessboard);	
				chessboard.setCardNumber(chessboard.GetCardNumber()-1);
				break;
			}
		}	
	}

}

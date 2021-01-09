package cardgame;

import java.util.Scanner;
/**
 * 
 * The class Game shows how to realize the different mode of the game
 * there are the mode of PvP, the mode of PvPvP, the mode of PvE, the mode of EvE and the mode of PvEvE
 *
 */
public class Game {
	
	static Scanner input = new Scanner(System.in);
	//菜单界面，选择进入哪个模式
	/**
	 * The menu of the game, it is decided by the mode you choose.
	 */
	public static void menu() {
		System.out.println("\n\n**************SHAPE UP**************");
		System.out.println("1.Player vs Player");
		System.out.println("2.Player vs Player vs Player");
		System.out.println("3.Player vs Computer");
		System.out.println("4.Computer vc Computer");
		System.out.println("5.Player vs Computer vs Computer");
		System.out.println("6.Exit");
		System.out.println("Still increasing...");
		System.out.print("Choose one mode[1/2/3/4/5/6]:");
			int choose = input.nextInt();
			switch (choose) {
			case 1: {
				PVP();
				break;
			}
			case 2: {
				PVPVP();
				break;
			}
			case 3: {
				PVE();
				break;
			}
			case 4: {
				EVE();
				break;
			}
			case 5:{
				PVEVE();
				break;
			}
			case 6:{
				System.exit(0);
			}
			default:
				System.err.println("Wrong choose..(Please choose from [1/2/3/4/5/6])");
			}
	}
	////////////////////////////////////////////////////////////////////
	//每个模式，每个方法
	
	/**
	 * The method is used to realize the mode of PvP
	 * First create the chessboard, shuffle the deck, pick the hidden card and two player draw their victory card.
	 * Then the game begins,
	 * Both of the players in their every turn can choose if move the card before drawing a card,
	 * and then draw a card and put it in the chessboard,
	 * if the player do not choose moving the card before drawing a card and he will be asked again if he needs to move a card,
	 * and the turn is over
	 * The game ends when the deck has no card and the player has played the final card
	 * Finally,We calculate the scores of the two players
	 * 
	 * @see Card
	 * @see Chessboard
	 * @see Deck
	 */
	private static void PVP() {
		
		System.out.print("Enter player1 name:");
		String name = input.next();
		PlayerReal p1 = new PlayerReal(name);
		System.out.printf("Creating p1 %s........\n",name);
		System.out.print("Enter player2 name:");
		name = input.next();
		PlayerReal p2 = new PlayerReal(name);
		System.out.printf("Creating p2 %s........\n",name);
		
		Chessboard chessboard = new Chessboard();
		System.out.println("Creating New Chessboard........");
		Deck deck = new Deck();
		System.out.println("Creating New Deck........");
		deck.ShuffleDeck();
		System.out.println("Shffling The Card.......");
		deck.setHiddenCard();
		System.out.println("HiddenCard has picked.....");
		
		p1.DrawCard(deck.MoveTheTopCard());
		System.out.println("p1's Vectory Card:"+p1.VectoryCard().toString());
		p2.DrawCard(deck.MoveTheTopCard());
		System.out.println("p2's Vectory Card:"+p2.VectoryCard().toString());
	
		int turn = 1;
		while (!deck.IsEmpty()) {
			if(turn == 1) {
				int moved = 0;
				System.out.println("Player1's turn:");
				if (chessboard.GetCardNumber()>4) {
					System.out.print("Do you want to move a card?[yes/no]");
					String anwser = input.next();
					if(anwser.equals("yes")) {
						p1.MoveCard(chessboard);
						chessboard.ShowChessBoard();	
						moved = 1;
					}
				}
				
				p1.DrawCard(deck.MoveTheTopCard());
				p1.PlayCard(chessboard);
				chessboard.ShowChessBoard();
				
				if (moved == 0 && chessboard.GetCardNumber()>4) {
					System.out.print("Do you want to move a card?[yes/no]");
					String anwser = input.next();
					if(anwser.equals("yes")) {
						p1.MoveCard(chessboard);
						chessboard.ShowChessBoard();	
						moved = 1;
					}
				}
				turn = 2;
			}
			else {
				int moved = 0;
				System.out.println("Player2's turn:");
				if (chessboard.GetCardNumber()>4) {
					System.out.print("Do you want to move a card?[yes/no]");
					String anwser = input.next();
					if(anwser.equals("yes")) {
						p2.MoveCard(chessboard);
						chessboard.ShowChessBoard();	
						moved = 1;
					}
				}
				p2.DrawCard(deck.MoveTheTopCard());
				p2.PlayCard(chessboard);
				chessboard.ShowChessBoard();
				
				if (moved == 0 && chessboard.GetCardNumber()>4) {
					System.out.print("Do you want to move a card?[yes/no]");
					String anwser = input.next();
					if(anwser.equals("yes")) {
						p2.MoveCard(chessboard);
						chessboard.ShowChessBoard();	
						moved = 1;
					}
				}
				turn = 1;
			}
		}
		
		System.out.print("GAMEOVER, Counting the score............");
		for (int i = 0; i < 20; i++) {
			System.out.print("》");
			try {
				Thread.currentThread();
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int p1score=chessboard.Scoring_Shape(p1.VectoryCard())+chessboard.Scoring_Filled(p1.VectoryCard())+chessboard.Scoring_Color(p1.VectoryCard());
		System.out.println("p1 score:"+p1score);
		int p2score=chessboard.Scoring_Shape(p2.VectoryCard())+chessboard.Scoring_Filled(p2.VectoryCard())+chessboard.Scoring_Color(p2.VectoryCard());
		System.out.println("p2 score:"+p2score);
	}

	/**
	 * The method is used to realize the mode of PvPvP
	 * First create the chessboard, shuffle the deck and three players draw their victory card
	 * Then the game begins,
	 * All of the players in their every turn can choose if move the card before drawing a card,
	 * and then draw a card and put it in the chessboard,
	 * if the player do not choose moving the card before drawing a card and he will be asked again if he needs to move a card,
	 * and the turn is over.
	 * The game ends when the chessboard remains one place without putting a card.
	 * Finally,We calculate the scores of the three players.
	 * @see Card
	 * @see Chessboard
	 * @see Deck 
	 */
	private static void PVPVP() {
		
		System.out.print("Enter player1 name:");
		String name = input.next();
		PlayerReal p1 = new PlayerReal(name);
		System.out.printf("Creating p1 %s........\n",name);
		System.out.print("Enter player2 name:");
		name = input.next();
		PlayerReal p2 = new PlayerReal(name);
		System.out.printf("Creating p2 %s........\n",name);
		System.out.print("Enter player3 name:");
		name = input.next();
		PlayerReal p3 = new PlayerReal(name);
		System.out.printf("Creating p3 %s........\n",name);
		
		Chessboard chessboard = new Chessboard();
		System.out.println("Creating New Chessboard........");
		Deck deck = new Deck();
		System.out.println("Creating New Deck........");
		deck.ShuffleDeck();
		System.out.println("Shffling The Card.......");
		
		p1.DrawCard(deck.MoveTheTopCard());
		System.out.println("p1's Vectory Card:"+p1.VectoryCard().toString());
		p2.DrawCard(deck.MoveTheTopCard());
		System.out.println("p2's Vectory Card:"+p2.VectoryCard().toString());
		p3.DrawCard(deck.MoveTheTopCard());
		System.out.println("p3's Vectory Card:"+p2.VectoryCard().toString());
	
		int turn = 1;
		while (!deck.IsEmpty()) {
			if(turn == 1) {
				int moved = 0;
				System.out.println("Player1's turn:");
				if (chessboard.GetCardNumber()>4) {
					System.out.print("Do you want to move a card?[yes/no]");
					String anwser = input.next();
					if(anwser.equals("yes")) {
						p1.MoveCard(chessboard);
						chessboard.ShowChessBoard();	
						moved = 1;
					}
				}
				
				p1.DrawCard(deck.MoveTheTopCard());
				p1.PlayCard(chessboard);
				chessboard.ShowChessBoard();
				
				if (moved == 0 && chessboard.GetCardNumber()>4) {
					System.out.print("Do you want to move a card?[yes/no]");
					String anwser = input.next();
					if(anwser.equals("yes")) {
						p1.MoveCard(chessboard);
						chessboard.ShowChessBoard();	
						moved = 1;
					}
				}
				turn = 2;
			}
			else if(turn == 2){
				int moved = 0;
				System.out.println("Player2's turn:");
				if (chessboard.GetCardNumber()>4) {
					System.out.print("Do you want to move a card?[yes/no]");
					String anwser = input.next();
					if(anwser.equals("yes")) {
						p2.MoveCard(chessboard);
						chessboard.ShowChessBoard();	
						moved = 1;
					}
				}
				p2.DrawCard(deck.MoveTheTopCard());
				p2.PlayCard(chessboard);
				chessboard.ShowChessBoard();
				
				if (moved == 0 && chessboard.GetCardNumber()>4) {
					System.out.print("Do you want to move a card?[yes/no]");
					String anwser = input.next();
					if(anwser.equals("yes")) {
						p2.MoveCard(chessboard);
						chessboard.ShowChessBoard();	
						moved = 1;
					}
				}
				turn = 3;
			}
			else {
				int moved = 0;
				System.out.println("Player3's turn:");
				if (chessboard.GetCardNumber()>4) {
					System.out.print("Do you want to move a card?[yes/no]");
					String anwser = input.next();
					if(anwser.equals("yes")) {
						p2.MoveCard(chessboard);
						chessboard.ShowChessBoard();	
						moved = 1;
					}
				}
				p2.DrawCard(deck.MoveTheTopCard());
				p2.PlayCard(chessboard);
				chessboard.ShowChessBoard();
				
				if (moved == 0 && chessboard.GetCardNumber()>4) {
					System.out.print("Do you want to move a card?[yes/no]");
					String anwser = input.next();
					if(anwser.equals("yes")) {
						p2.MoveCard(chessboard);
						chessboard.ShowChessBoard();	
						moved = 1;
					}
				}
				turn = 1;
			}
		}
		
		System.out.print("GAMEOVER, Counting the score............");
		for (int i = 0; i < 20; i++) {
			System.out.print("》");
			try {
				Thread.currentThread();
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int p1score=chessboard.Scoring_Shape(p1.VectoryCard())+chessboard.Scoring_Filled(p1.VectoryCard())+chessboard.Scoring_Color(p1.VectoryCard());
		System.out.println("p1 score:"+p1score);
		int p2score=chessboard.Scoring_Shape(p2.VectoryCard())+chessboard.Scoring_Filled(p2.VectoryCard())+chessboard.Scoring_Color(p2.VectoryCard());
		System.out.println("p2 score:"+p2score);
		int p3score=chessboard.Scoring_Shape(p3.VectoryCard())+chessboard.Scoring_Filled(p3.VectoryCard())+chessboard.Scoring_Color(p3.VectoryCard());
		System.out.println("p3 score:"+p3score);
	}
	
	/**
	 * The method is used to realize the mode of PvE
	 * We can choose the difficulty of playing against the computer
	 * First create the chessboard, shuffle the deck and the player and the computer draw their victory card
	 * Then the game begins,
	 * The player in every turn can choose if move the card before drawing a card,
	 * and then draw a card and put it in the chessboard,
	 * if the player do not choose moving the card before drawing a card and he will be asked again if he needs to move a card,
	 * and the turn is over.
	 * So is the computer's turn.
	 * The game ends when the deck has no card and the player has played the final card.
	 * Finally,We calculate the scores of the player and the computer.
	 * @see Card
	 * @see Chessboard
	 * @see Deck 
	 */
	private static void PVE() {
		
		System.out.print("Enter player's name:");
		String name = input.next();
		PlayerReal p1 = new PlayerReal(name);
		//while循环 给电脑选择难度，然后生成相应的策略模式，再初始化电脑玩家
		Strategy strategy;
		while(true) {
			System.out.print("Choose computer's level[1=easy,2=hard](enter number):");
			int level = input.nextInt();
			switch (level) {
			case 1: {
				strategy = new EasyComputer();
				break;
			}
			case 2: {
				strategy = new HardComputer();
				break;
			}
			default:
				System.out.println("Wrong input");
				continue;
			}
			break;
		}
		//这里把相应的策略赋值给电脑初始化
		PlayerVirtual robot = new PlayerVirtual("Pierre",strategy);
		Chessboard chessboard = new Chessboard();
		Deck deck = new Deck();
		deck.ShuffleDeck();
		deck.setHiddenCard();
		System.out.println("HiddenCard: "+deck.getHiddeCard().toString());
		p1.DrawCard(deck.MoveTheTopCard());
		System.out.println("p1's Vectory Card:"+p1.VectoryCard().toString());
		robot.DrawCard(deck.MoveTheTopCard());
		System.out.println("p2's Vectory Card:"+robot.VectoryCard().toString());
		
		int turn = 1;
		while (!deck.IsEmpty()) {
			if(turn == 1) {
				int moved = 0;
				System.out.println("Your turn:");
				if (chessboard.GetCardNumber()>4) {
					System.out.print("Do you want to move a card?[yes/no]");
					String anwser = input.next();
					if(anwser.equals("yes")) {
						p1.MoveCard(chessboard);
						chessboard.ShowChessBoard();	
						moved = 1;
					}
				}
				
				p1.DrawCard(deck.MoveTheTopCard());
				p1.PlayCard(chessboard);
				chessboard.ShowChessBoard();
				
				if (moved == 0 && chessboard.GetCardNumber()>4) {
					System.out.print("Do you want to move a card?[yes/no]");
					String anwser = input.next();
					if(anwser.equals("yes")) {
						p1.MoveCard(chessboard);
						chessboard.ShowChessBoard();	
						moved = 1;
					}
				}
				turn = 2;
			}
			else {
				robot.RobotPlay(chessboard, deck);
				chessboard.ShowChessBoard();
				turn = 1;
			}
		}

			int score=chessboard.Scoring_Shape(p1.VectoryCard())+chessboard.Scoring_Filled(p1.VectoryCard())+chessboard.Scoring_Color(p1.VectoryCard());
			System.out.println("\nPlayer'sVectory Card:"+p1.VectoryCard().toString());
			System.out.println("Scoring_Shape:"+chessboard.Scoring_Shape(p1.VectoryCard()));
			System.out.println("Scoring_Filled:"+chessboard.Scoring_Filled(p1.VectoryCard()));
			System.out.println("Scoring_Color:"+chessboard.Scoring_Color(p1.VectoryCard()));
			System.out.println("Player's Total score:"+score);
			score=chessboard.Scoring_Shape(robot.VectoryCard())+chessboard.Scoring_Filled(robot.VectoryCard())+chessboard.Scoring_Color(robot.VectoryCard());
			System.out.println("\nRobot'sVectory Card:"+robot.VectoryCard().toString());
			System.out.println("Robote's Total score:"+score);
	}

	/**
	 * The method is used to realize the mode of EvE
	 * We can choose the difficulty of the computers.
	 * First create the chessboard, shuffle the deck and the two computers draw their victory card
	 * Then the game begins,
	 * The computers in every turn can choose if move the card before drawing a card,
	 * and then draw a card and put it in the chessboard,
	 * if the computer do not choose moving the card before drawing a card and it will be asked again if he needs to move a card,
	 * and the turn is over.
	 * The game ends when the deck has no card and the computer has played the final card.
	 * Finally,We calculate the scores of the computers.
	 * @see Card
	 * @see Chessboard
	 * @see Deck 
	 */
	private static void EVE() {
		
		Strategy strategy;
		while(true) {
			
			System.out.print("Choose computer's level[1=easy,2=hard](enter number):");
			int level = input.nextInt();
			
			switch (level) {
			case 1: {
				strategy = new EasyComputer();
				break;
			}
			case 2: {
				strategy = new HardComputer();
				break;
			}
			default:
				System.out.println("Wrong input");
				continue;
			}
			break;
		}
		
		PlayerVirtual robot_1 = new PlayerVirtual("Robot_1",strategy);
		System.out.println("Creating Robot_1........");
		PlayerVirtual robot_2 = new PlayerVirtual("Robot_2",strategy);
		System.out.println("Creating Robot_2........");
		Chessboard chessboard = new Chessboard();
		System.out.println("Creating New Chessboard........");
		Deck deck = new Deck();
		System.out.println("Creating New Deck........");
		deck.ShuffleDeck();
		System.out.println("Shffling The CArd.......");
		deck.setHiddenCard();
		System.out.println("HiddenCard: "+deck.getHiddeCard().toString());
		
		robot_1.DrawCard(deck.MoveTheTopCard());
		System.out.println("Robot_1's Vectory Card:"+robot_1.VectoryCard().toString());
		robot_2.DrawCard(deck.MoveTheTopCard());
		System.out.println("Robot_2's Vectory Card:"+robot_2.VectoryCard().toString());
		
		int turn = 1;
		while (!deck.IsEmpty()) {
			if(turn == 1) {
				robot_1.RobotPlay(chessboard, deck);
				chessboard.ShowChessBoard();
				turn = 0;
			}
			else {
				robot_2.RobotPlay(chessboard, deck);
				chessboard.ShowChessBoard();
				turn = 1;
			}
			try {
				Thread.currentThread();
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.print("Counting the score");
		for (int i = 0; i < 10; i++) {
			System.out.print("》");
			try {
				Thread.currentThread();
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		int score=chessboard.Scoring_Shape(robot_1.VectoryCard())+chessboard.Scoring_Filled(robot_1.VectoryCard())+chessboard.Scoring_Color(robot_1.VectoryCard());
		System.out.println("\nRobot_1'sVectory Card:"+robot_1.VectoryCard().toString());
		System.out.println("Scoring_Shape:"+chessboard.Scoring_Shape(robot_1.VectoryCard()));
		System.out.println("Scoring_Filled:"+chessboard.Scoring_Filled(robot_1.VectoryCard()));
		System.out.println("Scoring_Color:"+chessboard.Scoring_Color(robot_1.VectoryCard()));
		System.out.println("Robote_1's Total score:"+score);
		score=chessboard.Scoring_Shape(robot_2.VectoryCard())+chessboard.Scoring_Filled(robot_2.VectoryCard())+chessboard.Scoring_Color(robot_2.VectoryCard());
		System.out.println("\nRobot_2'sVectory Card:"+robot_2.VectoryCard().toString());
		System.out.println("Robote_2's Total score:"+score);
	}

	/**
	 * The method is used to realize the mode of PvEvE
	 * We can choose the difficulty of playing against the computer
	 * First create the chessboard, shuffle the deck and the player and the two computers draw their victory card
	 * Then the game begins,
	 * The player in every turn can choose if move the card before drawing a card,
	 * and then draw a card and put it in the chessboard,
	 * if the player do not choose moving the card before drawing a card and he will be asked again if he needs to move a card,
	 * and the turn is over.
	 * So are the computers' turn.
	 * The game ends when the chessboard remains one place without putting a card.
	 * Finally,We calculate the scores of the computers.
	 * @see Card
	 * @see Chessboard
	 * @see Deck 
	 */
	private static void PVEVE() {
		
		System.out.print("Enter your name:");
		String name = input.next();
		PlayerReal p1 = new PlayerReal(name);
		System.out.printf("Creating p1 %s........\n",name);

		Strategy strategy;
		while(true) {
			System.out.print("Choose computer's level[1=easy,2=hard](enter number):");
			int level = input.nextInt();
			switch (level) {
			case 1: {
				strategy = new EasyComputer();
				break;
			}
			case 2: {
				strategy = new HardComputer();
				break;
			}
			default:
				System.out.println("Wrong input");
				continue;
			}
			break;
		}
		
		PlayerVirtual robot_1 = new PlayerVirtual("Robot_1",strategy);
		System.out.println("Creating Robot_1........");
		PlayerVirtual robot_2 = new PlayerVirtual("Robot_2",strategy);
		System.out.println("Creating Robot_2........");
		
		Chessboard chessboard = new Chessboard();
		System.out.println("Creating New Chessboard........");
		Deck deck = new Deck();
		System.out.println("Creating New Deck........");
		deck.ShuffleDeck();
		System.out.println("Shffling The Card.......");
		
		p1.DrawCard(deck.MoveTheTopCard());
		System.out.println("p1's Vectory Card:"+p1.VectoryCard().toString());
		robot_1.DrawCard(deck.MoveTheTopCard());
		System.out.println("p2's Vectory Card:"+robot_1.VectoryCard().toString());
		robot_2.DrawCard(deck.MoveTheTopCard());
		System.out.println("p3's Vectory Card:"+robot_2.VectoryCard().toString());
	
		int turn = 1;
		while (!deck.IsEmpty()) {
			if(turn == 1) {
				int moved = 0;
				System.out.println("Player1's turn:");
				if (chessboard.GetCardNumber()>4) {
					System.out.print("Do you want to move a card?[yes/no]");
					String anwser = input.next();
					if(anwser.equals("yes")) {
						p1.MoveCard(chessboard);
						chessboard.ShowChessBoard();	
						moved = 1;
					}
				}
				
				p1.DrawCard(deck.MoveTheTopCard());
				p1.PlayCard(chessboard);
				chessboard.ShowChessBoard();
				
				if (moved == 0 && chessboard.GetCardNumber()>4) {
					System.out.print("Do you want to move a card?[yes/no]");
					String anwser = input.next();
					if(anwser.equals("yes")) {
						p1.MoveCard(chessboard);
						chessboard.ShowChessBoard();	
						moved = 1;
					}
				}
				turn = 2;
			}
			else if(turn == 2){
				robot_1.RobotPlay(chessboard, deck);
				chessboard.ShowChessBoard();
				try {
					Thread.currentThread();
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				turn = 3;
			}
			else {
				robot_2.RobotPlay(chessboard, deck);
				chessboard.ShowChessBoard();
				try {
					Thread.currentThread();
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				turn = 1;
			}
		}
		
		System.out.print("GAMEOVER, Counting the score............");
		for (int i = 0; i < 20; i++) {
			System.out.print("》");
			try {
				Thread.currentThread();
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		int p1score=chessboard.Scoring_Shape(p1.VectoryCard())+chessboard.Scoring_Filled(p1.VectoryCard())+chessboard.Scoring_Color(p1.VectoryCard());
		System.out.println("p1 score:"+p1score);
		int p2score=chessboard.Scoring_Shape(robot_1.VectoryCard())+chessboard.Scoring_Filled(robot_1.VectoryCard())+chessboard.Scoring_Color(robot_1.VectoryCard());
		System.out.println("p2 score:"+p2score);
		int p3score=chessboard.Scoring_Shape(robot_2.VectoryCard())+chessboard.Scoring_Filled(robot_2.VectoryCard())+chessboard.Scoring_Color(robot_2.VectoryCard());
		System.out.println("p3 score:"+p3score);
	}
	
	
	/**
	 * Game starts.
	 * @param arg Pass an array of strings to the main method
	 */
	public static void main(String[] arg) {
		
		while(true) {
			menu();
		}
		
	}
	
}


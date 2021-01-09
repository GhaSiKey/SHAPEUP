package cardgame;



import java.util.ArrayList;
import java.util.Collections;
/**
 * la class chessboard contains the methods of the way how we to store the chessboard
 * 
 * @see Card
 */


public class Chessboard {
	
	private static Card[][] chessboard;
	private int cardnumber;                        //�����������м�����
	private int x;                                //��ǰĿ����targetcard�ڶ�ά�����е�x(����ligne,��0��ʼ��
	private int y;                               //��ǰĿ����taegetvard�ڶ�ά�����е�y������colone����0��ʼ��
	
	/**
	 * length of the colone of the chessboard
	 */
	protected int colone_length;                    //��ǰ�����еĳ���
	
	/**
	 * length of the line of the chessboard
	 */
	protected int ligne_length;                    //��ǰ�����ж�����
	
	/**
	 * The leftmost column of the board
	 */
	protected int small_colone;                    //���������һ��
	
	/**
	 * The rightmost column of the board
	 */
	protected int big_colone;                      //�������ҵ�һ��
	
	/**
	 * The top row of the board
	 */
	protected int small_ligne;                     //�������ϵ�һ��
	
	/**
	 * The bottom row of the board
	 */
	protected int big_ligne;                       //�������µ�һ��
	
	
	/**
	 * the method of putting card on the board
	 * @param x Abscissa of the position
	 * @param y Ordinate of the position
	 * @param card the card we want to put
	 * @see Card
	 */
	public void setCard(int x,int y,Card card) {
		chessboard[x][y]=card;
	}
	
	/**
	 * Default constructor ,we initialize the 11X11 chessboard and there is a hidden card of victory and two cards of the player, so we count card from 3
	 */
	public Chessboard() {
		chessboard = new Card[13][13];      //��ʼ��11X11������
		cardnumber = 3;                     //���������ƣ���λ��ҵ�ʤ���ƣ������ϵ�������3��ʼ����
	}
	
	
	/**
	 * the method of print out the chessboard
	 */
	public void ShowChessBoard() {           //��ӡ����
		CountSide();
		System.out.println("====================================================================================================================================================================\n");
		for(int i=small_ligne;i<=big_ligne;i++) {
			for(int j=small_colone;j<=big_colone;j++) {
				if(chessboard[i][j]!=null) {
					System.out.printf("%30s|",chessboard[i][j].toString());
				}
				else {
					String espace = "";
					System.out.printf("%30s|",espace);
				}
			}
			System.out.println("");
		}
		System.out.println("\n====================================================================================================================================================================\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	
	/**
	 * the method of setting the number of card
	 * @param i The number of the card you want to set as.
	 */
	public void setCardNumber(int i) {
		this.cardnumber = i;
	}
	
	/**
	 * We get the card in the array according to the coordinates x, y
	 * @param x Abscissa of the card of you want to get
	 * @param y Ordinate of the card of you want to get
	 * @return The card you want to get
	 */
	public Card getCard(int x, int y) {          //��������x,y��ȡ�����е���Card
		return chessboard[x][y];
	}
	
	/**
	 * Get the card in the array according to the card number (d=true means to delete the card here)
	 * @param n The number of the card you want to get
	 * @param d d=true means to delete the card here
	 * @return the card you want to get
	 * 
	 * @see Card
	 */
	public Card getCard(int n, Boolean d) {                   //���ݿ���number��ȡ�����е���Card(d=true����Ҫɾ���˴�����)
		Card neededCard = null;
		for(int i=0;i<chessboard.length;i++) {
			for(int j=0;j<chessboard.length;j++) {
				if(chessboard[i][j]!=null) {
					if(chessboard[i][j].getNumber()==n) {
						neededCard = chessboard[i][j];
						if(d) {
							chessboard[i][j] = null;
						}
					}
				}
			}	
		}
		return neededCard;
	}
	
	/**
	 * 
	 * @param n The number of the card
	 * @return the x position of the found card on the chessboard
	 */
	public int getCardPosition_X(int n) {       //�����������������е�xλ��
		int x=0;
		for(int i=0;i<chessboard.length;i++) {
			for(int j=0;j<chessboard.length;j++) {
				if(chessboard[i][j]!=null) {
					if(chessboard[i][j].getNumber()==n) {
						x = i;
					}
				}
			}	
		}
		return x;
	}
	
	
	/**
	 * 
	 * @param n the number of the card
	 * @return the y position of the found card on the chessboard
	 */
	public int getCardPosition_Y(int n) {        //�����������������е�yλ��
		int y=0;
		for(int i=0;i<chessboard.length;i++) {
			for(int j=0;j<chessboard.length;j++) {
				if(chessboard[i][j]!=null) {
					if(chessboard[i][j].getNumber()==n) {
						y = j;
					}
				}
			}	
		}
		return y;
	}
	
	/**
	 * 
	 * @return how many cards are currently on the board
	 */
	public int GetCardNumber() {       //�����������������ж�����
		return cardnumber;
	}
	
	/**Update the number of rows, columns, and boundaries of the board
	 * We use arraylist to store the rows and columns of the chessboard, 
	 * then traverse the entire chessboard to obtain the row and column numbers, 
	 * store them in the arraylist, 
	 * and then arrange two arraylists to obtain the boundary
	 * 
	 */
	public void CountSide() {              //�������̵��������������Լ��߽�
		ArrayList<Integer> colone = new ArrayList<Integer>();    //������������������Щ������
		ArrayList<Integer> ligne = new ArrayList<Integer>();     //������Щ������
		
		for(int i=0;i<chessboard.length;i++) {            //�������̰��кţ��кŴ浽arraylist��
			for(int j=0;j<chessboard.length;j++) {
				if(chessboard[i][j]!=null) {
					colone.add(j);
					ligne.add(i);
				}
			}
		}
		
		Collections.sort(colone);                    
		Collections.sort(ligne);                    //��������arraylist��Ϊ�˷����ȡ�߽磨��С����/����
		
		int colone_size = colone.size();
		int ligne_size = ligne.size();               //��Ϊ�Ǵ�0��ʼ���������ֵҪȡ��size-1��              
		
		this.colone_length = colone.get(colone_size-1) - colone.get(0) + 1;
		this.ligne_length = ligne.get(ligne_size-1) - ligne.get(0) + 1;
		this.big_colone = colone.get(colone_size-1);
		this.small_colone = colone.get(0);
		this.big_ligne = ligne.get(ligne_size-1);
		this.small_ligne = ligne.get(0);
	}
	
	
	/**
	 * This method is used to put in card in the position I want to put in the chessboard	
	 * @param card the card we want to put
	 * @param targetCard the card next to which card we want to put
	 * @param direction up, down, right, left of the card
	 * @see Card
	 */
	public void InputCard(Card card, int targetCard,String direction) {
		this.x = getCardPosition_X(targetCard);
		this.y = getCardPosition_Y(targetCard);
		CountSide();
		switch (direction) {
			case "up": {		
				chessboard[x-1][y] = card;
				break;
			}
			case "right": {
				chessboard[x][y+1] = card;
				break;
			}
			case "down": {
				chessboard[x+1][y] = card;
			    break;
			}
			case "left": {
				chessboard[x][y-1] = card;
				break;
			}
		}
		cardnumber++;
	}
	
	/**
	 * We calculate the score of shape, score of shape= 4a+3b+2c+d,
	 * a=Five consecutive same shapes,
	 * b=Four consecutive same shapes,
	 * c=Three consecutive same shapes,
	 * d=Two consecutive same shapes.
	 * 
	 * @param card The victory card
	 * @return the score of the shape
	 */
	public int Scoring_Shape(Card card) {    
		int score = 0;
		int count=0;
		int a=0,b=0,c=0,d=0;    //������״���ԣ�score=4a+3b+2c+d
		CountSide();
/////////////////////////////////////////////////////////////////////////////////////////
		//������״
//////////�б���
		for(int i=small_ligne;i<=big_ligne;i++) {  
			count = 0;
			for(int j=small_colone;j<=big_colone;j++) {
				if (chessboard[i][j].getShape()==card.getShape()) {    //�Ƚ�ÿ��λ�õ��Ƶ���״�������Ƶ���״
					for (int n=j+1;n<=big_colone;n++) { 
						if (card.getShape()==chessboard[i][n].getShape()) {
							count++;
						}else {
							break;
						}
					}
				}
				j=j+count;
			}
			//System.out.println(count);
			switch (count) {
			case 1: {     //����������״��ͬ
				d++;
				break;
			}
			case 2: {     //����������״��ͬ
				c++;
				break;
			}
			case 3: {     //�����ĸ���״��ͬ
				b++;
				break;
			}
			case 4: {     //���������״��ͬ
				a++;
				break;
			}
			default:
				break;
		}
		}   
		//System.out.println(a+" "+b+" "+c+" "+d);
		
////////�б���
		for(int j=small_colone;j<=big_colone;j++) {  
			count = 0;
			for(int i=small_ligne;i<=big_ligne;i++) {
				if (chessboard[i][j].getShape()==card.getShape()) {    //�Ƚ�ÿ��λ�õ��Ƶ���״�������Ƶ���״
					for (int n=i+1;n<=big_ligne;n++) {        		
						if (card.getShape()==chessboard[n][j].getShape()) {
							count++;
						}else {
							break;
						}
					}
				}
				i=i+count;
			}
			switch (count) {
			case 1: {     //����������״��ͬ
				d++;
				break;
			}
			case 2: {     //����������״��ͬ
				c++;
				break;
			}
			case 3: {     //�����ĸ���״��ͬ
				b++;
				break;
			}
			case 4: {     //���������״��ͬ
				a++;
				break;
			}
			default:
				break;
		}
		}
		//System.out.println(a+" "+b+" "+c+" "+d);
		
		score=4*a+3*b+2*c+d;
		return score;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	/** We calculate the score of filled, score of filled= 5a+4b+3c,
	 * a=Five consecutive fill colors are the same,
	 * b=Four consecutive fill colors are the same,
	 * c=Three consecutive fill colors are the same
	 * 
	 * 
	 * @param card The victory card
	 * @return the score of the filled
	 */
	public int Scoring_Filled(Card card) {    
		int score = 0;
		int count=0;
		int a=0,b=0,c=0;    //������״���ԣ�score=5a+4b+3c
		CountSide();
////////////////////////////////////////////////////////////////
		//�������
//////////�б���
		for(int i=small_ligne;i<=big_ligne;i++) {  
			count = 0;
			for(int j=small_colone;j<=big_colone;j++) {
				if (chessboard[i][j].getFilled()==card.getFilled()) {    //�Ƚ�ÿ��λ�õ��Ƶ���״�������Ƶ���״
					for (int n=j+1;n<=big_colone;n++) { 
						if (chessboard[i][n].getFilled()==card.getFilled()) {
							count++;
						}else {
							break;
						}
					}
					j=j+count;
				}
			}
			//System.out.println(count);
			switch (count) {
			case 2: {     //
				c++;
				break;
			}
			case 3: {     //
				b++;
				break;
			}
			case 4: {     //
				a++;
				break;
			}
		}
		}   
		//System.out.println(a+" "+b+" "+c);
		
////////�б���
		for(int j=small_colone;j<=big_colone;j++) {  
			count = 0;
			for(int i=small_ligne;i<=big_ligne;i++) {
				if (chessboard[i][j].getFilled()==card.getFilled()) {    //�Ƚ�ÿ��λ�õ��Ƶ���״�������Ƶ���״
					for (int n=i+1;n<=big_ligne;n++) {        		
						if (card.getFilled()==chessboard[n][j].getFilled()) {
							count++;
						}else {
							break;
						}
					}
				}
				i=i+count;
			}
			//System.out.println(count);
			switch (count) {
			case 2: {     //����������״��ͬ
				c++;
				break;
			}
			case 3: {     //�����ĸ���״��ͬ
				b++;
				break;
			}
			case 4: {     //���������״��ͬ
				a++;
				break;
			}
		}
		}
		//System.out.println(a+" "+b+" "+c);
		
		score=5*a+4*b+3*c;
		return score;
	}
//////////////////////////////////////////////////////////////////////////////////////
	
	/**We calculate the score of color, score of color= 6a+5b+4c,
	 * a=Five consecutive colors are the same,
	 * b=Four consecutive colors are the same,
	 * c=Three consecutive colors are the same 
	 * 
	 * @param card The victory card
	 * @return the score of the color.
	 */
	public int Scoring_Color(Card card) {    
		int score = 0;
		int count=0;
		int a=0,b=0,c=0;    //������״���ԣ�score=4a+3b+2c+d
		CountSide();
////////////////////////////////////////////////////////////////
		//������ɫ
//////////�б���
		for(int i=small_ligne;i<=big_ligne;i++) {  
			count = 0;
			for(int j=small_colone;j<=big_colone;j++) {
				if (chessboard[i][j].getColor()==card.getColor()) {    //�Ƚ�ÿ��λ�õ��Ƶ���״�������Ƶ���״
					for (int n=j+1;n<=big_colone;n++) { 
						if (chessboard[i][n].getColor()==card.getColor()) {
							count++;
						}else {
							break;
						}
					}
					j=j+count;
				}
			}
			//System.out.println(count);
			switch (count) {
			case 2: {     //����������״��ͬ
				c++;
				break;
			}
			case 3: {     //�����ĸ���״��ͬ
				b++;
				break;
			}
			case 4: {     //���������״��ͬ
				a++;
				break;
			}
		}
		}   
		//System.out.println(a+" "+b+" "+c);
		
////////�б���
		for(int j=small_colone;j<=big_colone;j++) {  
			count = 0;
			for(int i=small_ligne;i<=big_ligne;i++) {
				if (chessboard[i][j].getColor()==card.getColor()) {    //�Ƚ�ÿ��λ�õ��Ƶ���״�������Ƶ���״
					for (int n=i+1;n<=big_ligne;n++) {        		
						if (card.getColor()==chessboard[n][j].getColor()) {
							count++;
						}else {
							break;
						}
					}
				}
				i=i+count;
			}
			//System.out.println(count);
			switch (count) {
			case 2: {     //����������״��ͬ
				c++;
				break;
			}
			case 3: {     //�����ĸ���״��ͬ
				b++;
				break;
			}
			case 4: {     //���������״��ͬ
				a++;
				break;
			}
		}
		}
		//System.out.println(a+" "+b+" "+c);
		
		score=6*a+5*b+4*c;
		return score;
	}

	
	
	
	
}

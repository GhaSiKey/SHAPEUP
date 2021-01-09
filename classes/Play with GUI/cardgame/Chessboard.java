package cardgame;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;



@SuppressWarnings("deprecation")
public class Chessboard extends Observable {
	
	public Card[][] chessboard;
	private  int cardnumber;                        //�����������м�����
	private int x;                                //��ǰĿ����targetcard�ڶ�ά�����е�x(����ligne,��0��ʼ��
	private int y;                               //��ǰĿ����taegetvard�ڶ�ά�����е�y������colone����0��ʼ��
	public int colone_length;                    //��ǰ�����еĳ���
	public int ligne_length;                    //��ǰ�����ж�����
	public int small_colone;                    //���������һ��
	public int big_colone;                      //�������ҵ�һ��
	public int small_ligne;                     //�������ϵ�һ��
	public int big_ligne;                       //�������µ�һ��
	public boolean flag_m;
	public boolean flag_p;
	public int m,n;
	
	public void setCard(int x,int y,Card card) {
		chessboard[x][y]=card;
	}

	public Chessboard() {
		chessboard = new Card[13][13];      //��ʼ��11X11������
		cardnumber = 3;                     //���������ƣ���λ��ҵ�ʤ���ƣ������ϵ�������3��ʼ����
	}
	
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
		System.out.println("\n====================================================================================================================================================================\n");
		this.setChanged();
		this.notifyObservers();
	}
	
	public void setCardNumber(int i) {
		this.cardnumber = i;
	}
	
	public Card getCard(int x, int y) {          //��������x,y��ȡ�����е���Card
		return chessboard[x][y];
	}
	
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
	
	public int GetCardNumber() {       //�����������������ж�����
		return cardnumber;
	}
	
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
	
	public void InputCard(Card card, int x,int y) {
		chessboard[x][y] = card;
	}
	
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
	public int Scoring_Filled(Card card) {    
		int score = 0;
		int count=0;
		int a=0,b=0,c=0;    //������״���ԣ�score=4a+3b+2c+d
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

	public boolean canPut(int x, int y, Card card) {
		// TODO Auto-generated method stub
		if (chessboard[x][y]!=null) {
			return false;
		}
		else {
			if(chessboard[x-1][y]!=null||chessboard[x][y-1]!=null||chessboard[x+1][y]!=null||chessboard[x][y+1]!=null) {
				Chessboard testchessboardCards = new Chessboard();
				for(int i = 0;i < chessboard.length;i++){
		            for(int j = 0;j < chessboard[i].length;j++){
		            	testchessboardCards.chessboard[i][j] = chessboard[i][j];
		            }
		        }
				testchessboardCards.chessboard[x][y] = card;
				testchessboardCards.CountSide();
				if ((testchessboardCards.ligne_length<=5 && testchessboardCards.colone_length<=3)||testchessboardCards.ligne_length<=3&&testchessboardCards.colone_length<=5) {
					return true;
				}else {
					return false;
				}
			}
			else {
				return false;
			}
		}

	}
}

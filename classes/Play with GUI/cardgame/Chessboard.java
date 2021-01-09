package cardgame;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;



@SuppressWarnings("deprecation")
public class Chessboard extends Observable {
	
	public Card[][] chessboard;
	private  int cardnumber;                        //现在棋盘上有几张牌
	private int x;                                //当前目标牌targetcard在二维数组中的x(行数ligne,从0开始）
	private int y;                               //当前目标牌taegetvard在二维数组中的y（列数colone，从0开始）
	public int colone_length;                    //当前棋盘列的长度
	public int ligne_length;                    //当前棋盘有多少行
	public int small_colone;                    //棋盘最左的一列
	public int big_colone;                      //棋盘最右的一列
	public int small_ligne;                     //棋盘最上的一行
	public int big_ligne;                       //棋盘最下的一行
	public boolean flag_m;
	public boolean flag_p;
	public int m,n;
	
	public void setCard(int x,int y,Card card) {
		chessboard[x][y]=card;
	}

	public Chessboard() {
		chessboard = new Card[13][13];      //初始化11X11的棋盘
		cardnumber = 3;                     //除了隐藏牌，两位玩家的胜利牌，棋盘上的牌数从3开始计数
	}
	
	public void ShowChessBoard() {           //打印棋盘
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
	
	public Card getCard(int x, int y) {          //根据坐标x,y获取数组中的牌Card
		return chessboard[x][y];
	}
	
	public Card getCard(int n, Boolean d) {                   //根据卡号number获取数组中的牌Card(d=true代表要删除此处的牌)
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
	
	public int getCardPosition_X(int n) {       //返回所找牌在棋盘中的x位置
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
	
	public int getCardPosition_Y(int n) {        //返回所找牌在棋盘中的y位置
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
	
	public int GetCardNumber() {       //返回现在棋盘上里有多少牌
		return cardnumber;
	}
	
	public void CountSide() {              //更新棋盘的行数，列数，以及边界
		ArrayList<Integer> colone = new ArrayList<Integer>();    //用来储存棋盘上有哪些列有牌
		ArrayList<Integer> ligne = new ArrayList<Integer>();     //储存哪些行有牌
		
		for(int i=0;i<chessboard.length;i++) {            //便利棋盘把行号，列号存到arraylist里
			for(int j=0;j<chessboard.length;j++) {
				if(chessboard[i][j]!=null) {
					colone.add(j);
					ligne.add(i);
				}
			}
		}
		
		Collections.sort(colone);                    
		Collections.sort(ligne);                    //排序两个arraylist，为了方便获取边界（从小到大/升序）
		
		int colone_size = colone.size();
		int ligne_size = ligne.size();               //因为是从0开始计数，最大值要取（size-1）              
		
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
		int a=0,b=0,c=0,d=0;    //对于形状而言，score=4a+3b+2c+d
		CountSide();
/////////////////////////////////////////////////////////////////////////////////////////
		//计算形状
//////////行遍历
		for(int i=small_ligne;i<=big_ligne;i++) {  
			count = 0;
			for(int j=small_colone;j<=big_colone;j++) {
				if (chessboard[i][j].getShape()==card.getShape()) {    //比较每个位置的牌的形状和输入牌的形状
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
			case 1: {     //连续两个形状相同
				d++;
				break;
			}
			case 2: {     //连续三个形状相同
				c++;
				break;
			}
			case 3: {     //连续四个形状相同
				b++;
				break;
			}
			case 4: {     //连续五个形状相同
				a++;
				break;
			}
			default:
				break;
		}
		}   
		//System.out.println(a+" "+b+" "+c+" "+d);
		
////////列遍历
		for(int j=small_colone;j<=big_colone;j++) {  
			count = 0;
			for(int i=small_ligne;i<=big_ligne;i++) {
				if (chessboard[i][j].getShape()==card.getShape()) {    //比较每个位置的牌的形状和输入牌的形状
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
			case 1: {     //连续两个形状相同
				d++;
				break;
			}
			case 2: {     //连续三个形状相同
				c++;
				break;
			}
			case 3: {     //连续四个形状相同
				b++;
				break;
			}
			case 4: {     //连续五个形状相同
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
		int a=0,b=0,c=0;    //对于形状而言，score=4a+3b+2c+d
		CountSide();
////////////////////////////////////////////////////////////////
		//计算填充
//////////行遍历
		for(int i=small_ligne;i<=big_ligne;i++) {  
			count = 0;
			for(int j=small_colone;j<=big_colone;j++) {
				if (chessboard[i][j].getFilled()==card.getFilled()) {    //比较每个位置的牌的形状和输入牌的形状
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
			case 2: {     //连续三个形状相同
				c++;
				break;
			}
			case 3: {     //连续四个形状相同
				b++;
				break;
			}
			case 4: {     //连续五个形状相同
				a++;
				break;
			}
		}
		}   
		//System.out.println(a+" "+b+" "+c);
		
////////列遍历
		for(int j=small_colone;j<=big_colone;j++) {  
			count = 0;
			for(int i=small_ligne;i<=big_ligne;i++) {
				if (chessboard[i][j].getFilled()==card.getFilled()) {    //比较每个位置的牌的形状和输入牌的形状
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
			case 2: {     //连续三个形状相同
				c++;
				break;
			}
			case 3: {     //连续四个形状相同
				b++;
				break;
			}
			case 4: {     //连续五个形状相同
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
		int a=0,b=0,c=0;    //对于形状而言，score=4a+3b+2c+d
		CountSide();
////////////////////////////////////////////////////////////////
		//计算颜色
//////////行遍历
		for(int i=small_ligne;i<=big_ligne;i++) {  
			count = 0;
			for(int j=small_colone;j<=big_colone;j++) {
				if (chessboard[i][j].getColor()==card.getColor()) {    //比较每个位置的牌的形状和输入牌的形状
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
			case 2: {     //连续三个形状相同
				c++;
				break;
			}
			case 3: {     //连续四个形状相同
				b++;
				break;
			}
			case 4: {     //连续五个形状相同
				a++;
				break;
			}
		}
		}   
		//System.out.println(a+" "+b+" "+c);
		
////////列遍历
		for(int j=small_colone;j<=big_colone;j++) {  
			count = 0;
			for(int i=small_ligne;i<=big_ligne;i++) {
				if (chessboard[i][j].getColor()==card.getColor()) {    //比较每个位置的牌的形状和输入牌的形状
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
			case 2: {     //连续三个形状相同
				c++;
				break;
			}
			case 3: {     //连续四个形状相同
				b++;
				break;
			}
			case 4: {     //连续五个形状相同
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

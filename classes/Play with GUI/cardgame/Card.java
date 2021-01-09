package cardgame;

public class Card {
	private COLOR color;
	private SHAPE shape;
	private FILLED filled;
	private int number;
	
	public Card (COLOR color, SHAPE shape, FILLED filled) {
		this.color=color;
		this.shape=shape;
		this.filled=filled;
		this.number=0;
	}
	
	public COLOR getColor() {
		return color;
	}
	
	public SHAPE getShape() {
		return shape;
	}
	
	public FILLED getFilled() {
		return filled;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int i) {
		this.number = i;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{("+this.number+")");
		sb.append(" "+this.color.toString());
		sb.append(" "+this.shape.toString());
		sb.append(" "+this.filled.toString()+"}");
		return sb.toString();
	}

}

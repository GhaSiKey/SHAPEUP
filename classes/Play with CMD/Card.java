
package cardgame;

/**
 * la class card shows the attribute of cards, color, shape and the background
 * 
 */

public class Card {
	private COLOR color;
	private SHAPE shape;
	private FILLED filled;
	private int number;
	
	/**
	 * 
	 * @param color The color of the card
	 * @param shape The shape of the card
	 * @param filled The fill of the card
	 */
	public Card (COLOR color, SHAPE shape, FILLED filled) {
		this.color=color;
		this.shape=shape;
		this.filled=filled;
		this.number=0;
	}
	/**
	 * 
	 * @return color of card
	 */
	public COLOR getColor() {
		return color;
	}
	/**
	 * 
	 * @return shape of card
	 */
	public SHAPE getShape() {
		return shape;
	}
	
	/**
	 * 
	 * @return the background of card
	 */
	public FILLED getFilled() {
		return filled;
	}
	
	/**
	 * 
	 * @return the number of card
	 */
	
	public int getNumber() {
		return number;
	}
	
	/**
	 * 
	 * @param i The number of the card
	 */
	
	public void setNumber(int i) {
		this.number = i;
	}
	
	/**
	 * use string tostring methode to print out the information of card
	 */
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{("+this.number+")");
		sb.append(" "+this.color.toString());
		sb.append(" "+this.shape.toString());
		sb.append(" "+this.filled.toString()+"}");
		return sb.toString();
	}

}

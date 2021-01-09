package Controleur;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cardgame.Card;
import cardgame.Chessboard;
import cardgame.PlayerReal;



public class Controleurputcard {

	private Chessboard chessboard;
	private JLabel[][] locations;
	private PlayerReal playerReal;
	private JPanel operating;
	private JLabel label;
	private JButton Continue;
	private int x=0,y=0,xo=6,yo=6;
	private String path = null;
	
	public Controleurputcard(Chessboard cb,PlayerReal p,JLabel[][] l,JPanel o,JLabel lb,JButton c) {
		this.chessboard = cb;
		this.locations = l;
		this.playerReal = p;
		this.operating = o;
		this.label = lb; 
		this.Continue = c;
		
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				locations[i][j].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						for (int m = 0; m < 7; m++) {
							for (int n = 0; n < 7; n++) {
								if (e.getSource() == locations[m][n]) {
									x=m;
									y=n;
								}
							}
						} 
						operating.setVisible(true);
						if (playerReal.handCards.peek() != null && (chessboard.m!=x + chessboard.small_ligne - 1 || chessboard.n!=y + chessboard.small_colone - 1)) {
							
							if (chessboard.chessboard[x + chessboard.small_ligne - 1][y + chessboard.small_colone - 1]!=null) {
								//label.setText("<No!You can't put here!>");
								
							}else {
								if (chessboard.canPut(x + chessboard.small_ligne - 1, y + chessboard.small_colone - 1,playerReal.handCards.peek())) {
									path = getPath(playerReal.handCards.peek());
									setImage(path, locations[x][y]);
									setImage("image/bc.jpg", locations[xo][yo]);
									label.setText("<Yes!You can put here!>");
									chessboard.InputCard(playerReal.handCards.pop(),x + chessboard.small_ligne - 1,y + chessboard.small_colone - 1);	
									Continue.setVisible(true);
									xo=6;yo=6;
								}
								else {
									label.setText("<No!You can't put here!>");
									path = getPath(playerReal.handCards.peek());
									setImage("image/bc.jpg", locations[xo][yo]);
									setImage(path, locations[x][y]);
									xo=x;yo=y;
								}
							}
							chessboard.m=0;
							chessboard.n=0;
						}
					}
				});
			}
		}
		

	}
	
	
	public String getPath(Card card) {
		StringBuffer sb = new StringBuffer();
		sb.append("image/");
		sb.append(card.getColor().toString()+card.getShape().toString()+card.getFilled().toString());
		sb.append(".png");
		return sb.toString();
	}
	
	public void setImage(String path, JLabel label1) {
		ImageIcon image;
        image = new ImageIcon(path);
        // image.setImage(image.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT));
        Image img = image.getImage();
        int height = label1.getHeight();
        int width = label1.getWidth();
        img = img.getScaledInstance(width,height, Image.SCALE_DEFAULT);
        image.setImage(img);
        label1.setIcon(image);
	}
		
}




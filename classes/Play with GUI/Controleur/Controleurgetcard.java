package Controleur;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


import cardgame.Card;
import cardgame.Chessboard;
import cardgame.PlayerReal;



public class Controleurgetcard {

	private Chessboard chessboard;
	private JLabel[][] locations;
	private int x=0,y=0;
	private String path = null;
	
	public Controleurgetcard(Chessboard cb,PlayerReal p, JLabel[][] l,JLabel handcard) {
		this.chessboard = cb;
		this.locations = l;
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
						x = x + chessboard.small_ligne - 1;
						y = y + chessboard.small_colone - 1;
						if(chessboard.chessboard[x][y]!=null) {
							if (cb.flag_m == true) {
								path = getPath(chessboard.chessboard[x][y]);
								setImage(path, handcard);
								p.handCards.add(chessboard.chessboard[x][y]);
								chessboard.m = x;
								chessboard.n = y;
								chessboard.chessboard[x][y] = null;
								chessboard.ShowChessBoard();
								chessboard.flag_m = false;
							}
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

 package view;

import cardgame.*;


import javax.swing.*;

import Controleur.Controleurgetcard;
import Controleur.Controleurputcard;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class GameInterface implements Observer{

	private Chessboard cb;
	
	public  JFrame frame;
	private JPanel menu;
	private JLabel gametitle;
	private JButton b_gamestart;
	private JButton b_exit;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JRadioButton easycomputer;
	private JRadioButton cb_1;
	private JRadioButton cb_2;
	private JPanel game;
	private JLabel playernb;
	private JLabel withai;
	private JPanel chessboard;
	public static JLabel p0_0,p0_1,p0_2,p0_3,p0_4;
	public static JLabel p1_0,p1_1,p1_2,p1_3,p1_4;
	public static JLabel p2_0,p2_1,p2_2,p2_3,p2_4;
	public static JLabel p3_0,p3_1,p3_2,p3_3,p3_4;
	public static JLabel p4_0,p4_1,p4_2,p4_3,p4_4;
	private static JLabel[][] locations = new JLabel[7][7];
	private JButton eve;
	private JLabel hiddencard;
	private JLabel vectorycard_p1;
	private JLabel vectorycard_p2;
	private JLabel vectorycard_p3;
	private JLabel handcard;
	private JLabel turn;
	private JButton nextbutton;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel Result;
	private JLabel result_1;
	private JLabel result_2;
	private JLabel result_3;
	private JButton quit;
	private JLabel p0_5;
	private JLabel p0_6;
	private JLabel p1_5;
	private JLabel p1_6;
	private JLabel p2_5;
	private JLabel p2_6;
	private JLabel p3_5;
	private JLabel p3_6;
	private JLabel p4_5;
	private JLabel p4_6;
	private JLabel p5_0;
	private JLabel p5_1;
	private JLabel p5_2;
	private JLabel p5_3;
	private JLabel p5_4;
	private JLabel p5_5;
	private JLabel p5_6;
	private JLabel p6_0;
	private JLabel p6_1;
	private JLabel p6_2;
	private JLabel p6_3;
	private JLabel p6_4;
	private JLabel p6_5;
	private JLabel p6_6;
	private JLabel lblNewLabel_4;
	private JPanel operating_2;
	private JButton movecard_Y;
	private JButton movecard_N;
	private JLabel lblYouDrawThis;
	private JLabel drawcard;
	private JPanel myturnstart_panel;
	private JButton myturnstart_btm;
	
	private JPanel operating_1;
	private JLabel label;
	private JLabel notice;
	private JButton Continue;
	private JRadioButton hardcomputer;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameInterface window = new GameInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.menu);
		frame.setBounds(100, 100, 1273, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		game = new JPanel();
		game.setBackground(Color.LIGHT_GRAY);
		game.setVisible(false);
		
		menu = new JPanel();
		menu.setBackground(SystemColor.menu);
		menu.setBounds(10, 10, 1237, 691);
		frame.getContentPane().add(menu);
		menu.setLayout(null);
		
		
		gametitle = new JLabel("SHAPE UP");
		gametitle.setForeground(Color.WHITE);
		gametitle.setHorizontalTextPosition(SwingConstants.CENTER);
		gametitle.setHorizontalAlignment(SwingConstants.CENTER);
		gametitle.setFont(new Font("Shadows Into Light", Font.BOLD, 99));
		gametitle.setBounds(32, 59, 453, 142);
		menu.add(gametitle);
		
		b_gamestart = new JButton("Start");
		b_gamestart.setForeground(Color.BLACK);
		b_gamestart.setHorizontalTextPosition(SwingConstants.LEADING);
		b_gamestart.setFont(new Font("·ÂËÎ", Font.BOLD, 18));
		b_gamestart.setBounds(159, 550, 107, 23);
		menu.add(b_gamestart);
		
		b_exit = new JButton("Exit");
		b_exit.setForeground(Color.BLACK);
		b_exit.setFont(new Font("·ÂËÎ", Font.BOLD, 18));
		b_exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		b_exit.setBounds(408, 550, 93, 23);
		menu.add(b_exit);
		
		lblNewLabel = new JLabel("Player numbers");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 40));
		lblNewLabel.setBounds(88, 307, 284, 66);
		menu.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Computer Level");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 40));
		lblNewLabel_1.setBounds(88, 421, 284, 66);
		menu.add(lblNewLabel_1);
		
		easycomputer = new JRadioButton("Easy");
		easycomputer.setForeground(Color.WHITE);
		easycomputer.setOpaque(false);
		easycomputer.setFont(new Font("Tahoma", Font.BOLD, 40));
		buttonGroup_1.add(easycomputer);
		easycomputer.setBounds(464, 392, 180, 95);
		menu.add(easycomputer);
		
		hardcomputer = new JRadioButton("Hard");
		hardcomputer.setForeground(Color.WHITE);
		hardcomputer.setOpaque(false);
		hardcomputer.setFont(new Font("Tahoma", Font.BOLD, 40));
		buttonGroup_1.add(hardcomputer);
		hardcomputer.setBounds(464, 463, 163, 39);
		menu.add(hardcomputer);
		
		cb_1 = new JRadioButton("2");
		cb_1.setForeground(Color.WHITE);
		cb_1.setOpaque(false);
		cb_1.setFont(new Font("Tahoma", Font.BOLD, 40));
		buttonGroup.add(cb_1);
		cb_1.setBounds(463, 296, 121, 41);
		menu.add(cb_1);
		
		cb_2 = new JRadioButton("3");
		cb_2.setForeground(Color.WHITE);
		cb_2.setOpaque(false);
		cb_2.setFont(new Font("Tahoma", Font.BOLD, 40));
		buttonGroup.add(cb_2);
		cb_2.setBounds(463, 339, 121, 34);
		menu.add(cb_2);
		
		eve = new JButton("EVE");
		eve.setFont(new Font("Segoe Script", Font.BOLD, 18));
		eve.setBounds(753, 59, 107, 30);
		menu.add(eve);
		
		JLabel bc = new JLabel("");
		bc.setBounds(0, 0, 1237, 691);
		menu.add(bc);
		setImage("image/background.jpg", bc);
		
		b_gamestart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				while (cb_1.isSelected()||cb_2.isSelected()) {
					while (easycomputer.isSelected() || hardcomputer.isSelected()) {
						menu.setVisible(false);
						game.setVisible(true);
						
						if (easycomputer.isSelected()) {
							Strategy strategy = new EasyComputer();
							withai.setText("With AI: EASY" );
							if (cb_1.isSelected()) {
								playernb.setText("Player: 2");
								setNull();
								PVE(strategy);
							}
							if (cb_2.isSelected()) {
								playernb.setText("Player: 3");
								setNull();
								PVEVE(strategy);
							}
						}
						
						if (hardcomputer.isSelected()) {
							Strategy strategy = new HardComputer();
							withai.setText("With AI: HARD" );
							if (cb_1.isSelected()) {
								playernb.setText("Player: 2");
								setNull();
								PVE(strategy);
							}
							if (cb_2.isSelected()) {
								playernb.setText("Player: 3");
								setNull();
								PVEVE(strategy);
							}
						}
						break;
					}
					break;
				}
			}
		});
		
		eve.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menu.setVisible(false);
				game.setVisible(true);
				nextbutton.setVisible(true);
				setNull();
				EVE();
				new Controleurgetcard(cb, null , locations, handcard);
				eve.setVisible(false);
			}
		});
		game.setBounds(137, 10, 944, 732);
		frame.getContentPane().add(game);
		game.setLayout(null);
		
		myturnstart_panel = new JPanel();
		myturnstart_panel.setVisible(false);
		
		operating_2 = new JPanel();
		operating_2.setVisible(false);
		operating_2.setBounds(355, 240, 282, 154);
		game.add(operating_2);
		operating_2.setLayout(null);
		
		lblNewLabel_4 = new JLabel("Do you want to move a card?");
		lblNewLabel_4.setFont(new Font("ËÎÌå", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(20, 22, 262, 39);
		operating_2.add(lblNewLabel_4);
		
		movecard_Y = new JButton("Yes");
		movecard_Y.setBounds(27, 87, 93, 23);
		operating_2.add(movecard_Y);
		
		movecard_N = new JButton("No");
		movecard_N.setBounds(155, 87, 93, 23);
		operating_2.add(movecard_N);
		
		myturnstart_panel.setBounds(367, 259, 252, 146);
		game.add(myturnstart_panel);
		myturnstart_panel.setLayout(null);
		
		myturnstart_btm = new JButton("OK");
		myturnstart_btm.setFont(new Font("ËÎÌå", Font.BOLD, 30));
		myturnstart_btm.setBounds(53, 67, 152, 55);
		myturnstart_panel.add(myturnstart_btm);
		
		JLabel lblNewLabel_8 = new JLabel("Your Turn");
		lblNewLabel_8.setBounds(67, 24, 108, 28);
		lblNewLabel_8.setFont(new Font("ËÎÌå", Font.PLAIN, 24));
		myturnstart_panel.add(lblNewLabel_8);
		
		playernb = new JLabel("");
		playernb.setFont(new Font("ËÎÌå", Font.BOLD, 18));
		playernb.setBounds(593, 10, 221, 15);
		game.add(playernb);
		
		withai = new JLabel("");
		withai.setFont(new Font("ËÎÌå", Font.BOLD, 18));
		withai.setBounds(593, 35, 152, 15);
		game.add(withai);
		
		chessboard = new JPanel();
		chessboard.setBackground(Color.LIGHT_GRAY);
		chessboard.setForeground(Color.LIGHT_GRAY);
		chessboard.setBounds(10, 35, 554, 623);
		game.add(chessboard);
		
		p0_0 = new JLabel("1");
		p0_0.setToolTipText("");
		chessboard.setLayout(new GridLayout(0, 7, 0, 0));
		p0_0.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p0_0);
		locations[0][0] = p0_0;
		
		p0_1 = new JLabel("1");
		p0_1.setToolTipText("");
		p0_1.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p0_1);
		locations[0][1] = p0_1;
		
		p0_2 = new JLabel("1");
		p0_2.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p0_2);
		locations[0][2] = p0_2;
		
		p0_3 = new JLabel("1");
		p0_3.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p0_3);
		locations[0][3] = p0_3;
		
		p0_4 = new JLabel("1");
		p0_4.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p0_4);
		locations[0][4] = p0_4;
		
		p0_5 = new JLabel("1");
		p0_5.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p0_5);
		locations[0][5] = p0_5;
		
		p0_6 = new JLabel("1");
		p0_6.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p0_6);
		locations[0][6] = p0_6;
		
		p1_0 = new JLabel("1");
		p1_0.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p1_0);
		locations[1][0] = p1_0;
		
		p1_1 = new JLabel("1");
		p1_1.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p1_1);
		locations[1][1] = p1_1;
		
		p1_2 = new JLabel("1");
		p1_2.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p1_2);
		locations[1][2] = p1_2;
		
		p1_3 = new JLabel("1");
		p1_3.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p1_3);
		locations[1][3] = p1_3;
		
		p1_4 = new JLabel("1");
		p1_4.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p1_4);
		locations[1][4] = p1_4;
		
		p1_5 = new JLabel("1");
		p1_5.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p1_5);
		locations[1][5] = p1_5;
		
		p1_6 = new JLabel("1");
		p1_6.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p1_6);
		locations[1][6] = p1_6;
		
		p2_0 = new JLabel("1");
		p2_0.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p2_0);
		locations[2][0] = p2_0;
		
		p2_1 = new JLabel("1");
		p2_1.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p2_1);
		locations[2][1] = p2_1;
		
		p2_2 = new JLabel("1");
		p2_2.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p2_2);
		locations[2][2] = p2_2;
		
		p2_3 = new JLabel("1");
		p2_3.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p2_3);
		locations[2][3] = p2_3;
		
		p2_4 = new JLabel("1");
		p2_4.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p2_4);
		locations[2][4] = p2_4;
		
		p2_5 = new JLabel("1");
		p2_5.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p2_5);
		locations[2][5] = p2_5;
		
		p2_6 = new JLabel("1");
		p2_6.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p2_6);
		locations[2][6] = p2_6;
		
		p3_0 = new JLabel("1");
		p3_0.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p3_0);
		locations[3][0] = p3_0;
		
		p3_1 = new JLabel("1");
		p3_1.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p3_1);
		locations[3][1] = p3_1;
		
		p3_2 = new JLabel("1");
		p3_2.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p3_2);
		locations[3][2] = p3_2;
		
		p3_3 = new JLabel("1");
		p3_3.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p3_3);
		locations[3][3] = p3_3;
		
		p3_4 = new JLabel("1");
		p3_4.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p3_4);
		locations[3][4] = p3_4;
		
		p3_5 = new JLabel("1");
		p3_5.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p3_5);
		locations[3][5] = p3_5;
		
		p3_6 = new JLabel("1");
		p3_6.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p3_6);
		locations[3][6] = p3_6;
		
		p4_0 = new JLabel("1");
		p4_0.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p4_0);
		locations[4][0] = p4_0;
		
		
		p4_1 = new JLabel("1");
		p4_1.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p4_1);
		locations[4][1] = p4_1;
		
		p4_2 = new JLabel("1");
		p4_2.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p4_2);
		locations[4][2] = p4_2;
		
		p4_3 = new JLabel("1");
		p4_3.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p4_3);
		locations[4][3] = p4_3;
		
		p4_4 = new JLabel("1");
		p4_4.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p4_4);
		locations[4][4] = p4_4;
		
		p4_5 = new JLabel("1");
		p4_5.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p4_5);
		locations[4][5] = p4_5;
		
		p4_6 = new JLabel("1");
		p4_6.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p4_6);
		locations[4][6] = p4_6;
		
		p5_0 = new JLabel("1");
		p5_0.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p5_0);
		locations[5][0] = p5_0;
		
		p5_1 = new JLabel("1");
		p5_1.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p5_1);
		locations[5][1] = p5_1;
		
		p5_2 = new JLabel("1");
		p5_2.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p5_2);
		locations[5][2] = p5_2;
		
		p5_3 = new JLabel("1");
		p5_3.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p5_3);
		locations[5][3] = p5_3;
		
		p5_4 = new JLabel("1");
		p5_4.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p5_4);
		locations[5][4] = p5_4;
		
		p5_5 = new JLabel("1");
		p5_5.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p5_5);
		locations[5][5] = p5_5;
		
		p5_6 = new JLabel("1");
		p5_6.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p5_6);
		locations[5][6] = p5_6;
		
		p6_0 = new JLabel("1");
		p6_0.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p6_0);
		locations[6][0] = p6_0;
		
		p6_1 = new JLabel("1");
		p6_1.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p6_1);
		locations[6][1] = p6_1;
		
		p6_2 = new JLabel("1");
		p6_2.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p6_2);
		locations[6][2] = p6_2;
		
		p6_3 = new JLabel("1");
		p6_3.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p6_3);
		locations[6][3] = p6_3;
		
		p6_4 = new JLabel("1");
		p6_4.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p6_4);
		locations[6][4] = p6_4;
		
		p6_5 = new JLabel("1");
		p6_5.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p6_5);
		locations[6][5] = p6_5;
		
		p6_6 = new JLabel("1");
		p6_6.setHorizontalAlignment(SwingConstants.CENTER);
		chessboard.add(p6_6);
		locations[6][6] = p6_6;
		
		JLabel lblNewLabel_2 = new JLabel("Hidden Card");
		lblNewLabel_2.setBounds(577, 60, 93, 15);
		game.add(lblNewLabel_2);
		
		hiddencard = new JLabel("");
		hiddencard.setBackground(Color.GRAY);
		hiddencard.setForeground(Color.BLACK);
		hiddencard.setBounds(574, 85, 70, 90);
		game.add(hiddencard);
		
		JLabel lblNewLabel_3 = new JLabel("Vectoycard_1");
		lblNewLabel_3.setBounds(663, 60, 82, 15);
		game.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Vectoycard_2");
		lblNewLabel_3_1.setBounds(755, 60, 93, 15);
		game.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Vectoycard_3");
		lblNewLabel_3_2.setBounds(853, 60, 81, 15);
		game.add(lblNewLabel_3_2);
		
		vectorycard_p1 = new JLabel("");
		vectorycard_p1.setBackground(Color.GRAY);
		vectorycard_p1.setBounds(667, 85, 70, 90);
		game.add(vectorycard_p1);
		
		vectorycard_p2 = new JLabel("");
		vectorycard_p2.setBackground(Color.GRAY);
		vectorycard_p2.setBounds(755, 85, 70, 90);
		game.add(vectorycard_p2);
		
		vectorycard_p3 = new JLabel("");
		vectorycard_p3.setBackground(Color.GRAY);
		vectorycard_p3.setBounds(853, 85, 70, 90);
		game.add(vectorycard_p3);
		
		Result = new JPanel();
		Result.setBackground(Color.YELLOW);
		Result.setToolTipText("");
		Result.setVisible(false);
		Result.setBounds(574, 477, 336, 157);
		game.add(Result);
		Result.setLayout(null);
		
		result_1 = new JLabel("");
		result_1.setHorizontalAlignment(SwingConstants.CENTER);
		result_1.setFont(new Font("Adobe ·ÂËÎ Std R", Font.PLAIN, 24));
		result_1.setBounds(0, 0, 336, 49);
		Result.add(result_1);
		
		result_2 = new JLabel("");
		result_2.setHorizontalAlignment(SwingConstants.CENTER);
		result_2.setFont(new Font("Adobe ·ÂËÎ Std R", Font.PLAIN, 24));
		result_2.setBounds(0, 59, 336, 49);
		Result.add(result_2);
		
		result_3 = new JLabel("");
		result_3.setHorizontalAlignment(SwingConstants.CENTER);
		result_3.setFont(new Font("Adobe ·ÂËÎ Std R", Font.PLAIN, 24));
		result_3.setBounds(0, 108, 336, 49);
		Result.add(result_3);
		
		quit = new JButton("Exit");
		quit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		quit.setBounds(841, 10, 93, 23);
		game.add(quit);
		
		nextbutton = new JButton("Next");
		nextbutton.setVisible(false);
		nextbutton.setBounds(219, 666, 93, 23);
		game.add(nextbutton);
		
		JLabel lblNewLabel4 = new JLabel("You pick this card");
		lblNewLabel4.setBounds(582, 198, 122, 15);
		game.add(lblNewLabel4);
		
		handcard = new JLabel("");
		handcard.setBackground(Color.GRAY);
		handcard.setBounds(593, 223, 70, 90);
		game.add(handcard);
		
		turn = new JLabel("New label");
		turn.setFont(new Font("ËÎÌå", Font.BOLD, 18));
		turn.setBounds(10, 0, 199, 33);
		game.add(turn);
		
		operating_1 = new JPanel();
		operating_1.setVisible(false);
		operating_1.setBounds(574, 323, 302, 157);
		game.add(operating_1);
		operating_1.setLayout(null);
		
		notice = new JLabel("You draw one card and Play it.\r\n");
		notice.setFont(new Font("Adobe ËÎÌå Std L", Font.PLAIN, 18));
		notice.setBounds(10, 10, 267, 37);
		operating_1.add(notice);
		
		JLabel lblNewLabel_5_1 = new JLabel("Click Buttom to continue");
		lblNewLabel_5_1.setFont(new Font("Adobe ËÎÌå Std L", Font.PLAIN, 18));
		lblNewLabel_5_1.setBounds(10, 33, 267, 37);
		operating_1.add(lblNewLabel_5_1);
		
		label = new JLabel("");
		label.setFont(new Font("ËÎÌå", Font.PLAIN, 16));
		label.setBounds(10, 71, 267, 32);
		operating_1.add(label);
		
		Continue = new JButton("Continue");
		Continue.setVisible(false);
		Continue.setBounds(95, 124, 93, 23);
		operating_1.add(Continue);
		
		lblYouDrawThis = new JLabel("You draw this card");
		lblYouDrawThis.setBounds(734, 198, 122, 15);
		game.add(lblYouDrawThis);
		
		drawcard = new JLabel("");
		drawcard.setBackground(Color.GRAY);
		drawcard.setBounds(755, 223, 70, 90);
		game.add(drawcard);

	}
	protected void PVEVE(Strategy strategy) {
		// TODO Auto-generated method stub
		System.out.print("PVEVE");
		String path = null;
		PlayerReal p1 = new PlayerReal("Player");
		PlayerVirtual robot_1 = new PlayerVirtual("Robot_1",strategy);
		PlayerVirtual robot_2 = new PlayerVirtual("Robot_2",strategy);
		cb = new Chessboard();
		cb.addObserver(this);
		Deck deck = new Deck();
		deck.ShuffleDeck();
		
		p1.DrawCard(deck.MoveTheTopCard());
		path = getPath(p1.VectoryCard());
		setImage(path, vectorycard_p1);
		System.out.println("Player's Vectory Card:"+p1.VectoryCard().toString());
		
		robot_1.DrawCard(deck.MoveTheTopCard());
		path = getPath(robot_1.VectoryCard());
		setImage(path, vectorycard_p2);
		System.out.println("Robot's Vectory Card:"+robot_1.VectoryCard().toString());
		
		robot_2.DrawCard(deck.MoveTheTopCard());
		path = getPath(robot_2.VectoryCard());
		setImage(path, vectorycard_p3);
		System.out.println("Robot's Vectory Card:"+robot_2.VectoryCard().toString());
		
		turn.setText("Your Turn:");
		p1.DrawCard(deck.MoveTheTopCard());
		p1.PlayCard(cb);
		cb.ShowChessBoard();
		
		turn.setText("Computer's Turn:");
		robot_1.RobotPlay(cb, deck);
		robot_2.RobotPlay(cb, deck);
		cb.ShowChessBoard();
		
		cb.flag_m = false;
		cb.flag_p = false;
		
		new Controleurgetcard(cb, p1, locations, handcard);
		new Controleurputcard(cb, p1, locations,operating_1,label,Continue);
		
		myturnstart_panel.setVisible(true);	
		myturnstart_btm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				myturnstart_panel.setVisible(false);	
				operating_2.setVisible(true);
				setImage(null, handcard);
				turn.setText("Your Turn:");
			}
		}); 
		
		movecard_Y.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				operating_2.setVisible(false);
				notice.setText("You pick one card and play it.");
				cb.flag_m = true;
				cb.flag_p = false;
			}
		});
		
		movecard_N.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				operating_2.setVisible(false);
				
				String path = null;
				p1.DrawCard(deck.MoveTheTopCard());
				notice.setText("You draw one card and play it.");
				path = getPath(p1.handCards.peek());
				setImage(path, drawcard);
				
				cb.flag_p = true;
			}
		});
		
		Continue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Continue.setVisible(false);
				boolean flag = false;
				if (!deck.IsEmpty()) {
					if(!cb.flag_p) {
						String path = null;
						p1.DrawCard(deck.MoveTheTopCard());
						notice.setText("Then, You draw one card and play it.");
						path = getPath(p1.handCards.peek());
						setImage(path, drawcard);
						cb.flag_p = true;
					}else {
						turn.setText("Computer's Turn:");
						robot_1.RobotPlay(cb, deck);
						robot_2.RobotPlay(cb, deck);
						cb.ShowChessBoard();
						if (deck.IsEmpty()) {
							flag = true;
						}else {
							operating_1.setVisible(false);
							myturnstart_panel.setVisible(true);	
						}
					}
				}else {
					flag = true;
				}
				if (flag) {
					Result.setVisible(true);
					int score=cb.Scoring_Shape(p1.VectoryCard())+cb.Scoring_Filled(p1.VectoryCard())+cb.Scoring_Color(p1.VectoryCard());	
					System.out.println("\nRobot_1'sVectory Card:"+p1.VectoryCard().toString());
					System.out.println("Scoring_Shape:"+cb.Scoring_Shape(p1.VectoryCard()));
					System.out.println("Scoring_Filled:"+cb.Scoring_Filled(p1.VectoryCard()));
					System.out.println("Scoring_Color:"+cb.Scoring_Color(p1.VectoryCard()));
					System.out.println("Player's Total score:"+score);
					result_1.setText("<html>Player's Total score:"+score+"</html>");
					
					score = cb.Scoring_Shape(robot_1.VectoryCard())+cb.Scoring_Filled(robot_1.VectoryCard())+cb.Scoring_Color(robot_1.VectoryCard());
					System.out.println("\nRobot_2'sVectory Card:"+robot_1.VectoryCard().toString());
					System.out.println("Scoring_Shape:"+cb.Scoring_Shape(robot_1.VectoryCard()));
					System.out.println("Scoring_Filled:"+cb.Scoring_Filled(robot_1.VectoryCard()));
					System.out.println("Scoring_Color:"+cb.Scoring_Color(robot_1.VectoryCard()));
					System.out.println("Robot_1's Total score:"+score);
					result_2.setText("Robot_1's Total score:"+score);
					
					score = cb.Scoring_Shape(robot_2.VectoryCard())+cb.Scoring_Filled(robot_2.VectoryCard())+cb.Scoring_Color(robot_2.VectoryCard());
					System.out.println("\nRobot_2'sVectory Card:"+robot_2.VectoryCard().toString());
					System.out.println("Scoring_Shape:"+cb.Scoring_Shape(robot_2.VectoryCard()));
					System.out.println("Scoring_Filled:"+cb.Scoring_Filled(robot_2.VectoryCard()));
					System.out.println("Scoring_Color:"+cb.Scoring_Color(robot_2.VectoryCard()));
					System.out.println("Robot_2's Total score:"+score);
					result_3.setText("Robot_2's Total score:"+score);
				}
			}
		});	
	}

	protected void PVE(Strategy strategy) {
		// TODO Auto-generated method stub
		System.out.print("PVE");
		String path = null;
		PlayerReal p1 = new PlayerReal("Player");
		PlayerVirtual robot = new PlayerVirtual("Robot",strategy);
		cb = new Chessboard();
		cb.addObserver(this);

		Deck deck = new Deck();
		deck.ShuffleDeck();
		
		deck.setHiddenCard();
		path = getPath(deck.getHiddeCard());
		setImage(path, hiddencard);
		System.out.println("HiddenCard: "+deck.getHiddeCard().toString());
		
		p1.DrawCard(deck.MoveTheTopCard());
		path = getPath(p1.VectoryCard());
		setImage(path, vectorycard_p1);
		System.out.println("Player's Vectory Card:"+p1.VectoryCard().toString());
		
		robot.DrawCard(deck.MoveTheTopCard());
		path = getPath(robot.VectoryCard());
		setImage(path, vectorycard_p2);
		System.out.println("Robot's Vectory Card:"+robot.VectoryCard().toString());
		
		turn.setText("Your Turn:");
		p1.DrawCard(deck.MoveTheTopCard());
		p1.PlayCard(cb);
		cb.ShowChessBoard();
		
		turn.setText("Computer's Turn:");
		robot.RobotPlay(cb, deck);
		cb.ShowChessBoard();
		
		
		cb.flag_m = false;
		cb.flag_p = false;
		
		new Controleurgetcard(cb, p1, locations, handcard);
		new Controleurputcard(cb, p1, locations,operating_1,label,Continue);
		
		myturnstart_panel.setVisible(true);	
		myturnstart_btm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				myturnstart_panel.setVisible(false);	
				operating_2.setVisible(true);
				setImage(null, handcard);
				turn.setText("Your Turn:");
			}
		}); 
		
		movecard_Y.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				operating_2.setVisible(false);
				notice.setText("You pick one card and play it.");
				cb.flag_m = true;
				cb.flag_p = false;
			}
		});
		
		movecard_N.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				operating_2.setVisible(false);
				
				String path = null;
				p1.DrawCard(deck.MoveTheTopCard());
				notice.setText("You draw one card and play it.");
				path = getPath(p1.handCards.peek());
				setImage(path, drawcard);
				
				cb.flag_p = true;
			}
		});
		
		Continue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Continue.setVisible(false);
				if (!deck.IsEmpty()) {
					if(!cb.flag_p) {
						String path = null;
						p1.DrawCard(deck.MoveTheTopCard());
						setImage(null, handcard);
						notice.setText("Then, You draw one card and play it.");
						path = getPath(p1.handCards.peek());
						setImage(path, drawcard);
						cb.flag_p = true;
					}else {
						turn.setText("Computer's Turn:");
						robot.RobotPlay(cb, deck);
						cb.ShowChessBoard();
						operating_1.setVisible(false);
						myturnstart_panel.setVisible(true);	
					}
				}else {
					Result.setVisible(true);
					int score=cb.Scoring_Shape(p1.VectoryCard())+cb.Scoring_Filled(p1.VectoryCard())+cb.Scoring_Color(p1.VectoryCard());	
					System.out.println("\nRobot_1'sVectory Card:"+p1.VectoryCard().toString());
					System.out.println("Scoring_Shape:"+cb.Scoring_Shape(p1.VectoryCard()));
					System.out.println("Scoring_Filled:"+cb.Scoring_Filled(p1.VectoryCard()));
					System.out.println("Scoring_Color:"+cb.Scoring_Color(p1.VectoryCard()));
					System.out.println("Player's Total score:"+score);
					result_1.setText("<html>Player's Total score:"+score+"</html>");
					
					score = cb.Scoring_Shape(robot.VectoryCard())+cb.Scoring_Filled(robot.VectoryCard())+cb.Scoring_Color(robot.VectoryCard());
					System.out.println("\nRobot_2'sVectory Card:"+robot.VectoryCard().toString());
					System.out.println("Scoring_Shape:"+cb.Scoring_Shape(robot.VectoryCard()));
					System.out.println("Scoring_Filled:"+cb.Scoring_Filled(robot.VectoryCard()));
					System.out.println("Scoring_Color:"+cb.Scoring_Color(robot.VectoryCard()));
					System.out.println("Robot's Total score:"+score);
					result_2.setText("Robot's Total score:"+score);
				}
				
			}
		});	
	}
	
	public void EVE() {
		String path = null;
		Strategy strategy = new EasyComputer();
		PlayerVirtual robot_1 = new PlayerVirtual("Robot_1",strategy);
		PlayerVirtual robot_2 = new PlayerVirtual("Robot_2",strategy);
		cb = new Chessboard();
		cb.addObserver(this);

		Deck deck = new Deck();
		deck.ShuffleDeck();
		
		deck.setHiddenCard();
		path = getPath(deck.getHiddeCard());
		setImage(path, hiddencard);
		System.out.println("HiddenCard: "+deck.getHiddeCard().toString());
		
		robot_1.DrawCard(deck.MoveTheTopCard());
		path = getPath(robot_1.VectoryCard());
		setImage(path, vectorycard_p1);
		System.out.println("Robot_1's Vectory Card:"+robot_1.VectoryCard().toString());
		
		robot_2.DrawCard(deck.MoveTheTopCard());
		path = getPath(robot_2.VectoryCard());
		setImage(path, vectorycard_p2);
		System.out.println("Robot_2's Vectory Card:"+robot_2.VectoryCard().toString());
		
		nextbutton.addMouseListener(new MouseAdapter() {
			int turn = 1;
			public void mouseClicked(MouseEvent e) {
				if(!deck.IsEmpty()) {
					if(turn == 1) {
						robot_1.RobotPlay(cb, deck);
						cb.ShowChessBoard();
						turn = 0;
					}
					else {
						robot_2.RobotPlay(cb, deck);
						cb.ShowChessBoard();
						turn = 1;
					}
				}else {
					Result.setVisible(true);
					int score=cb.Scoring_Shape(robot_1.VectoryCard())+cb.Scoring_Filled(robot_1.VectoryCard())+cb.Scoring_Color(robot_1.VectoryCard());	
					System.out.println("\nRobot_1'sVectory Card:"+robot_1.VectoryCard().toString());
					System.out.println("Scoring_Shape:"+cb.Scoring_Shape(robot_1.VectoryCard()));
					System.out.println("Scoring_Filled:"+cb.Scoring_Filled(robot_1.VectoryCard()));
					System.out.println("Scoring_Color:"+cb.Scoring_Color(robot_1.VectoryCard()));
					System.out.println("Robot_1's Total score:"+score);
					result_1.setText("<html>Robot_1's Total score:"+score+"</html>");
					
					score = cb.Scoring_Shape(robot_2.VectoryCard())+cb.Scoring_Filled(robot_2.VectoryCard())+cb.Scoring_Color(robot_2.VectoryCard());
					System.out.println("\nRobot_2'sVectory Card:"+robot_2.VectoryCard().toString());
					System.out.println("Scoring_Shape:"+cb.Scoring_Shape(robot_2.VectoryCard()));
					System.out.println("Scoring_Filled:"+cb.Scoring_Filled(robot_2.VectoryCard()));
					System.out.println("Scoring_Color:"+cb.Scoring_Color(robot_2.VectoryCard()));
					System.out.println("Robot_2's Total score:"+score);
					result_2.setText("Robot_2's Total score:"+score);
					
					nextbutton.setVisible(false);
					
				}
			}	
			
		});
		
	}
	
	//image/bluecirclefilled.png
		public String getPath(Card card) {
			StringBuffer sb = new StringBuffer();
			sb.append("image/");
			sb.append(card.getColor().toString()+card.getShape().toString()+card.getFilled().toString());
			sb.append(".png");
			return sb.toString();
		}
	
	//ÈÃÍ¼Æ¬°´ÕÕJLabel´óÐ¡ÏÔÊ¾
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

	//Çå¿ÕµØÍ¼
	public void setNull() {
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				setImage("image/bc.jpg", locations[i][j]);
			}
		}
	}

	@SuppressWarnings({ "static-access" })
	@Override
	public void update(Observable o, Object arg) {
		
		/***
		 * ¸üÐÂÆåÅÌ
		 */
		String path = null;
		setNull();
		
		if(o instanceof Chessboard) {
			
			Chessboard chessboard = (Chessboard)o;
			int m = 1,n = 1;
//			setImage(null, handcard);
			setImage(null, drawcard);
			for(int i=chessboard.small_ligne;i<=chessboard.big_ligne;i++) {
				n=1;
				for(int j=chessboard.small_colone;j<=chessboard.big_colone;j++) {
					if(chessboard.chessboard[i][j]!=null) {
						path = getPath(chessboard.chessboard[i][j]);
						setImage(path, locations[m][n]);
						n++;
					}
					else {
						setImage("image/bc.jpg", locations[m][n]);
						n++;
					}
				}
				m++;
			}
		}
		
		
	}
}

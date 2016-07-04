
import java.awt.*; 
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class Main {
	private static CardLayout cardlayout;
	private static JPanel game;

	static BattlePanel bat;
	static MapPanel map ;
	static MenuPanel menu ;
	static InstructPanel instruct;
	public static void main(String[] args) {

		JFrame app = new JFrame("Battle Adventure Game RPG");
		game = new JPanel();
		cardlayout = new CardLayout();
		
		//make all the panels and use cardlayout to go between them all
		map = new MapPanel();
		bat = new BattlePanel();
		menu = new MenuPanel();
		instruct = new InstructPanel();
		game.setLayout(cardlayout);
		game.add(map,"mapPanel");
		game.add(bat,"battlePanel");
		game.add(menu,"menuPanel");
		game.add(instruct, "instructPanel");
		cardlayout.show(game, "menuPanel");
		
		app.setSize(800, 600);
		app.setVisible(true);
		app.setLocationRelativeTo(null);
		app.setResizable(false);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = app.getContentPane();
		c.setLayout(new BorderLayout());
		app.add(game, BorderLayout.CENTER);
		

	}
	//starting and stopping timers 
	public static void show(String s){
		if(s.equals("mapPanel")){
			instruct.stop();
			bat.stop();
			menu.stop();
			map.start();
			
		
		}
		else if(s.equals("battlePanel")){
			instruct.stop();
			map.stop();
			menu.stop();
			bat.start();
			
			
		}
		else if(s.equals("menuPanel")){
			instruct.stop();
			map.stop();
			bat.stop();
			menu.start();
		}
		else if(s.equals("instructPanel")){
			menu.stop();
			map.stop();
			bat.stop();
			instruct.start();
		}
		cardlayout.show(game,s);
	}
}
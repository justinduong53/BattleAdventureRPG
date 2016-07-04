import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;

import java.awt.event.*;

public class BattlePanel extends JPanel implements KeyListener, ActionListener {
	static final int MAPSTATE = 0;
	static final int BATTLESTATE = 1;
	static final int MENUSTATE = 2;
	static final int INVSTATE = 3;
	static final int WINSTATE = 4;
	private static int state;

	private Timer myTimer;


	static Battle b;
	//Organizes the battles and is panel connecting the battles to the other panels through card layout
	public BattlePanel() {
		
	
		addKeyListener(this);
		
		b = new Battle();
		
		myTimer = new Timer(15, this);
		myTimer.addActionListener(this);
	

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		b.draw(g2d);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == myTimer) {

			update();
			repaint();

		}
	}

	public void update() {
		requestFocusInWindow();
		// bgMusic.stop();

		b.update();
		
	
		
		if (b.lose) {
			b.lose = false;
			Main.show("menuPanel");

		}

	}

	public void keyPressed(KeyEvent k) {
		int key = k.getKeyCode();

		if (key == KeyEvent.VK_DOWN) {
			b.playerMoveDown();
		}
		if (key == KeyEvent.VK_UP) {
			b.playerMoveUp();
		}
		if (key == KeyEvent.VK_ENTER) {
			b.enter();

		}
		if (key == KeyEvent.VK_BACK_SPACE) {
			b.back();

		}

	}

	public void keyReleased(KeyEvent k) {
		int key = k.getKeyCode();

	}

	public void keyTyped(KeyEvent k) {

	}
	public static void newBattle(int type,int bg){
		b.newBattle(type, bg);
	}
	public void stop(){
		myTimer.stop();
	}
	public void start(){
		myTimer.start();
	}
}

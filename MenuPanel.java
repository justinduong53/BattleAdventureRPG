import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;

public class MenuPanel extends JPanel implements MouseMotionListener,
MouseListener, ActionListener {
	//all nessecary compenents to make it look pretty
	ImageIcon bg = new ImageIcon("menubg.gif");
	ImageIcon bg2 = new ImageIcon("win.png");
	JLabel label;
	private Timer myTimer;
	
	JLabel text = new JLabel("        Battle Adventure Game RPG");
	JButton b, b2, b3, b4, b5;
	Font f = new Font("Times New Roman", 100, 50);
	static int num = 0;
	// use panels and layout to make a pretty menu
	public MenuPanel() {
		addMouseListener(this);
		addMouseMotionListener(this);
		myTimer = new Timer(15, this);
		myTimer.addActionListener(this);
		myTimer.start();
		JPanel p = new JPanel();
		text.setFont(f);
		b = new JButton("                Start Game                ");
		b2 = new JButton("                Instructions                  ");
		b3 = new JButton("                Load Game                 ");
		b4 = new JButton("                Options                  ");
		b5 = new JButton("                Exit                  ");
		b.setBackground(new Color(59, 89, 182, 255));
		b.setForeground(Color.WHITE);
		b2.setBackground(new Color(59, 89, 182, 255));
		b2.setForeground(Color.WHITE);
		b3.setBackground(new Color(59, 89, 182, 255));
		b3.setForeground(Color.WHITE);
		b4.setBackground(new Color(59, 89, 182, 255));
		b4.setForeground(Color.WHITE);
		b5.setBackground(new Color(59, 89, 182, 255));
		b5.setForeground(Color.WHITE);
		
		b.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		setLayout(new BorderLayout(120, 50));
		p.setLayout(new GridLayout(5, 1, 10, 10));
		p.add(b);
		p.add(b2);
		p.add(b3);
		p.add(b4);
		p.add(b5);
		add(text, BorderLayout.NORTH);
		label = new JLabel(bg);
		p.setOpaque(false);
		add(p, BorderLayout.EAST);

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if(num == 0)
			bg.paintIcon(this, g2d, 0, 0);
		else {
			bg2.paintIcon(this, g2d, 0, 0);
		}
		}	
	


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == myTimer) {

			update();
			repaint();
			
		} else {

			JButton but = (JButton) e.getSource();
			
			if (but.getText() == "                Exit                  ") {
				System.exit(0);
			}
			else if (but.getText() == "                Instructions                  ") {
				Main.show("instructPanel");
			}
			else if(but.getText() == "                Start Game                ") {
				Main.show("mapPanel");
			}
			
		}
	}

	public void update() {
		requestFocusInWindow();

	}
	public static void change() {
		num = 1;

	}
	
	public void mouseClicked(MouseEvent e) {
	
		
	}


	public void mousePressed(MouseEvent e) {

		
	}

	public void mouseReleased(MouseEvent e) {
		
		
	}


	public void mouseEntered(MouseEvent e) {
	
		
	}


	public void mouseExited(MouseEvent e) {
	
		
	}


	public void mouseDragged(MouseEvent e) {

		
	}


	public void mouseMoved(MouseEvent e) {
		
		
	}
	public void stop(){
		myTimer.stop();
	}
	public void start(){
		myTimer.start();
	}
}

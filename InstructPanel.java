import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class InstructPanel extends JPanel implements 
MouseListener, ActionListener {
	private Timer myTimer;
	JButton b, b2, b3;
	int num = 0;
	static BufferedImage i1,i2,i3= null;
	Font f = new Font("Times New Roman", 100, 50);
	ImageIcon bg = new ImageIcon("menubg.gif");
	//instructions panel, use layouts and buttons to create sort of a slide show to explain my game
	public InstructPanel(){
		int num = 0;
		addMouseListener(this);
		myTimer = new Timer(15, this);
		myTimer.addActionListener(this);
		myTimer.start();
		b = new JButton("Back");
		b2 = new JButton("Exit to Main Menu");
		b3 = new JButton("Next");
		JPanel p = new JPanel();
		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout(FlowLayout.CENTER,300,70));
		add(p2);
		setLayout(new BorderLayout(0, 0));
		add(p, BorderLayout.SOUTH);
		
		b.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b.setBackground(new Color(59, 89, 182, 255));
		b.setForeground(Color.WHITE);
		b2.setBackground(new Color(59, 89, 182, 255));
		b2.setForeground(Color.WHITE);
		b3.setBackground(new Color(59, 89, 182, 255));
		b3.setForeground(Color.WHITE);
		
		p.setLayout(new GridLayout(1, 3, 0, 0));
		p.add(b);
		p.add(b2);
		p.add(b3);
		try {
			i1 = ImageIO.read(new File("i1.png"));
			i2 = ImageIO.read(new File("i2.png"));
			i3 = ImageIO.read(new File("i3.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		if(num == 0){
			g.drawImage(i1, 0,0,800,550,null);
		}
		else if(num == 1){
			g.drawImage(i2, 0,0,800,550,null);
		}
		else if(num == 2){
			g.drawImage(i3, 0,0,800,550,null);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == myTimer) {

			update();
			repaint();
			
		} else {

			JButton but = (JButton) e.getSource();
			
			if (but.getText() == "Back") {
				num--;
			}
			else if (but.getText() == "Exit to Main Menu") {
				Main.show("menuPanel");
			}
			else if(but.getText() == "Next") {
				num++;
			}
			
		}
	}

	public void update() {
		requestFocusInWindow();
		if(num < 0){
			num = 0;
		}
		else if(num > 2){
			num = 2;
		}
		
	}

	
	public void mouseClicked(MouseEvent e) {
	
		
	}


	public void mousePressed(MouseEvent e) {

		
	}

	public void mouseReleased(MouseEvent e) {
		
		
	}


	public void mouseEntered(MouseEvent e) {
	
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void stop(){
		myTimer.stop();
	}
	public void start(){
		myTimer.start();
	}
}

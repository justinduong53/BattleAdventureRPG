import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MapPanel extends JPanel implements KeyListener, ActionListener {
	static final int MAPSTATE = 0;
	static final int BATTLESTATE = 1;
	static final int MENUSTATE = 2;
	static final int INVSTATE = 3;
	static final int WINSTATE = 4;
	private static int state;
	private Map map;
	private MapPlayer mp;
	private Timer myTimer;
	private Camera cam;
	private BattleBox battle;
	private CaveBox cave;
	private TownBox town1;
	private WaterBox pool;
	private InvisbleBox outTown, outDung;
	static int randomBattles = 0;
	int shake = 0;
	static int waitTime = 0;
	static BufferedImage house, tree, box = null;
	boolean texting = false;

	// private AudioPlayer bgMusic = new AudioPlayer("tt.wav");
	// private AudioPlayer bgMusic2 = new AudioPlayer("bt.wav");
	public MapPanel() {
		this.setBackground(Color.black);
		try {
			house = ImageIO.read(new File("house.png"));
			tree = ImageIO.read(new File("tree.png"));
			box = ImageIO.read(new File("box.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}
		//Map panel connects all the maps in my game from the worldmap, to the town, to the dungeon 
		// all are controlled by mappanel
		//create all objects
		map = new Map();
		map.setMap03();
		mp = new MapPlayer(32, 32, map);
		cam = new Camera(0, 0, 1600, 1200);
		cave = new CaveBox(730, 825, 128, 128);
		battle = new BattleBox(700, 128, 128, 128);
		town1 = new TownBox(800, 64, 128, 128);
		pool = new WaterBox(608, 224, 160, 128);
		outTown = new InvisbleBox(350, 580, 64, 32);
		outDung = new InvisbleBox(750, 1180, 64, 32);
		changeMap(32, 32);
		addKeyListener(this);
		myTimer = new Timer(15, this);
		myTimer.addActionListener(this);
		myTimer.start();
	}

	public void paintComponent(Graphics g) {
		//draw them when nessecary
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if (waitTime < 75 && randomBattles > 100) {
			g.translate(shake, shake);
		}
		map.draw(g2d, cam.getX(), cam.getY());
		if (map.mapSelect == "maps\\world.txt") {
			
			town1.draw(g2d, cam.getX(), cam.getY());
			cave.draw(g2d, cam.getX(), cam.getY());
		}
		if (map.mapSelect == "maps\\dungeon.txt") {
			battle.draw(g2d, cam.getX(), cam.getY());
		}
		mp.draw(g2d, cam.getX(), cam.getY());
		
		if (map.mapSelect == "maps\\town.txt") {
			pool.draw(g, cam.getX(), cam.getY());
			g.drawImage(tree, 100, 60, 80, 95, null);
			g.drawImage(tree, 60, 280, 80, 95, null);
			g.drawImage(tree, 480, 110, 80, 95, null);
			g.drawImage(house, 260, 30, 180, 150, null);
			if (Collision.javaCollision(mp.box, pool.hitBox)) {
				g.setColor(Color.white);
				g.setFont(new Font("Terminal", Font.BOLD, 25));
				Battle.waterHeal();
				g.drawImage(box, 32, 32, 700, 128, null);
				g.drawString("You're in the fairy pool, your party is now at ",
						64, 64);
				g.drawString("full health & mp! Dead party members are", 64, 96);
				g.drawString("revived to full HP ", 64, 128);
			}
		}

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == myTimer) {

			update();
			repaint();

		}
	}

	public void update() {
		//random battles, when character is moving increase a number, when that number goes over shake screen and start battle
		if (randomBattles > 100) {
			mp.stopMovingHorz();
			mp.stopMovingVert();
			waitTime++;
		}
		if (waitTime > 75) {
			Main.show("battlePanel");
			if (map.mapSelect == "maps\\world.txt") {
				BattlePanel.newBattle((int) (Math.random() * 6), 0);
			}
			if (map.mapSelect == "maps\\dungeon.txt") {
				BattlePanel.newBattle((int) (Math.random() * 6), 1);
			}
		} else {
			shake = (int) (Math.random() * 30) - 15;
		}
		requestFocusInWindow();
		mp.update();
		cam.update((int) mp.pX - 384, (int) mp.pY - 284, (int) mp.velX,
				(int) mp.velY);
		if (map.mapSelect == "maps\\world.txt") {
			if (Collision.javaCollision(mp.box, cave.hitBox)) {

				map.setMap02();
				changeMap(750, 1130);
			}
			if (Collision.javaCollision(mp.box, town1.hitBox)) {

				map.setMap03();
				changeMap(350, 530);

			}
		} else if (map.mapSelect == "maps\\town.txt") {
			if (Collision.javaCollision(mp.box, outTown.hitBox)) {
				map.setMap01();
				changeMap(830, 220);

			}

		} else if (map.mapSelect == "maps\\dungeon.txt") {
			if (Collision.javaCollision(mp.box, outDung.hitBox)) {
				map.setMap01();
				changeMap(780, 980);

			}
			if (Collision.javaCollision(mp.box, battle.hitBox)) {
				Main.show("battlePanel");
				BattlePanel.newBattle(99, 1);
				
				battle.moveTo(battle.x + 100, battle.y);
			}

		}
	
	}

	public void keyPressed(KeyEvent k) {
		//moving and keycodes
		int key = k.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			if (map.mapSelect != "maps\\town.txt") {
				randomBattles++;
			}
			mp.moveLeft();
		}

		if (key == KeyEvent.VK_RIGHT) {
			if (map.mapSelect != "maps\\town.txt") {
				randomBattles++;
			}
			mp.moveRight();

		}
		if (key == KeyEvent.VK_UP) {
			if (map.mapSelect != "maps\\town.txt") {
				randomBattles++;
			}
			mp.moveUp();

		}
		if (key == KeyEvent.VK_DOWN) {
			if (map.mapSelect != "maps\\town.txt") {
				randomBattles++;
			}
			mp.moveDown();

		}
		if (key == KeyEvent.VK_O) {
			Main.show("battlePanel");
			BattlePanel.newBattle(99, 1);

		}

	}

	public void keyReleased(KeyEvent k) {
		int key = k.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			mp.stopMovingHorz();
		}
		if (key == KeyEvent.VK_RIGHT) {
			mp.stopMovingHorz();
		}
		if (key == KeyEvent.VK_UP) {
			mp.stopMovingVert();
		}
		if (key == KeyEvent.VK_DOWN) {
			mp.stopMovingVert();
		}

	}

	public void keyTyped(KeyEvent k) {

	}

	public void stop() {
		myTimer.stop();
	}

	public void start() {
		myTimer.start();
	}

	public void changeMap(int posX, int posY) {
		mp.stopMovingHorz();
		mp.stopMovingVert();
		cam.xmap = Map.getxSize();
		cam.ymap = Map.getySize();

		mp.pX = posX;
		mp.pY = posY;
	}
}

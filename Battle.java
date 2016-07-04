import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.Color;

public class Battle {
	// Create Queues for the units to go into
	private ArrayList<BattlePlayer> playerQueue = new ArrayList<BattlePlayer>();
	private ArrayList<BattleEnemy> enemyQueue = new ArrayList<BattleEnemy>();
	private ArrayList<BattleUnit> actionQueue = new ArrayList<BattleUnit>();
	// The teams in thee battle
	private ArrayList<BattleEnemy> enemyTeam = new ArrayList<BattleEnemy>();
	private ArrayList<Integer> enemySelect = new ArrayList<Integer>();

	private static ArrayList<BattlePlayer> playerTeam = new ArrayList<BattlePlayer>();
	int cnt = 0;
	static int typeBG;
	int type;
	static BufferedImage grassland, box, finger, por, dungBG, priest,
			boss = null;
	private BattlePlayer bp, bp2, bp3;
	
	private ImageIcon storm = new ImageIcon("storm.gif");
	private ImageIcon fireStorm = new ImageIcon("firestorm.gif");
	private ImageIcon fireStormFlip = new ImageIcon("fireStormFlip.gif");
	private ImageIcon explode = new ImageIcon("explode.gif");
	private ImageIcon ice = new ImageIcon("ice.gif");
	private ImageIcon bolt = new ImageIcon("bolt.gif");
	private ImageIcon heal = new ImageIcon("heal.gif");
	private ImageIcon revive = new ImageIcon("revive.gif");
	private ImageIcon casting = new ImageIcon("casting.gif");
	// Win lose
	public boolean win = false;
	public boolean lose = false;
	
	boolean temp = false;

	ArrayList<Flake> stars = new ArrayList<Flake>();
	private boolean choosingEnemy = false;
	private boolean choosingEnemyAll;
	private boolean choosingPlayer;
	private boolean choosingPlayerAll;
	public int expGive = 0;

	public Battle() {
		typeBG = 0;
		type = 0;
		// TO DO LIST
		// ------------
		// FIX MAP
		// ADD NEW ENEMIES
		// ADD IN MENU STUFF
		// IF HAVE TIME ADD NEW A.I
		// COMMENT EVERYTHING

		try {
			// importing images
			grassland = ImageIO.read(new File("bg1.png"));
			dungBG = ImageIO.read(new File("dungBG.png"));
			box = ImageIO.read(new File("box.png"));
			finger = ImageIO.read(new File("finger.png"));
			por = ImageIO.read(new File("por.png"));
			priest = ImageIO.read(new File("priest.png"));
			boss = ImageIO.read(new File("boss.png"));
		} catch (IOException e) {
			e.printStackTrace();

		}
		// create the instances of the players at the beginning of the program
		bp = new BattlePlayer(0, "stand.gif", "Daniel", 600, 230, 64, 48,
				500 + (int) (Math.random() * 10), 1, 100, 100, 0, 1, 100, 100,
				1, "10000", "0", false, 0, 0, false, 0, false, false, 0, 0);
		bp2 = new BattlePlayer(2, "stand.gif", "Jerry", 600, 280, 64, 48,
				500 + (int) (Math.random() * 10), 1, 100, 100, 0, 1, 100, 100,
				1, "10000", "0", false, 0, 0, false, 0, false, false, 0, 0);
		bp3 = new BattlePlayer(1, "stand.gif", "Jerry", 600, 330, 64, 48,
				500 + (int) (Math.random() * 10), 1, 100, 100, 0, 1, 100, 100,
				1, "10000", "0", false, 0, 0, false, 0, false, false, 0, 0);
		playerTeam.add(bp);
		playerTeam.add(bp2);
		playerTeam.add(bp3);

	}

	// ================================================================================================================================================================
	public void draw(Graphics2D g) {
		if (!win && !lose) {
			// drawing things
			g.drawImage(box, 0, 400, 300, 170, null);
			g.drawImage(box, 300, 400, 500, 170, null);

			if (typeBG == 0) {
				g.drawImage(grassland, 0, 0, 800, 400, null);
			} else if (typeBG == 1) {
				g.drawImage(dungBG, 0, 0, 800, 400, null);
			}
			for (int i = 0; i < enemyTeam.size(); i++) {
				enemyTeam.get(i).draw(g);
			}
			if (!actionQueue.isEmpty()) {
				// The way the animation works is only two parts one part to
				// start it and then the action
				// "wait" is the first instance that the move is happening and
				// "wait2" is the animation usually.
				// This is generally used for all my drawing magics
				// Some of them have to use positions by looking at what
				// placement the target is at
				//To explain this I'll just explain it for the first one
				//fire is magicusing 1
				if (actionQueue.get(0).magicUsing == 1) {
					//reset what magic they are using
					actionQueue.get(0).magicUsing = 0;
					//load the image
					//if it reaches this number than
					if (actionQueue.get(0).wait(100)) {
						//reset the image and set their atking true to start the attack
						//in numbers
						explode.getImage().flush();
						actionQueue.get(0).atking = true;
						actionQueue.get(0).move = false;
						temp = false;
					} else {
						//draw the box and name of maigc
						g.setColor(Color.white);
						g.setFont(new Font("Terminal", Font.PLAIN, 25));

						g.drawImage(box, 250, 20, 300, 40, null);
						g.drawString("Fire", 375, 48);
						//turn on a temp
						if (actionQueue.get(0).wait2(25)) {
							temp = true;

						}
						//the temp draws the explosion after 
						//the casting gif has been on for a while
						//so it seems if he casted the spell.
						if (temp) {
							if (actionQueue.get(0).options.substring(3).equals(
									"01")) {
								explode.paintIcon(null, g, 190, (int) 90);
							} else if (actionQueue.get(0).options.substring(3)
									.equals("02")) {
								explode.paintIcon(null, g, 140, (int) 140);
							} else if (actionQueue.get(0).options.substring(3)
									.equals("03")) {
								explode.paintIcon(null, g, 90, (int) 190);
							} else if (actionQueue.get(0).options.substring(3)
									.equals("04")) {
								explode.paintIcon(null, g, 90, (int) 90);
							} else if (actionQueue.get(0).options.substring(3)
									.equals("05")) {
								explode.paintIcon(null, g, 40, (int) 160);
							} else if (actionQueue.get(0).options.substring(3)
									.equals("06")) {
								explode.paintIcon(null, g, -10, (int) 210);
							}
						}
						casting.paintIcon(null, g, (int) actionQueue.get(0).x,
								(int) actionQueue.get(0).y);

					}
				}
				//Same as magicUsing 1
				if (actionQueue.get(0).magicUsing == 2) {

					actionQueue.get(0).magicUsing = 0;
					if (actionQueue.get(0).wait(100)) {
						ice.getImage().flush();
						actionQueue.get(0).atking = true;
						actionQueue.get(0).move = false;
						temp = false;
					} else {

						g.setColor(Color.white);
						g.setFont(new Font("Terminal", Font.PLAIN, 25));

						g.drawImage(box, 250, 20, 300, 40, null);
						g.drawString("Ice", 375, 48);

						if (actionQueue.get(0).wait2(40)) {
							temp = true;

						}
						if (temp) {
							if (actionQueue.get(0).options.substring(3).equals(
									"01")) {
								ice.paintIcon(null, g, 190, (int) 90);
							} else if (actionQueue.get(0).options.substring(3)
									.equals("02")) {
								ice.paintIcon(null, g, 140, (int) 140);
							} else if (actionQueue.get(0).options.substring(3)
									.equals("03")) {
								ice.paintIcon(null, g, 90, (int) 190);
							} else if (actionQueue.get(0).options.substring(3)
									.equals("04")) {
								ice.paintIcon(null, g, 90, (int) 90);
							} else if (actionQueue.get(0).options.substring(3)
									.equals("05")) {
								ice.paintIcon(null, g, 40, (int) 160);
							} else if (actionQueue.get(0).options.substring(3)
									.equals("06")) {
								ice.paintIcon(null, g, -10, (int) 180);
							}
						}
						casting.paintIcon(null, g, (int) actionQueue.get(0).x,
								(int) actionQueue.get(0).y);

					}
				}
				//Same as magicUsing 1
				if (actionQueue.get(0).magicUsing == 3) {

					actionQueue.get(0).magicUsing = 0;
					if (actionQueue.get(0).wait(100)) {
						ice.getImage().flush();
						actionQueue.get(0).atking = true;
						actionQueue.get(0).move = false;
						temp = false;
					} else {

						g.setColor(Color.white);
						g.setFont(new Font("Terminal", Font.PLAIN, 25));

						g.drawImage(box, 250, 20, 300, 40, null);
						g.drawString("Bolt", 375, 48);

						if (actionQueue.get(0).wait2(40)) {
							temp = true;

						}
						if (temp) {
							if (actionQueue.get(0).options.substring(3).equals(
									"01")) {
								bolt.paintIcon(null, g, 170, (int) 40);
							} else if (actionQueue.get(0).options.substring(3)
									.equals("02")) {
								bolt.paintIcon(null, g, 120, (int) 90);
							} else if (actionQueue.get(0).options.substring(3)
									.equals("03")) {
								bolt.paintIcon(null, g, 70, (int) 140);
							} else if (actionQueue.get(0).options.substring(3)
									.equals("04")) {
								bolt.paintIcon(null, g, 70, (int) 40);
							} else if (actionQueue.get(0).options.substring(3)
									.equals("05")) {
								bolt.paintIcon(null, g, 20, (int) 110);
							} else if (actionQueue.get(0).options.substring(3)
									.equals("06")) {
								bolt.paintIcon(null, g, -20, (int) 130);
							}
						}
						casting.paintIcon(null, g, (int) actionQueue.get(0).x,
								(int) actionQueue.get(0).y);

					}
				}
				//Same as magicUsing 1
				if (actionQueue.get(0).magicUsing == 4) {
					actionQueue.get(0).magicUsing = 0;
					if (actionQueue.get(0).wait(70)) {
						fireStorm.getImage().flush();
						actionQueue.get(0).animCount = 0;
						actionQueue.get(0).animCount2 = 0;
						actionQueue.get(0).atking = true;
						actionQueue.get(0).move = false;
					} else {
						if (actionQueue.get(0) instanceof BattlePlayer) {
							fireStorm.paintIcon(null, g, (int) 0, (int) 0);
							casting.paintIcon(null, g,
									(int) actionQueue.get(0).x,
									(int) actionQueue.get(0).y);
						} else {
							fireStormFlip.paintIcon(null, g,
									(int) actionQueue.get(0).x,
									(int) actionQueue.get(0).y);
							g.drawImage(boss, (int) actionQueue.get(0).x,
									(int) actionQueue.get(0).y,
									(int) actionQueue.get(0).w,
									(int) actionQueue.get(0).l, null);
						}
						g.setColor(Color.white);
						g.setFont(new Font("Terminal", Font.PLAIN, 25));
						g.drawImage(box, 250, 20, 300, 40, null);
						g.drawString("Fire Breath", 335, 48);
					}

				}
				//Same as magicUsing 1
				if (actionQueue.get(0).magicUsing == 5) {

					actionQueue.get(0).magicUsing = 0;
					if (actionQueue.get(0).wait(120)) {
						stars.clear();
						actionQueue.get(0).animCount = 0;
						actionQueue.get(0).animCount2 = 0;
						actionQueue.get(0).atking = true;
						actionQueue.get(0).move = false;
					} else {

						if (actionQueue.get(0).wait2(15)) {
							for (int i = 0; i < 50; i++) {
								stars.add(new Flake(
										(int) (Math.random() * 800),
										(int) (Math.random() * 600),
										(int) (Math.random() * 10 + 12),
										(int) (Math.random() * 4 + 6),
										new Color(255, 250, 250), (int) (Math
												.random() * 9) + 1));
							}
						}
						for (Flake i : stars) {
							i.paint(g);
						}
						for (Flake i : stars) {
							i.travel();
							if (i.y > 380) {
								i.y = -100;
								i.c = new Color(255, 250, 250);
								i.x = (int) (Math.random() * 1200) - 600;
							}
						}
						casting.paintIcon(null, g, (int) actionQueue.get(0).x,
								(int) actionQueue.get(0).y);
						g.setColor(Color.white);
						g.setFont(new Font("Terminal", Font.PLAIN, 25));
						g.drawImage(box, 250, 20, 300, 40, null);
						g.drawString("Blizzard", 335, 48);
					}

				}
				//Same as magicUsing 1
				if (actionQueue.get(0).magicUsing == 6) {
					actionQueue.get(0).magicUsing = 0;
					if (actionQueue.get(0).wait(200)) {

						storm.getImage().flush();
						actionQueue.get(0).animCount = 0;
						actionQueue.get(0).animCount2 = 0;
						actionQueue.get(0).atking = true;
						actionQueue.get(0).move = false;

					} else {

						if (actionQueue.get(0).wait2(150)) {

						}
						storm.paintIcon(null, g, (int) 0, (int) 0);
						casting.paintIcon(null, g, (int) actionQueue.get(0).x,
								(int) actionQueue.get(0).y);
						g.setColor(Color.white);
						g.setFont(new Font("Terminal", Font.PLAIN, 25));
						g.drawImage(box, 250, 20, 300, 40, null);
						g.drawString("Storm", 335, 48);
					}
				}
				//Same as magicUsing 1
				if (actionQueue.get(0).magicUsing == 9) {

					actionQueue.get(0).magicUsing = 0;
					if (actionQueue.get(0).wait(150)) {
						revive.getImage().flush();
						actionQueue.get(0).atking = true;
						actionQueue.get(0).move = false;
						temp = false;
					} else {

						g.setColor(Color.white);
						g.setFont(new Font("Terminal", Font.PLAIN, 25));

						g.drawImage(box, 250, 20, 300, 40, null);
						g.drawString("Revive", 375, 48);

						if (actionQueue.get(0).wait2(60)) {
							temp = true;

						}
						if (temp) {
							if (actionQueue.get(0).options.substring(3).equals(
									"07")) {
								revive.paintIcon(null, g, 550, (int) 50);
							} else if (actionQueue.get(0).options.substring(3)
									.equals("08")) {
								revive.paintIcon(null, g, 550, (int) 100);
							} else if (actionQueue.get(0).options.substring(3)
									.equals("09")) {
								revive.paintIcon(null, g, 550, (int) 150);
							}

						}
						casting.paintIcon(null, g, (int) actionQueue.get(0).x,
								(int) actionQueue.get(0).y);

					}
				}

			}

			for (int i = 0; i < playerTeam.size(); i++) {
				playerTeam.get(i).draw(g);
			}
			//Same as magicUsing 1
			if (!actionQueue.isEmpty()) {
				if (actionQueue.get(0).magicUsing == 7) {

					actionQueue.get(0).magicUsing = 0;
					if (actionQueue.get(0).wait(100)) {
						heal.getImage().flush();
						actionQueue.get(0).atking = true;
						actionQueue.get(0).move = false;
						temp = false;
					} else {

						g.setColor(Color.white);
						g.setFont(new Font("Terminal", Font.PLAIN, 25));

						g.drawImage(box, 250, 20, 300, 40, null);
						g.drawString("Heal", 375, 48);

						if (actionQueue.get(0).wait2(40)) {
							temp = true;

						}

						if (temp) {
							// System.out.println(actionQueue.get(0).options.substring(3));
							if (actionQueue.get(0).options.substring(3).equals(
									"00")) {
								heal.paintIcon(null, g, 190, (int) 90);
							} else if (actionQueue.get(0).options.substring(3)
									.equals("01")) {
								heal.paintIcon(null, g, 140, (int) 140);
							} else if (actionQueue.get(0).options.substring(3)
									.equals("02")) {
								heal.paintIcon(null, g, 90, (int) 190);
							} else if (actionQueue.get(0).options.substring(3)
									.equals("03")) {
								heal.paintIcon(null, g, 90, (int) 90);
							} else if (actionQueue.get(0).options.substring(3)
									.equals("04")) {
								heal.paintIcon(null, g, 40, (int) 160);
							} else if (actionQueue.get(0).options.substring(3)
									.equals("05")) {
								heal.paintIcon(null, g, -10, (int) 210);
							}
							if (actionQueue.get(0).options.substring(3).equals(
									"07")) {
								heal.paintIcon(null, g, 540, (int) 170);
							} else if (actionQueue.get(0).options.substring(3)
									.equals("08")) {
								heal.paintIcon(null, g, 540, (int) 220);
							} else if (actionQueue.get(0).options.substring(3)
									.equals("09")) {
								heal.paintIcon(null, g, 540, (int) 270);
							}
						}

						if (actionQueue.get(0) instanceof BattlePlayer) {

							casting.paintIcon(null, g,
									(int) actionQueue.get(0).x,
									(int) actionQueue.get(0).y);
						} else {
							System.out.println(actionQueue.get(0).x + ":"
									+ actionQueue.get(0).y);
							g.drawImage(priest, (int) actionQueue.get(0).x,
									(int) actionQueue.get(0).y,
									(int) actionQueue.get(0).w,
									(int) actionQueue.get(0).l, null);
						}

					}
				}
				//Same as magicUsing 1
				if (actionQueue.get(0).magicUsing == 8) {
					actionQueue.get(0).magicUsing = 0;
					if (actionQueue.get(0).wait(80)) {
						heal.getImage().flush();
						actionQueue.get(0).animCount = 0;
						actionQueue.get(0).animCount2 = 0;
						actionQueue.get(0).atking = true;
						actionQueue.get(0).move = false;
					} else {

						if (actionQueue.get(0).wait2(40)) {

						}
						heal.paintIcon(null, g, 540, (int) 170);
						heal.paintIcon(null, g, 540, (int) 220);
						heal.paintIcon(null, g, 540, (int) 270);
						casting.paintIcon(null, g, (int) actionQueue.get(0).x,
								(int) actionQueue.get(0).y);
						g.setColor(Color.white);
						g.setFont(new Font("Terminal", Font.PLAIN, 25));
						g.drawImage(box, 250, 20, 300, 40, null);
						g.drawString("Heal All", 335, 48);

					}
				}

			}
			// This draws based on the option system
			// These draw based on which enemy to single targetly select
			if (!playerQueue.isEmpty()) {
				if (playerQueue.get(0).options.startsWith("2")
						&& Integer.parseInt(playerQueue.get(0).options
								.substring(3)) == 11) {
					if (enemyTeam.get(0).KO == 0)
						g.drawImage(finger, 200, 200, 32, 32, null);
					if (enemyTeam.get(1).KO == 0)
						g.drawImage(finger, 150, 250, 32, 32, null);
					if (enemyTeam.get(2).KO == 0)
						g.drawImage(finger, 100, 300, 32, 32, null);
					if (enemyTeam.get(3).KO == 0)
						g.drawImage(finger, 100, 200, 32, 32, null);
					if (enemyTeam.get(4).KO == 0)
						g.drawImage(finger, 50, 250, 32, 32, null);
					if (enemyTeam.get(5).KO == 0)
						g.drawImage(finger, 0, 300, 32, 32, null);

				}
				// These as well, these draw fingers on all allies
				if (playerQueue.get(0).options.startsWith("2")
						&& Integer.parseInt(playerQueue.get(0).options
								.substring(3)) == 12) {
					g.drawImage(finger, 550, 230, 32, 32, null);
					g.drawImage(finger, 550, 280, 32, 32, null);
					g.drawImage(finger, 550, 330, 32, 32, null);
				}
				// This draws the option boxes when selecting magic
				if (playerQueue.get(0).options.startsWith("2")
						&& Integer.parseInt(playerQueue.get(0).options
								.substring(2, 3)) > 0) {
					g.setFont(new Font("Terminal", Font.PLAIN, 25));
					g.setColor(Color.WHITE);
					g.drawImage(box, 310, 410, 480, 150, null);
					//if they have the magic, draw the string of the magic
					if (playerQueue.get(0).magics[0] == true) {
						g.drawString("Fire", 350, 440);

						if (playerQueue.get(0).magics[1] == true) {
							g.drawString("Ice", 350, 490);
						}
						if (playerQueue.get(0).magics[2] == true) {
							g.drawString("Bolt", 350, 540);
						}
						if (playerQueue.get(0).magics[3] == true) {
							g.drawString("Fire Breath", 470, 440);
						}
						if (playerQueue.get(0).magics[4] == true) {
							g.drawString("Blizzard", 470, 490);
						}
						if (playerQueue.get(0).magics[5] == true) {
							g.drawString("Storm", 470, 540);
						}
						if (playerQueue.get(0).magics[6] == true) {
							g.drawString("Heal", 650, 440);
						}
						if (playerQueue.get(0).magics[7] == true) {
							g.drawString("Heal All", 650, 490);
						}
						if (playerQueue.get(0).magics[8] == true) {
							g.drawString("Revive", 650, 540);
						}
						// This draws the finger on these option boxes based on
						// the option system again
						if (playerQueue.get(0).options.equals("20100")) {
							g.drawImage(finger, 320, 420, 32, 32, null);
						} else if (playerQueue.get(0).options.equals("20200")) {
							g.drawImage(finger, 320, 470, 32, 32, null);
						} else if (playerQueue.get(0).options.equals("20300")) {
							g.drawImage(finger, 320, 520, 32, 32, null);
						} else if (playerQueue.get(0).options.equals("20400")) {
							g.drawImage(finger, 440, 420, 32, 32, null);
						} else if (playerQueue.get(0).options.equals("20500")) {
							g.drawImage(finger, 440, 470, 32, 32, null);
						} else if (playerQueue.get(0).options.equals("20600")) {
							g.drawImage(finger, 440, 520, 32, 32, null);
						} else if (playerQueue.get(0).options.equals("20700")) {
							g.drawImage(finger, 620, 420, 32, 32, null);
						} else if (playerQueue.get(0).options.equals("20800")) {
							g.drawImage(finger, 620, 470, 32, 32, null);
						} else if (playerQueue.get(0).options.equals("20900")) {
							g.drawImage(finger, 620, 520, 32, 32, null);
						}
					}
				}
			}
		} else if (win) {
			// The win screen draws things based on the character stats and
			// information of the battle such as difficulty
			// to as a give a measure on how much experience points to give for
			// that battle
			g.setColor(Color.black);
			g.fillRect(0, 0, 800, 600);
			g.drawImage(box, 10, 10, 280, 50, null);
			g.setColor(Color.white);
			g.setFont(new Font("Terminal", Font.PLAIN, 30));
			g.drawString("Win Rewards", 60, 45);
			// =======================================
			g.drawImage(box, 10, 65, 780, 150, null);
			g.drawImage(por, 25, 85, 114, 114, null);
			g.drawString(playerTeam.get(0).name
					+ "                       Class: Freelancer", 165, 105);
			if ((playerTeam.get(0).exp + expGive) >= playerTeam.get(0).maxEXP) {
				g.drawString("LVL: " + (playerTeam.get(0).lvl + 1)
						+ "!   LEVEL UP!", 165, 155);
			} else {
				g.drawString("LVL: " + playerTeam.get(0).lvl, 165, 155);
			}

			g.drawString(
					"Exp: " + playerTeam.get(0).exp + " + " + expGive
							+ "               Total EXP:  "
							+ (playerTeam.get(0).exp + expGive) + "/"
							+ playerTeam.get(0).maxEXP, 165, 195);
			// =======================================
			g.drawImage(box, 10, 215, 780, 150, null);
			g.drawImage(por, 25, 235, 114, 114, null);
			g.drawString(playerTeam.get(1).name
					+ "                         Class: Freelancer", 165, 255);
			if ((playerTeam.get(1).exp + expGive) >= playerTeam.get(0).maxEXP) {
				g.drawString("LVL: " + (playerTeam.get(1).lvl + 1)
						+ "!   LEVEL UP!", 165, 305);
			} else {
				g.drawString("LVL: " + playerTeam.get(1).lvl, 165, 305);
			}

			g.drawString(
					"Exp: " + playerTeam.get(1).exp + " + " + expGive
							+ "               Total EXP:  "
							+ (playerTeam.get(1).exp + expGive) + "/"
							+ playerTeam.get(1).maxEXP, 165, 345);
			// =======================================
			g.drawImage(box, 10, 365, 780, 150, null);
			g.drawImage(por, 25, 385, 114, 114, null);
			g.drawString(playerTeam.get(1).name
					+ "                         Class: Freelancer", 165, 405);
			if ((playerTeam.get(1).exp + expGive) >= playerTeam.get(0).maxEXP) {
				g.drawString("LVL: " + (playerTeam.get(1).lvl + 1)
						+ "!   LEVEL UP!", 165, 455);
			} else {
				g.drawString("LVL: " + playerTeam.get(1).lvl, 165, 455);
			}

			g.drawString(
					"Exp: " + playerTeam.get(1).exp + " + " + expGive
							+ "               Total EXP:  "
							+ (playerTeam.get(1).exp + expGive) + "/"
							+ playerTeam.get(1).maxEXP, 165, 495);
		}
	}

	// ================================================================================================================================================================
	public void update() {
		// Generally In my game, everything revloves around queues and options.
		// There are 3 levels of queues, the team, the queue, and the action, I
		// have arraylists for each except that action is shared
		// All units are on their corresponding teams and when their bar fills
		// up, they can make a move and are put in their teams queue to choose a
		// move
		// After they make this move they must wait in the action until they are
		// first and they do their action.
		// This system allows for a real-time feel but is turn based at the same
		// time
		// This uses the option system that I developed, it finds in the string
		// level that the unit is at and
		// does things based on it. To represent this the example of 20104
		// The "2" represents magic - this is the main move
		// The "01" represents fire - a sub move
		// The "04" represents the thing it wants to it 4 would be the 4th
		// placement of the enemy Team - choose who to do it on
		// I found this an extremely effcient way to organize information about
		// what the player wants to do
		eConfirmAction();
		// if they lost send them to main menu
		if (teamAlive()) {
			lose = true;
		}
		// If the player won, clear the slate
		if (enemyAlive()) {
			win = true;
			//reset the players
			for (int i = 0; i < playerTeam.size(); i++) {
				playerTeam.get(i).choose = false;
				playerTeam.get(i).options = "10000";
				playerTeam.get(i).getCd().reset();
			}
			//reset the enemies
			for (int i = 0; i < enemyTeam.size(); i++) {
				enemyTeam.get(i).options = "10000";
				enemyTeam.get(i).getCd().reset();

			}
			//clear all variables and clear all arraylists 
			choosingPlayer = false;
			choosingPlayerAll = false;
			choosingEnemy = false;
			choosingEnemyAll = false;
			actionQueue.clear();
			enemyTeam.clear();
			playerQueue.clear();
			enemyQueue.clear();
		}
		// add all the players to the team
		for (int i = 0; i < playerTeam.size(); i++) {
			// System.out.println(playerTeam.get(i).getCd().cnt);
			if (playerTeam.get(i).getCd().updateAndCheck()) {
				playerQueue.add(playerTeam.get(i));
			}
			//if they are healed over their maxHp are at that hp
			if (playerTeam.get(i).hp >= playerTeam.get(i).maxHP) {
				playerTeam.get(i).hp = playerTeam.get(i).maxHP;

			}
			// check if they're dead
			if (playerTeam.get(i).hp <= 0 && playerTeam.get(i).KO == 0) {
				playerTeam.get(i).KO = 1;
				//if any players from are in still the actionQueue
				//take em out and reset em
				if (!actionQueue.isEmpty()) {
					for (int z = 0; z < actionQueue.size(); z++) {
						if (actionQueue.get(z) == playerTeam.get(i)) {
							actionQueue.get(z).options = "10000";
						}
					}
					actionQueue.remove(playerTeam.get(i));
				}
				if (!playerQueue.isEmpty()) {
					playerQueue.remove(playerTeam.get(i));
				}

			}
			//if they are dead stop em and reset their stuff
			if (playerTeam.get(i).KO == 1) {
				playerTeam.get(i).getCd().stop = true;
				playerTeam.get(i).getCd().reset();
			} else {
				playerTeam.get(i).getCd().stop = false;

			}

		}
		if (!playerQueue.isEmpty()) {
			playerQueue.get(0).choose = true;
		}
		// Do the same thing for enemies
		for (int i = 0; i < enemyTeam.size(); i++) {
			if (enemyTeam.get(i).hp < 0) {
				enemyTeam.get(i).hp = 0;
			}
			if (enemyTeam.get(i).hp >= enemyTeam.get(i).maxHP) {
				enemyTeam.get(i).hp = enemyTeam.get(i).maxHP;

			}
			if (enemyTeam.get(i).getCd().updateAndCheck()) {
				enemyQueue.add(enemyTeam.get(i));

			}
			
			if (enemyTeam.get(i).hp <= 0 && enemyTeam.get(i).KO == 0) {

				enemyTeam.get(i).KO = 1;
				enemySelect.remove((Integer) enemyTeam.get(i).placement);
				Collections.sort(enemySelect);
				if (!actionQueue.isEmpty()) {
					for (int z = 0; z < actionQueue.size(); z++) {
						if (actionQueue.get(z) == enemyTeam.get(i)) {
							actionQueue.get(z).options = "10000";
						}
					}
					actionQueue.remove(enemyTeam.get(i));
				}
				if (!playerQueue.isEmpty()) {
					enemyQueue.remove(enemyTeam.get(i));
				}
			}
			if (enemyTeam.get(i).KO == 1) {
				enemyTeam.get(i).getCd().stop = true;
				enemyTeam.get(i).getCd().reset();
			}

		}

		if (!actionQueue.isEmpty()) {
			if (actionQueue.get(0) instanceof BattlePlayer
					&& !enemyTeam.isEmpty()) {
				actionQueue.get(0).move = true;
				// retargeting when a dead enemy arrives when they are in the
				// action queue
				if (actionQueue.get(0).options.startsWith("1")
						|| actionQueue.get(0).options.substring(0, 3).equals(
								"201")
						|| actionQueue.get(0).options.substring(0, 3).equals(
								"202")
						|| actionQueue.get(0).options.substring(0, 3).equals(
								"203")) {
					if (actionQueue.get(0) instanceof BattlePlayer) {
						if (enemyTeam
								.get(Integer.parseInt(actionQueue.get(0).options
										.substring(4)) - 1).KO == 1) {
							for (int i = 0; i < enemyTeam.size(); i++) {
								// add an extra move up until you find an alive
								// target
								actionQueue.get(0).check += 1;

								// if they go over go back to the beginning or
								// vice versa
								if (actionQueue.get(0).check < 0) {
									actionQueue.get(0).check = enemySelect
											.size() - 1;
								}
								if (actionQueue.get(0).check > enemySelect
										.size() - 1) {
									actionQueue.get(0).check = 0;
								}
								if (!enemyTeam.isEmpty()
										&& !enemySelect.isEmpty()) {
									actionQueue.get(0).options = actionQueue
											.get(0).options.substring(0, 4)
											+ (enemySelect.get(actionQueue
													.get(0).check) + 1);

								}
							}
						}
					}
				}
				if (actionQueue.get(0).atking) {
					actionQueue.get(0).move = false;
					actionQueue.get(0).atking = false;
					if (!enemyTeam.isEmpty())
						// The input of all the actions when they are ready to
						// All of these are done in the Action class, where it
						// takes two BattleUnits
						// and calculates the outcome after the action
						if (actionQueue.get(0).options.startsWith("1")) {

							Action.attack(actionQueue.get(0),
									enemyTeam.get(Integer.parseInt(actionQueue
											.get(0).options.substring(4)) - 1));
						} else if (actionQueue.get(0).options.substring(0, 3)
								.equals("201")) {
							Action.fire(actionQueue.get(0),
									enemyTeam.get(Integer.parseInt(actionQueue
											.get(0).options.substring(4)) - 1));

						} else if (actionQueue.get(0).options.substring(0, 3)
								.equals("202")) {
							Action.ice(actionQueue.get(0),
									enemyTeam.get(Integer.parseInt(actionQueue
											.get(0).options.substring(4)) - 1));
						} else if (actionQueue.get(0).options.substring(0, 3)
								.equals("203")) {
							Action.bolt(actionQueue.get(0),
									enemyTeam.get(Integer.parseInt(actionQueue
											.get(0).options.substring(4)) - 1));
						} else if (actionQueue.get(0).options.substring(0, 3)
								.equals("204")) {
							for (int i = 0; i < enemyTeam.size(); i++) {
								if (enemyTeam.get(i).KO == 0) {
									Action.fireStorm(actionQueue.get(0),
											enemyTeam.get(i));

								}

							}
							Action.loseMana(actionQueue.get(0));
						} else if (actionQueue.get(0).options.substring(0, 3)
								.equals("205")) {
							for (int i = 0; i < enemyTeam.size(); i++) {
								if (enemyTeam.get(i).KO == 0) {
									Action.blizzard(actionQueue.get(0),
											enemyTeam.get(i));

								}

							}
							Action.loseMana(actionQueue.get(0));
						} else if (actionQueue.get(0).options.substring(0, 3)
								.equals("206")) {
							for (int i = 0; i < enemyTeam.size(); i++) {
								if (enemyTeam.get(i).KO == 0) {
									Action.storm(actionQueue.get(0),
											enemyTeam.get(i));

								}

							}
							Action.loseMana(actionQueue.get(0));
						} else if (actionQueue.get(0).options.substring(0, 3)
								.equals("207")) {
							Action.heal(actionQueue.get(0),
									playerTeam.get(Integer.parseInt(actionQueue
											.get(0).options.substring(4)) - 7));
						} else if (actionQueue.get(0).options.substring(0, 3)
								.equals("208")) {
							for (int i = 0; i < playerTeam.size(); i++) {
								if (playerTeam.get(i).KO == 0) {
									Action.heal(actionQueue.get(0),
											playerTeam.get(i));

								}

							}
							Action.loseMana(actionQueue.get(0));
						} else if (actionQueue.get(0).options.substring(0, 3)
								.equals("209")) {
							Action.revive(actionQueue.get(0),
									playerTeam.get(Integer.parseInt(actionQueue
											.get(0).options.substring(4)) - 7));
						}
					// remove from queue and reset
					actionQueue.get(0).options = "10000";
					actionQueue.remove(0);
				}

			}
			// Enemies use enemy AI a bit lacking, but they generally choose
			// good choices
			else if (!playerTeam.isEmpty()
					&& actionQueue.get(0) instanceof BattleEnemy) {
				actionQueue.get(0).options = enemyAI();
				actionQueue.get(0).move = true;
				if (actionQueue.get(0).atking) {
					actionQueue.get(0).move = false;
					actionQueue.get(0).atking = false;
					if (actionQueue.get(0).options.startsWith("1")) {

						Action.attack(actionQueue.get(0),
								playerTeam.get(Integer.parseInt(actionQueue
										.get(0).options.substring(3, 5))));
					} else if (actionQueue.get(0).options.substring(0, 3)
							.equals("207")) {

						Action.heal(actionQueue.get(0), enemyTeam.get(Integer
								.parseInt(actionQueue.get(0).options.substring(
										3, 5))));
					} else if (actionQueue.get(0).options.substring(0, 3)
							.equals("204")) {
						for (int i = 0; i < playerTeam.size(); i++) {
							if (playerTeam.get(i).KO == 0) {
								Action.bossFireBreath(actionQueue.get(0),
										playerTeam.get(i));
							}
						}
					}
					// take them out of the queue and reset
					actionQueue.get(0).options = "10000";
					actionQueue.remove(0);
				}
			}
		}

		// This is meant for allowing the player to choose their target but also
		// check throughly for any possible errors
		// such as dead enemies of empty arraylists
		if (!playerQueue.isEmpty() && choosingEnemy && !enemyTeam.isEmpty()) {
			if (!enemySelect.isEmpty()) {
				if (enemySelect.size() > 1) {
					if (enemyTeam
							.get(enemySelect.get(playerQueue.get(0).check)).KO == 0) {
						playerQueue.get(0).options = playerQueue.get(0).options
								.substring(0, 4)
								+ (enemySelect.get(playerQueue.get(0).check) + 1);
					}
					
				}
				else if(enemySelect.size() == 1){
					if (enemyTeam
							.get(enemySelect.get(0)).KO == 0) {
						playerQueue.get(0).options = playerQueue.get(0).options
								.substring(0, 4)
								+ (enemySelect.get(0) + 1);
				}
				}
			}
		}
		// chooses player
		if (!playerQueue.isEmpty() && choosingPlayer && !playerTeam.isEmpty()) {
			playerQueue.get(0).options = playerQueue.get(0).options.substring(
					0, 4)
					+ ((playerTeam.get(playerQueue.get(0).check).placement) + 7);
		}
		// chooses all enemies
		if (!playerQueue.isEmpty() && choosingEnemyAll && !enemyTeam.isEmpty()) {

			playerQueue.get(0).options = playerQueue.get(0).options.substring(
					0, 3) + (11);
		}
		// chooses all players
		if (!playerQueue.isEmpty() && choosingPlayerAll && !enemyTeam.isEmpty()) {

			playerQueue.get(0).options = playerQueue.get(0).options.substring(
					0, 3) + (12);
		}
	
	}

	// =================================================================================================================================================================================
	// New battle allows me to input a combination of any of the enemies that I
	// created into an arraylist of their parent class "BattleEnemy". 
	// This makes things extremely convienent for as you can see below
	//I can just add the type of enemy I want with their placement
	public void newBattle(int type, int typeBG) {
		this.typeBG = typeBG;
		if (typeBG == 0) {
			if (type == 0) {
				//amount of exp to give at end of battle
				expGive = 50;
				enemyTeam.add(new EnemySoldier(0));
				enemyTeam.add(new EnemySoldier(1));
				enemyTeam.add(new EmptyEnemy(2));
				enemyTeam.add(new EnemySoldier(3));
				enemyTeam.add(new EmptyEnemy(4));
				enemyTeam.add(new EmptyEnemy(5));
			} else if (type == 1) {
				expGive = 50;
				enemyTeam.add(new EnemySoldier(0));
				enemyTeam.add(new EnemyWolf(1));
				enemyTeam.add(new EmptyEnemy(2));
				enemyTeam.add(new EmptyEnemy(3));
				enemyTeam.add(new EnemySoldier(4));
				enemyTeam.add(new EnemyWolf(5));
			}

			else if (type == 2) {
				expGive = 50;
				enemyTeam.add(new EmptyEnemy(0));
				enemyTeam.add(new EnemyWolf(1));
				enemyTeam.add(new EnemyWolf(2));
				enemyTeam.add(new EnemyWolf(3));
				enemyTeam.add(new EnemyWolf(4));
				enemyTeam.add(new EmptyEnemy(5));
			} else if (type == 3) {
				expGive = 50;
				enemyTeam.add(new EnemySoldier(0));
				enemyTeam.add(new EnemySoldier(1));
				enemyTeam.add(new EmptyEnemy(2));
				enemyTeam.add(new EmptyEnemy(3));
				enemyTeam.add(new EnemyWolf(4));
				enemyTeam.add(new EmptyEnemy(5));
			} else if (type == 4) {
				expGive = 100;
				enemyTeam.add(new EnemySoldier(0));
				enemyTeam.add(new EnemySoldier(1));
				enemyTeam.add(new EnemySoldier(2));
				enemyTeam.add(new EmptyEnemy(3));
				enemyTeam.add(new EnemyPriest(4));
				enemyTeam.add(new EmptyEnemy(5));
			} else if (type == 5) {
				expGive = 100;
				enemyTeam.add(new EnemyWolf(0));
				enemyTeam.add(new EnemyWolf(1));
				enemyTeam.add(new EnemyWolf(2));
				enemyTeam.add(new EmptyEnemy(3));
				enemyTeam.add(new EnemySoldier(4, "Pack Leader", 50, 2));
				enemyTeam.add(new EmptyEnemy(5));
			}
		} else if (typeBG == 1) {
			if (type == 0) {
				expGive = 200;
				enemyTeam.add(new EnemySkel(0));
				enemyTeam.add(new EnemySkel(1));
				enemyTeam.add(new EmptyEnemy(2));
				enemyTeam.add(new EmptyEnemy(3));
				enemyTeam.add(new EnemyPriest(4));
				enemyTeam.add(new EmptyEnemy(5));
			} else if (type == 2) {
				expGive = 200;
				enemyTeam.add(new EnemyKiller(0));
				enemyTeam.add(new EnemyKiller(1));
				enemyTeam.add(new EmptyEnemy(2));
				enemyTeam.add(new EmptyEnemy(3));
				enemyTeam.add(new EnemyPriest(4));
				enemyTeam.add(new EmptyEnemy(5));
			} else if (type == 3) {
				expGive = 200;
				enemyTeam.add(new EnemyKiller(0));
				enemyTeam.add(new EnemySkel(1));
				enemyTeam.add(new EnemyKiller(2));
				enemyTeam.add(new EmptyEnemy(3));
				enemyTeam.add(new EnemyPriest(4));
				enemyTeam.add(new EmptyEnemy(5));
			} else if (type == 4) {
				expGive = 250;
				enemyTeam.add(new EnemyKiller(0));
				enemyTeam.add(new EnemySkel(1));
				enemyTeam.add(new EmptyEnemy(2));
				enemyTeam.add(new EmptyEnemy(3));
				enemyTeam.add(new EmptyEnemy(4));
				enemyTeam.add(new EnemyKiller(5));
			} else if (type == 5) {
				expGive = 400;
				enemyTeam.add(new EnemySkel(0));
				enemyTeam.add(new EnemySkel(1));
				enemyTeam.add(new EnemySkel(2));
				enemyTeam.add(new EmptyEnemy(3));
				enemyTeam.add(new EnemyPriest(4, "Necromancer", 200, 2, 2));
				enemyTeam.add(new EmptyEnemy(5));
			}
		}
		//boss fight
		if (type == 99) {
			expGive = 1000000;
			enemyTeam.add(new EmptyEnemy(0));
			enemyTeam.add(new EmptyEnemy(1));
			enemyTeam.add(new EmptyEnemy(2));
			enemyTeam.add(new EmptyEnemy(3));
			enemyTeam.add(new BossEnemy(4));
			enemyTeam.add(new EmptyEnemy(5));
		}
		for (int i = 0; i < enemyTeam.size(); i++) {
			if (enemyTeam.get(i).KO == 0) {
				enemySelect.add(enemyTeam.get(i).placement);
			}
		}
	}

	// heal the players
	public static void waterHeal() {
		for (int i = 0; i < playerTeam.size(); i++) {
			playerTeam.get(i).KO = 0;
			playerTeam.get(i).hp = playerTeam.get(i).maxHP;
			playerTeam.get(i).mana = playerTeam.get(i).maxMP;

		}
	}

	// when the player pushes the down button it check using the option system
	// to check what do
	// next when it is at a certain level
	public void playerMoveDown() {
		if (!playerQueue.isEmpty()) {
			if (playerQueue.get(0).options.substring(3).equals("00")) {
				if (playerQueue.get(0).options.startsWith("1")) {
					playerQueue.get(0).moveDownLVL1();
				} else if (playerQueue.get(0).options.startsWith("2")) {
					if (Integer.parseInt(playerQueue.get(0).options.substring(
							2, 3)) > 0) {

						playerQueue.get(0).moveDownLVL2();
					} else {
						playerQueue.get(0).moveDownLVL1();
					}
				} else if (playerQueue.get(0).options.startsWith("3")) {
					playerQueue.get(0).moveDownLVL1();
				}

			} else if (playerQueue.get(0).check >= enemySelect.size() - 1
					&& choosingEnemy) {
				playerQueue.get(0).check = 0;
			} else if (playerQueue.get(0).check + 7 >= 9 && choosingPlayer) {
				playerQueue.get(0).check = 0;
			} else
				playerQueue.get(0).check += 1;
		}
	}

	// same thing for up
	public void playerMoveUp() {
		if (!playerQueue.isEmpty()) {
			if (playerQueue.get(0).options.substring(3).equals("00")) {
				if (playerQueue.get(0).options.startsWith("1")) {
					playerQueue.get(0).moveUpLVL1();
				} else if (playerQueue.get(0).options.startsWith("2")) {
					if (Integer.parseInt(playerQueue.get(0).options.substring(
							2, 3)) > 0) {
						playerQueue.get(0).moveUpLVL2();
					} else {
						playerQueue.get(0).moveUpLVL1();
					}

				} else if (playerQueue.get(0).options.startsWith("3")) {
					playerQueue.get(0).moveUpLVL1();
				}
			} else if (playerQueue.get(0).check <= 0 && choosingEnemy) {
				playerQueue.get(0).check = enemySelect.size() - 1;
			} else if (playerQueue.get(0).check + 7 <= 7 && choosingPlayer) {
				playerQueue.get(0).check = 2;
			} else

				playerQueue.get(0).check -= 1;
		}
	}

	// confirm the action for enemies and take them out of their queues
	public void eConfirmAction() {
		if (!enemyQueue.isEmpty()) {
			if (!actionQueue.contains(enemyQueue.get(0))) {
				actionQueue.add(enemyQueue.get(0));
				enemyQueue.get(0).getCd().reset();
				enemyQueue.remove(0);

			}
		}
	}

	// same for players
	public void pConfirmAction() {
		if (!playerQueue.isEmpty()) {
			if (!actionQueue.contains(playerQueue.get(0))) {

				actionQueue.add(playerQueue.get(0));

			}
		}
	}

	// back space reset everything
	public void back() {
		if (!playerQueue.isEmpty()) {
			playerQueue.get(0).back();
			choosingPlayer = false;
			choosingPlayerAll = false;
			choosingEnemy = false;
			choosingEnemyAll = false;
		}
	}

	// what happens when I press enter, alot of different things happen at many
	// different levels
	// of the game
	public void enter() {
		// when im on my win screen i can move on and give my characters all
		// these new things and bring them back to life
		if (win) {
			win = false;
			for (int i = 0; i < playerTeam.size(); i++) {
				if (playerTeam.get(i).exp + expGive >= playerTeam.get(i).maxEXP) {
					playerTeam.get(i).lvl += 1;
					playerTeam.get(i).exp = 0;
					playerTeam.get(i).matk += 1;
					playerTeam.get(i).atk += 1;
					playerTeam.get(i).maxEXP = (int) (playerTeam.get(i).maxEXP * 2.5);
					playerTeam.get(i).maxHP += 50;
					playerTeam.get(i).hp = playerTeam.get(i).maxHP;
					playerTeam.get(i).maxMP += 50;
					playerTeam.get(i).mana = playerTeam.get(i).maxMP;
				} else {
					playerTeam.get(i).exp += expGive;
					if (playerTeam.get(i).KO == 1) {
						playerTeam.get(i).KO = 0;
						playerTeam.get(i).hp = 1;
					}
				}
			}
			// beating the game

			if (expGive > 10000) {
				for (int i = 0; i < playerTeam.size(); i++) {
					playerTeam.get(i).lvl = 1;
					playerTeam.get(i).exp = 0;
					playerTeam.get(i).matk = 1;
					playerTeam.get(i).atk = 1;
					playerTeam.get(i).maxEXP = 100;
					playerTeam.get(i).maxHP = 100;
					playerTeam.get(i).hp = playerTeam.get(i).maxHP;
					playerTeam.get(i).maxMP = 100;
					playerTeam.get(i).mana = playerTeam.get(i).maxMP;
				}
				MapPanel.randomBattles = 0;
				MapPanel.waitTime = 0;
				MenuPanel.change();
				Main.show("menuPanel");
			}
			else{
				MapPanel.randomBattles = 0;
				MapPanel.waitTime = 0;
				Main.show("mapPanel");
			}
			
		}
		// checks what to do next using the option system, chooses based on
		// which magic
		if (!playerQueue.isEmpty()) {
			playerQueue.get(0).check = 0;
			if (playerQueue.get(0).options.equals("20100")
					|| playerQueue.get(0).options.equals("20200")
					|| playerQueue.get(0).options.equals("20300")) {
				choosingEnemy = true;
			} else if (playerQueue.get(0).options.equals("20400")
					|| playerQueue.get(0).options.equals("20500")
					|| playerQueue.get(0).options.equals("20600")) {
				choosingEnemyAll = true;
			} else if (playerQueue.get(0).options.equals("20700")
					|| (playerQueue.get(0).options.equals("20900"))) {
				choosingPlayer = true;
			} else if (playerQueue.get(0).options.equals("20800")) {
				choosingPlayerAll = true;
			}
			// checks if they having nothing and then start them off with attack
			// regular and choosing enemy true
			else if (playerQueue.get(0).options.substring(3).equals("00")) {
				if (playerQueue.get(0).options.startsWith("1")) {
					choosingEnemy = true;
				} else if (playerQueue.get(0).options.startsWith("2")) {
					playerQueue.get(0).options = "20100";
				}

			} else {
				// confirm the action and allow the player to go into the
				// actionQueue
				choosingPlayer = false;
				choosingPlayerAll = false;
				choosingEnemy = false;
				choosingEnemyAll = false;
				playerQueue.get(0).choose = false;
				playerQueue.get(0).getCd().reset();
				pConfirmAction();

				playerQueue.remove(0);

			}

		}
	}

	// enemyAI
	public String enemyAI() {
		// if they are not a special A.I of priest or boss, they attack the
		// target with the lowest health focusing their efforts
		int min = 0;
		if (!(actionQueue.get(0) instanceof EnemyPriest)
				&& !(actionQueue.get(0) instanceof BossEnemy)) {
			for (int i = 0; i < playerTeam.size(); i++) {
				if (playerTeam.get(min).hp <= 0
						|| (playerTeam.get(i).hp > 0 && playerTeam.get(i).hp < playerTeam
								.get(min).hp)) {

					min = i;

				}
			}

			return "1000" + min;
			// the idea of the priest is that he will heal his allies ,
			// but no allies need heal, or no more allies, he just attacks
		} else if (actionQueue.get(0) instanceof EnemyPriest) {
			if (enemySelect.size() > 1) {

				for (int i = 0; i < enemyTeam.size(); i++) {
					if (enemyTeam.get(min).hp <= 0
							|| (enemyTeam.get(i).hp > 0 && enemyTeam.get(i).hp < enemyTeam
									.get(min).hp)) {

						min = i;

					}
				}
				if (!(enemyTeam.get(min).hp == enemyTeam.get(min).maxHP)) {
					actionQueue.get(0).magicUsing = 7;
					return "2070" + min;
				} else {
					//they attack the target with the lowest health focusing their efforts
					for (int i = 0; i < playerTeam.size(); i++) {
						if (playerTeam.get(min).hp <= 0
								|| (playerTeam.get(i).hp > 0 && playerTeam
										.get(i).hp < playerTeam.get(min).hp)) {

							min = i;

						}
					}
					return "1000" + min;
				}
			} else {
				//they attack the target with the lowest health focusing their efforts
				for (int i = 0; i < playerTeam.size(); i++) {
					if (playerTeam.get(min).hp <= 0
							|| (playerTeam.get(i).hp > 0 && playerTeam.get(i).hp < playerTeam
									.get(min).hp)) {

						min = i;

					}
				}
				return "1000" + min;
			}
			// Boss A.I breathes fire if there is 3 ppl to cause maximum damage.
			// If anyone is under 70 health he can finish them with a
			// regular attack thats it
		} else if (actionQueue.get(0) instanceof BossEnemy) {
			int numberOfAlive = playerTeam.size();
			for (int i = 0; i < playerTeam.size(); i++) {
				if (playerTeam.get(i).KO == 1) {
					numberOfAlive--;
				}
			}
			for (int z = 0; z < playerTeam.size(); z++) {
				if (playerTeam.get(z).hp <= 70) {
					actionQueue.get(0).magicUsing = 0;
					for (int i = 0; i < playerTeam.size(); i++) {

						if (playerTeam.get(min).hp <= 0
								|| (playerTeam.get(i).hp > 0 && playerTeam
										.get(i).hp < playerTeam.get(min).hp)) {

							min = i;

						}

					}
					return "1000" + min;
				}
			}

			if (numberOfAlive > 2) {
				actionQueue.get(0).magicUsing = 4;
				return "20400";
			}

		}

		//just in case nothing got through 
		//they will just attack
		//they attack the target with the lowest health focusing their efforts
		for (int i = 0; i < playerTeam.size(); i++) {
			if (playerTeam.get(min).hp <= 0
					|| (playerTeam.get(i).hp > 0 && playerTeam.get(i).hp < playerTeam
							.get(min).hp)) {

				min = i;

			}
		}

		return "1000" + min;
	}

	// check is players are alive
	public boolean teamAlive() {
		for (int i = 0; i < playerTeam.size(); i++) {
			if (playerTeam.get(i).KO == 0) {
				return false;
			}
		}
		return true;
	}

	// check if enemies are alive
	public boolean enemyAlive() {
		for (int i = 0; i < enemyTeam.size(); i++) {
			if (enemyTeam.get(i).realKO == 0) {
				return false;
			}
		}
		return true;
	}
}

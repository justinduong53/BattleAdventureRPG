import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class BattlePlayer extends BattleUnit {
	static BufferedImage sprite, box, finger, dead = null;
	static BufferedImage[] slash = new BufferedImage[8];
	ImageIcon storm = new ImageIcon("storm.gif");
	String file, name;
	int placement;
	// , x, y, l, w
	Cooldown cd;
	boolean choose;
	boolean[] magics = new boolean[9];
	String target;
	public int animFrame;
	ImageIcon casting = new ImageIcon("casting.gif");
	// Projectile p = new Projectile(1000, 1000, 0, 0, 0, 0);
	public int exp = 0;
	public int maxEXP = 100;
	public int lvl = 1;
	//all the status and related fiels with being a battle unit child
	public BattlePlayer(int placement, String spriteFile, String name, int x,
			int y, int l, int w, int cooldown, int atk, int hp, int maxHP,
			int KO, int spd, int mana, int maxMP, int matk, String options,
			String target, boolean atking, int animCount, int animCount2,
			boolean move, int check, boolean hit, boolean healed,
			int damageTaken, int magicUsing) {
		// cd, atk, hp, KO, spd
		super(cooldown, atk, hp, maxHP, KO, spd, mana, maxMP, matk, options,
				target, atking, animCount, animCount2, move, check, hit,
				healed, damageTaken, magicUsing);

		this.options = options;

		// image fields
		file = spriteFile;
		this.placement = placement;
		this.name = name;
		// image placement fields
		this.x = x;
		this.y = y;
		this.l = l;
		this.w = w;
		

		// battle fields
		cd = getCd();
		this.atk = atk;
		this.maxHP = maxHP;
		this.mana = mana;
		this.matk = matk;
		this.hp = hp;
		hp = 0;
		//An important aspect of the BattlePlayer is the connection between options
		//and check, check allows me to let the player choose the unit they want.
		//How it does this is that it allows me to have a number which is what they are
		//selecting, this allows for things like easy limit handling ie. if they go over
		//the maximum number of enemies in the list it will go back to the first enemy
		//which is not dead
		this.check = check;
		this.KO = KO;
		this.animCount = animCount;
		animCount = 0;
		this.spd = spd;
		this.atking = atking;
		choose = false;
		this.hit = hit;
		this.healed = healed;
		this.maxMP = maxMP;
		try {
			box = ImageIO.read(new File("box.png"));
			dead = ImageIO.read(new File("dead.gif"));
			finger = ImageIO.read(new File("finger.png"));
			sprite = ImageIO.read(new File(file));
			for (int i = 0; i < 8; i++) {

				slash[i] = ImageIO.read(new File("slash/slash_" + (i + 1)
						+ ".png"));
			}

		} catch (IOException e) {
			e.printStackTrace();

		}
		//give them all the magics
		for (int i = 0; i < 9; i++) {
			magics[i] = true;
		}
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.white);
		g.setFont(new Font("Terminal", Font.PLAIN, 25));
		//if he is dead , he is dead then
		if (KO == 1) {
			g.drawImage(dead, x, y, w, l, null);
			hp = 0;
			options = "10000";
		} else {
			//if hes being hit, show damage
			if (hit) {
				g.setFont(new Font("Terminal", Font.PLAIN, 20));
				if (wait(20)) {
					hit = false;
				}
				g.drawString(Integer.toString(damageTaken), x, y - 15 - animCount
						* 2);
				g.drawImage(sprite, x, y, w, l, null);

			}
			//being healed show healing spawns a green text to
			//show the number that they were healed by
			if (healed) {
				g.setColor(Color.green);
				g.setFont(new Font("Terminal", Font.PLAIN, 20));
				if (wait(20)) {
					healed = false;
				}
				g.drawString(Integer.toString(damageTaken), x, y - 15 - animCount
						* 2);
				g.drawImage(sprite, x, y, w, l, null);

			}
			g.setColor(Color.white);
			g.setFont(new Font("Terminal", Font.PLAIN, 25));
			//check if he is attacking or doing amove
			if (move) {
				//does the animation for the correseponding move 
				if (options.startsWith("1")) {
					if (wait(30)) {
						atking = true;
						move = false;
					}

					else {
						if (animCount < 8) {
							if (options.substring(3).equals("01")) {
								g.drawImage(slash[animCount], 200, 130, 128,
										128, null);

							} else if (options.substring(3).equals("02")) {
								g.drawImage(slash[animCount], 150, 180, 128,
										128, null);
							} else if (options.substring(3).equals("03")) {
								g.drawImage(slash[animCount], 100, 230, 128,
										128, null);
							} else if (options.substring(3).equals("04")) {
								g.drawImage(slash[animCount], 100, 130, 128,
										128, null);
							} else if (options.substring(3).equals("05")) {
								g.drawImage(slash[animCount], 50, 200, 128,
										128, null);
							} else if (options.substring(3).equals("06")) {
								g.drawImage(slash[animCount], 0, 270, 128, 128,
										null);
							}

							g.drawImage(sprite, x - animCount * 8, y, w, l,
									null);
						}
						g.drawImage(sprite, x - 64, y, w, l, null);

					}

				}
				//gives a magicUsing variable to "Battle" class to draw in battle class
				if (options.startsWith("2")) {
					if (options.substring(1, 3).equals("01")) {

						magicUsing = 1;
					}
					if (options.substring(1, 3).equals("02")) {

						magicUsing = 2;
					}
					if (options.substring(1, 3).equals("03")) {

						magicUsing = 3;
					}
					if (options.substring(1, 3).equals("04")) {

						magicUsing = 4;
					}
					if (options.substring(1, 3).equals("05")) {

						magicUsing = 5;
					}
					if (options.substring(1, 3).equals("06")) {

						magicUsing = 6;
					}
					if (options.substring(1, 3).equals("07")) {

						magicUsing = 7;
					}

					if (options.substring(1, 3).equals("08")) {

						magicUsing = 8;
					}
					if (options.substring(1, 3).equals("09")) {

						magicUsing = 9;
					}

				}
				//if not doing action just standing
			} else {
				if (!hit && !healed) {
					animCount = 0;
					animCount2 = 0;
				}
				
				g.drawImage(sprite, x, y, w, l, null);
			}
			//choosing boxes and choose what to do next
			//uses option system
			//explained better in my battle.java update comment
			// The "2" represents magic - this is the main move
			// The "01" represents fire - a sub move
			// The "04" represents the thing it wants to it 4 would be the 4th

			if (choose) {
				//print all the options that the player has
				//move things accordingly to the option system
				g.drawImage(box, 20, 410, 260, 155, null);
				g.drawString("Attack", 50, 440);
				g.drawString("Magic", 50, 480);
				if (Integer.parseInt(options) <= 0) {
					options = "20000";
				}
				if (Integer.parseInt(options) >= 30000) {
					options = "10000";
				}

				if (options.equals("21000")) {
					options = "20100";
				}

				if (options.equals("10000")) {
					g.drawImage(finger, 20, 420, 32, 32, null);
				} else if (options.equals("20000")) {
					g.drawImage(finger, 20, 460, 32, 32, null);
				} else if (options.equals("30000")) {
					g.drawImage(finger, 20, 500, 32, 32, null);
				}

				if (options.substring(3).equals("01")) {
					g.drawImage(finger, 200, 200, 32, 32, null);
				} else if (options.substring(3).equals("02")) {
					g.drawImage(finger, 150, 250, 32, 32, null);
				} else if (options.substring(3).equals("03")) {
					g.drawImage(finger, 100, 300, 32, 32, null);
				} else if (options.substring(3).equals("04")) {
					g.drawImage(finger, 100, 200, 32, 32, null);
				} else if (options.substring(3).equals("05")) {
					g.drawImage(finger, 50, 250, 32, 32, null);
				} else if (options.substring(3).equals("06")) {
					g.drawImage(finger, 0, 300, 32, 32, null);
				}
				// player targeting
				else if (options.substring(3).equals("07")) {
					g.drawImage(finger, 550, 230, 32, 32, null);
				} else if (options.substring(3).equals("08")) {
					g.drawImage(finger, 550, 280, 32, 32, null);
				} else if (options.substring(3).equals("09")) {
					g.drawImage(finger, 550, 330, 32, 32, null);
				}

			}
		}
		//draws the hp bars and cooldown bars
		//draws info based on placement, it allows for me to put things
		//based on their placement on the playerTeam
		g.setFont(new Font("Terminal", Font.PLAIN, 25));
		if (placement == 0) {
			g.drawString(name, 320, 445);
			g.setColor(Color.RED);
			g.drawString(Integer.toString(hp), 470, 445);
			g.setColor(Color.WHITE);
			g.drawString("/", 532, 445);
			g.setColor(new Color(145,11,11));
			g.drawString(Integer.toString(maxHP), 545, 443);
			g.setColor(Color.white);
			g.fillRect(620, 430, 150, 15);
			g.setColor(Color.ORANGE);
			g.fillRect(620, 430, (int) (150 * cd.getProgress()), 15);

		}

		if (placement == 1) {

			g.drawString(name, 320, 545);
			g.setColor(Color.RED);
			g.drawString(Integer.toString(hp), 470, 545);
			g.setColor(Color.WHITE);
			g.drawString("/", 532, 545);
			g.setColor(new Color(145,11,11));
			g.drawString(Integer.toString(maxHP), 545, 543);
			g.setColor(Color.white);
			g.fillRect(620, 530, 150, 15);
			g.setColor(Color.ORANGE);
			g.fillRect(620, 530, (int) (150 * cd.getProgress()), 15);
		}
		if (placement == 2) {
			g.drawString(name, 320, 495);
			g.setColor(Color.RED);
			g.drawString(Integer.toString(hp), 470, 495);
			g.setColor(Color.WHITE);
			g.drawString("/", 532, 495);
			g.setColor(new Color(145,11,11));
			g.drawString(Integer.toString(maxHP), 545, 493);
			g.setColor(Color.white);
			g.fillRect(620, 480, 150, 15);
			g.setColor(Color.ORANGE);
			g.fillRect(620, 480, (int) (150 * cd.getProgress()), 15);
		}
	}
//moving down in the option levels
	public void moveDownLVL1() {
		if (Integer.parseInt(options.substring(4)) <= 0) {
			options = Integer.toString(Integer.parseInt(options) + 10000);
		}

	}
//moving down in the next level in the magic level
	public void moveDownLVL2() {
		if (Integer.parseInt(options.substring(2, 3)) >= 0
				&& options.startsWith("2")) {
			options = Integer.toString(Integer.parseInt(options) + 100);
			// options =
			// options.substring(0,2)+Integer.toString(Integer.parseInt(options.substring(2,3))
			// + 1)+options.substring(3, 5);
		}
	}
	//go back
	public void back() {
		options = "10000";
	}
	//same as down
	public void moveUpLVL1() {
		if (Integer.parseInt(options.substring(4)) <= 0) {
			options = Integer.toString(Integer.parseInt(options) - 10000);
		}
	}

	public void moveUpLVL2() {
		if (Integer.parseInt(options.substring(2, 3)) >= 0
				&& options.startsWith("2")) {
			options = Integer.toString(Integer.parseInt(options) - 100);
		}
	}

}

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.AlphaComposite;

import javax.imageio.ImageIO;

public class BattleEnemy extends BattleUnit {
	BufferedImage sprite, KOsprite = null;
	String file, name, KOfile;
	int placement;
	Cooldown cd;
	float alpha = 1f;
	int realKO = 0;

	public BattleEnemy(int placement, String spriteFile, String KOspriteFile,
			String name, int x, int y, int l, int w, int cooldown, int atk,
			int hp, int maxHP, int KO, int spd, int mana, int maxMP, int matk,
			String options, String target, boolean atking, int animCount,
			int animCount2, boolean move, int check, boolean hit,
			boolean healed, int damageTaken, int magicUsing) {
		// cd, atk, hp, KO, spd
		super(cooldown, atk, hp, maxHP, KO, spd, mana, maxMP, matk, options,
				target, atking, animCount, animCount2, move, check, hit,
				healed, damageTaken, magicUsing);
		this.options = options;

		this.animCount = animCount;
		// image fields
		file = spriteFile;
		KOfile = KOspriteFile;
		this.placement = placement;
		this.name = name;
		// image placement fields
		this.x = x;
		this.hit = hit;
		this.y = y;
		this.l = l;
		this.maxHP = maxHP;
		this.w = w;
		this.damageTaken = damageTaken;
		// battle fields
		this.matk = matk;
		this.atk = atk;
		this.hp = hp;
		this.KO = KO;
		this.animCount = animCount;
		animCount = 0;
		this.spd = spd;
		this.atking = atking;
		this.healed = healed;
		this.maxMP = maxMP;
		try {
			KOsprite = ImageIO.read(new File(KOfile));
			sprite = ImageIO.read(new File(file));

		} catch (IOException e) {
			e.printStackTrace();

		}
	}
//Generally all the same things as in battle player the comments apply for both
	//to check comments for BattleEnemy its the same as BattlePlayer except battle player has more things
	public void draw(Graphics2D g) {
		g.setColor(Color.white);
		
		g.setFont(new Font("Terminal", Font.PLAIN, 20));
		Graphics2D g2d = (Graphics2D) g.create();
		if (KO == 0) {
			//Placements is what place they are on the 6 possible slots of 
			//enemy team. This allows me to put them in their appropriate
			//place and print their information in an orderly fashion
			if (placement == 0) {
				g.drawString(name, 30, 430);
				g.drawString(Integer.toString(hp), 170, 430);
			}
			if (placement == 1) {
				g.drawString(name, 30, 455);
				g.drawString(Integer.toString(hp), 170, 455);

			}
			if (placement == 2) {
				g.drawString(name, 30, 480);
				g.drawString(Integer.toString(hp), 170, 480);
			}
			if (placement == 3) {
				g.drawString(name, 30, 505);
				g.drawString(Integer.toString(hp), 170, 505);

			}
			if (placement == 4) {
				g.drawString(name, 30, 530);
				g.drawString(Integer.toString(hp), 170, 530);
			}
			if (placement == 5) {
				g.drawString(name, 30, 555);
				g.drawString(Integer.toString(hp), 170, 555);
			}
		}
		if (hit) {

			g.drawString(Integer.toString(damageTaken), x, y - 15 - animCount
					* 2);
			g.drawImage(sprite, x, y, w, l, null);
			if (wait(20)) {
				hit = false;

			}
		} else {
			if (KO == 1) {
				alpha += -0.05f;
				if (alpha <= 0) {
					alpha = 0;
					realKO = 1;
				}

				g2d.setComposite(AlphaComposite.getInstance(
						AlphaComposite.SRC_OVER, alpha));
				g2d.drawImage(KOsprite, x, y, w, l, null);
			} else {
				if (move) {
					if (options.startsWith("1")) {
						if (wait(10)) {
							atking = true;
							move = false;
						}

						else {
							if (options.substring(3).equals("01")) {
								// g.drawImage(slash[animCount],200,130,128,128,null);
								// g.drawString(Integer.toString(10*atk),200,130-animCount);
							} else if (options.substring(3).equals("02")) {
								// g.drawImage(slash[animCount],150,180,128,128,null);
							} else if (options.substring(3).equals("03")) {
								// g.drawImage(slash[animCount],100,230,128,128,null);
							} else if (options.substring(3).equals("04")) {
								// g.drawImage(slash[animCount],100,130,128,128,null);
							} else if (options.substring(3).equals("05")) {
								// g.drawImage(slash[animCount],50,200,128,128,null);
							} else if (options.substring(3).equals("06")) {
								// g.drawImage(slash[animCount],0,270,128,128,null);
							}

							g.drawImage(sprite, x + animCount * 4, y, w, l,
									null);

						}

					}
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
				}
				
				else {
					if (!hit && !healed) {
						animCount = 0;
						animCount2 = 0;
					}
					g.drawImage(sprite, x, y, w, l, null);

				}
			}

		}
	}

}
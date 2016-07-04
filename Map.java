import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Map {
	static BufferedImage grass, rock,dirt,stonetile,black,pool = null;
	private static int xSize = 1600;
	private static int ySize = 1200;
	String mapSelect = "maps\\town.txt";
	private static int[][] map;
	private BufferedReader br;
	ImageIcon water = new ImageIcon("water.gif");
	public Map() {
		//How map works is that it creates an array a size that i predetermine and take the
		//information that it need from a text file that I important to create the map
		//If I find a value I will assign that value a particular drawing or meaning on the map
		//E.g Rock is 0, every 0 in the text, i will put a rock
 		setMap(new int[((getySize()) / 32)][((getxSize()) / 32)]);

		try {
			grass = ImageIO.read(new File("grass.jpg"));
			dirt = ImageIO.read(new File("dirt.png"));
			rock = ImageIO.read(new File("rock.png"));
			stonetile = ImageIO.read(new File("stonetile.png"));
			black = ImageIO.read(new File("black.png"));
			pool = ImageIO.read(new File("pool.png"));
			
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	public void setMap01() {
		mapSelect = "maps\\world.txt";
		setxSize(1600);
		setySize(1200);
		setMap(new int[((getySize()) / 32)][((getxSize()) / 32)]);
		reloadMap(mapSelect);
	}
	public void setMap02() {
		mapSelect = "maps\\dungeon.txt";
		setxSize(1600);
		setySize(1200);
		setMap(new int[((getySize()) / 32)][((getxSize()) / 32)]);
		reloadMap(mapSelect);

		
	}
	public void setMap03() {
		mapSelect = "maps\\town.txt";
		setxSize(800);
		setySize(600);
		setMap(new int[((getySize()) / 32)][((getxSize()) / 32)]);
		reloadMap(mapSelect);
	}
	//draw based on the text boxes but also draws things that are only in the camera for
	//increase effecieny
	public void draw(Graphics2D g, int cx, int cy) {
		
		for (int i = 0; i < getMap()[1].length; i++) {
			for (int q = 0; q < getMap().length; q++) {
				if (getMap()[q][i] == 0) {
					if(i*32>cx-32 && i*32< cx+800 && q*32 >cy-32 && q*32< cy+576){
					g.drawImage(rock, i * 32 - cx, q * 32 - cy, 32, 32, null);
					}
				} else if (getMap()[q][i] == 1) {
					if(i*32>cx-32 && i*32< cx+800 && q*32 >cy-32 && q*32< cy+576){
					g.drawImage(grass, i * 32 - cx, q * 32 - cy, 32, 32, null);
					}
				}
				else if (getMap()[q][i] == 2) {
					if(i*32>cx-32 && i*32< cx+800 && q*32 >cy-32 && q*32< cy+576){
					g.drawImage(dirt, i * 32 - cx, q * 32 - cy, 32, 32, null);
					}
				}
				else if (getMap()[q][i] == 3) {
					if(i*32>cx-32 && i*32< cx+800 && q*32 >cy-32 && q*32< cy+576){
					water.paintIcon(null,g, i * 32 - cx, q * 32 - cy);
					}
				}
				else if (getMap()[q][i] == 4) {
					if(i*32>cx-32 && i*32< cx+800 && q*32 >cy-32 && q*32< cy+576){
						g.drawImage(stonetile, i * 32 - cx, q * 32 - cy, 32, 32, null);
					}
				}
				else if (getMap()[q][i] == 5) {
					if(i*32>cx-32 && i*32< cx+800 && q*32 >cy-32 && q*32< cy+576){
						g.drawImage(black, i * 32 - cx, q * 32 - cy, 32, 32, null);
					}
				}
				else if (getMap()[q][i] == 6) {
					if(i*32>cx-32 && i*32< cx+800 && q*32 >cy-32 && q*32< cy+576){
						g.drawImage(pool, i * 32 - cx, q * 32 - cy, 32, 32, null);
					}
				}
			}
		}
		
	}

	public void reloadMap(String mapSelect) {
		
		try {
			br = new BufferedReader(new FileReader(mapSelect));
			String[] input;
			for (int i = 0; i < getMap().length; i++) {
				input = br.readLine().split(" ");
				for (int q = 0; q < getMap()[1].length; q++) {
					getMap()[i][q] = Integer.parseInt(input[q]);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static int[][] getMap() {
		return map;
	}

	public static void setMap(int[][] map) {

		Map.map = map;
		
	}

	public static int getxSize() {
		return xSize;
	}

	public static void setxSize(int xSize) {
		Map.xSize = xSize;
	}

	public static int getySize() {
		return ySize;
	}

	public static void setySize(int ySize) {
		Map.ySize = ySize;
	}

}

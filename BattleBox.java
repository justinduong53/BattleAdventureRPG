
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class BattleBox extends MapBox{
	static BufferedImage boss= null;
	int x;
	int y;
	int xSize;
	int ySize;
	Rectangle hitBox;
	//hitboxes on the maps
	public BattleBox(int x, int y,int xSize, int ySize){
		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
		this.hitBox = new Rectangle(x,y,xSize,ySize);
		try {
			boss = ImageIO.read(new File("boss.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}
	}
	public void draw(Graphics g, int cx, int cy){
		g.drawImage(boss,(int)x-cx,(int)y-cy,xSize,ySize,null);
	}

	void moveTo(int newX, int newY) {
		x = newX;
		y = newY;
		hitBox = new Rectangle(x,y,xSize,ySize);
	}
	void changeSize(int newW, int newL) {
		xSize = newW;
		ySize = newL;
		hitBox = new Rectangle(x,y,xSize,ySize);
	}
}

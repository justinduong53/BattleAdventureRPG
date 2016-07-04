
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class TownBox extends MapBox{
	static BufferedImage town= null;
	int x;
	int y;
	int xSize;
	int ySize;
	//hitboxes on the maps
	public TownBox(int x, int y,int xSize, int ySize){
		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
		this.hitBox = new Rectangle(x,y,xSize,ySize);
		try {
			town = ImageIO.read(new File("town.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}
	}
	public void draw(Graphics g, int cx, int cy){
		g.drawImage(town,(int)x-cx,(int)y-cy,xSize,ySize,null);
	}

	void moveTo(int newX, int newY) {
		x = newX;
		y = newY;
	}
	void changeSize(int newW, int newL) {
		xSize = newW;
		ySize = newL;
	}
}


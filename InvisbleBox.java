
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class InvisbleBox extends MapBox{
	static BufferedImage town= null;
	int x;
	int y;
	int xSize;
	int ySize;
	//just a hit box on the maps
	public InvisbleBox(int x, int y,int xSize, int ySize){
		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
		this.hitBox = new Rectangle(x,y,xSize,ySize);
		
	}
	public void draw(Graphics g, int cx, int cy){

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


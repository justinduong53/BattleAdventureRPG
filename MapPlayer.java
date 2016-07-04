import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Graphics2D;






import javax.swing.*;

import java.awt.event.*;

import javax.imageio.ImageIO;

public class MapPlayer {
	int tileSize = 32;
	double pX;
	double pY;
	int velX = 0;
	int velY = 0;
	Map m;
	int ci = 4;
	int cnt = 0;
	Rectangle2D box;
	
	ImageIcon up = new ImageIcon("up.gif");
	ImageIcon left = new ImageIcon("left.gif");
	ImageIcon right = new ImageIcon("right.gif");
	ImageIcon down = new ImageIcon("down.gif");
	
	public MapPlayer(int x, int y, Map m) {
		this.m = m;
		pX = x;
		pY = y;
		

	

	}

	public void draw(Graphics2D g,int cx, int cy) {
		if(ci == 1){
			up.paintIcon(null, g, (int)pX - cx,(int) pY-cy);
		}
		else if(ci == 2){
			left.paintIcon(null, g, (int)pX - cx,(int) pY-cy);
		}
		else if(ci == 3){
			right.paintIcon(null, g, (int)pX - cx,(int) pY-cy);
		}
		else if(ci == 4){
			down.paintIcon(null, g, (int)pX - cx,(int) pY-cy);
		}
//			{
//			g.drawImage(player[0], (int)pX - cx,(int) pY - cy, 32, 32, null);
//		}
//		else{
//			g.drawImage(player[1], (int)pX - cx,(int) pY - cy, 32, 32, null);
//		}

		
	}
	//Generally just mvoving arround the player and changing the image based on it
	public void update() {
		box = new Rectangle2D.Double(pX-2,pY-2,tileSize,tileSize);
		pX += velX;
		pY += velY ;
		//Doesn't work for two sides

		int collideHorz = Collision.collideHorz(pX, pY, Map.getMap());
		int collideVert = Collision.collideVert(pX, pY, Map.getMap());
		if (collideHorz == 1) {
			pX+=2;
		}
		if (collideHorz == 2) {
			pX-=2;
		}
		if (collideVert == 3) {
			pY-=2;
		}
		if (collideVert == 4) {
			pY+=2;
		}

		
	}
	
	public void moveLeft() {
		velX = -1;
		ci = 2;
	}

	public void moveRight() {
		velX = 1;
		ci = 3;
	}

	public void moveUp() {
		velY = -1;
		ci = 1;
	}

	public void moveDown() {
		velY = 1;
		ci = 4;
	}

	public void stopMovingHorz() {
		velX = 0;
	}

	public void stopMovingVert() {
		velY = 0;
	}
}

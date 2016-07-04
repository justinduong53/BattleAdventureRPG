
import java.awt.Graphics;
import java.awt.Rectangle;



public abstract class MapBox extends Rectangle{
	//the parent class to all hitboxes on the maps
	//the abstract class lets me make new classes easily
	int x,y;
	Rectangle hitBox;
	

	abstract void draw(Graphics g, int cx, int cy);
	
	abstract void moveTo(int newX,int newY);

	abstract void changeSize(int newW,int newL);
}

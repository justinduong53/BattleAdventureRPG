import java.awt.Color;
import java.awt.Graphics;



class Flake {
	 // variable of type Graphics
	//Snowflakes using recursion and such
	int x,y,size,rand,speed;
	Color c;
	public Flake(int x, int y,int size,int rand,Color c,int speed) {
		this.x = x;
		this.c = c;
		this.y =y;
		this.size = size;
		this.rand= rand;
		this.speed = speed;
	}
	private void drawStar(Graphics g,int x, int y, int size, int rand) {
		Graphics graph = g;
		int endX;
		int endY;
		int randLines = rand;
		if (size <= 2)
			return;

		for (int i = 0; i < randLines; i++) {
			endX = x + (int) (size * Math.cos((2 * Math.PI / randLines) * i));
			endY = y - (int) (size * Math.sin((2 * Math.PI / randLines) * i));
			drawStar(g, endX, endY, size / 3, rand);
			graph.drawLine(x, y, endX, endY);
			
		}
	} 
	public void paint(Graphics g) {
		int width = 800;
		int height = 600;
		int min;
		if (height < width)
			min = height;
		else
			min = width;
		g.setColor(c);

		drawStar(g,x ,y, min / size,rand);
		

		
	}
	public void travel(){
		y+=speed;
		x+=speed;
		
		
	}
	
}
/* This is the camera class.
 * x and y are the coordinates
 * xsize and ysize are the dimensions of the viewport
 * xmap and ymap are the size of the full map
 * the camera will always stay inside bounds when you update it
 * pls remember to update it justin
 * 
 */

//Camera follows the player and only can move around its max area with is xmap and ymap
public class Camera {
	public int x;
	public int y;
	public int xSize;
	public int ySize;
	public int xmap, ymap;

	public Camera(int x, int y, int xmap, int ymap) {
		this.x = x;
		this.y = y;
		xSize = 800;
		ySize = 600;
		this.xmap = xmap;
		this.ymap = ymap;

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void update(int pX, int pY, int velX,int velY) {
		x = (int) Math.min(Math.max((double) pX, 0),(xmap-xSize));
		y = (int) Math.min(Math.max((double) pY, 0),(ymap-ySize));
	}
}


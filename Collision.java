import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Collision {
	private static int tileSize = 32;

	public Collision() {

	}
	//Check points that co-related to the two blocks such as left would be when the any two points of 
	//the top left and bottom right intersect, with the other blocks points. If they intercept that counts
	//as a collision and I push the blocks away based on which direction its coming from
	public static int collideHorz(double pX, double pY, int[][] map) {
		for (int i = 0; i < map[1].length; i++) {
			for (int q = 0; q < map.length; q++) {
				if (map[q][i] == 0 || map[q][i] == 3) {
					Rectangle2D terrain = new Rectangle2D.Double(i * tileSize,
							q * tileSize, tileSize, tileSize);
					// Left Collision
					if (terrain.contains(new Point((int) (pX - 1), (int) pY))
							|| terrain.contains(new Point((int) (pX - 1),
									(int) pY + tileSize))) {
						return 1;
					}
					// Right Collision
					if (terrain.contains(new Point((int) (pX + tileSize + 1),
							(int) pY))
							|| terrain.contains(new Point(
									(int) (pX + tileSize + 1), (int) pY
											+ tileSize))) {
						return 2;
					}

				}
			}
		}
		return 0;
	}

	public static int collideVert(double pX, double pY, int[][] map) {
		for (int i = 0; i < map[1].length; i++) {
			for (int q = 0; q < map.length; q++) {
				
				if (map[q][i] == 0 || map[q][i] == 3) {
					Rectangle2D terrain = new Rectangle2D.Double(i * tileSize,
							q * tileSize, tileSize, tileSize);

					// Bottom Collision
					if (terrain.contains(new Point((int) (pX), (int) (pY
							+ tileSize + 1)))
							|| terrain.contains(new Point(
									(int) (pX + tileSize),
									(int) (pY + tileSize + 1)))) {
						return 3;
					}
					// Top Collision
					if (terrain.contains(new Point((int) (pX), (int) (pY - 1)))
							|| terrain.contains(new Point(
									(int) (pX + tileSize), (int) (pY - 1)))) {
						return 4;
					}
				}
			}
		}
		return 0;
	}
	public static boolean javaCollision(Rectangle2D p, Rectangle2D b){
		if(p.intersects(b)){
			return true;
		}
		else{
			return false;
		}
	}
}

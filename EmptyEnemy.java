import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class EmptyEnemy extends BattleEnemy{
	int cnt;
	//Empty box enemy, placeholder
	public EmptyEnemy(int placement){
		//cd, atk, hp, KO, spd
		super(placement,"blank.png","blank.png","",0,0,0,0,2000+ (int) (Math.random() * 50),1,0,0,1,3,0,0,0,"10000","0",false,0,0,false,0,false,false,0,0);
		
				x = 0;
				y = 0;
				w = 0;
				l = 0;
		
		
	}
	
}

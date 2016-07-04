

public class BossEnemy extends BattleEnemy{
	int setW = 250;
	int setL = 300;
	//type of enemy
	public BossEnemy(int placement){
		//cd, atk, hp, KO, spd
		super(placement,"boss.png","bossDead.png","Basillisk",0,0,0,0,1000+ (int) (Math.random() * 50),7,999,999,0,3,0,1,1,"10000","0",false,0,0,false,0,false,false,0,0);
			
			if (placement == 0) {
				x= 250;
				y= 180;
				w = setW;
				l = setL;
			}
			if (placement == 1) {
				x= 200;
				y= 230;
				w = setW;
				l = setL;
			}
			if (placement == 2) {
				x= 150;
				y= 280;
				w = setW;
				l = setL;
			}
			if (placement == 3) {
				x= 200;
				y= 50;
				w = setW;
				l = setL;
			}
			if (placement == 4) {
				x= 30;
				y= 50;
				w = setW;
				l = setL;
			}
			if (placement == 5) {
				x= 50;
				y= 280;
				w = setW;
				l = setL;
			}
		
	}
	
}

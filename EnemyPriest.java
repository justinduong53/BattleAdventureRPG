
public class EnemyPriest extends BattleEnemy {

	int setW = 49;
	int setL = 109;

	//Regular Enemy
	public EnemyPriest(int placement) {
		super(placement, "priest.png", "priestDead.png", "Dark Priest", 0, 0, 0,
				0, 1000 + (int) (Math.random() * 50), 1, 100, 100, 0, 3, 100, 100, 1,
				"10000", "0", false, 0, 0, false, 0, false, false, 0, 0);

		if (placement == 0) {
			x = 250;
			y = 180;
			w = setW;
			l = setL;
		}
		if (placement == 1) {
			x = 200;
			y = 230;
			w = setW;
			l = setL;
		}
		if (placement == 2) {
			x = 150;
			y = 280;
			w = setW;
			l = setL;
		}
		if (placement == 3) {
			x = 150;
			y = 180;
			w = setW;
			l = setL;
		}
		if (placement == 4) {
			x = 100;
			y = 230;
			w = setW;
			l = setL;
		}
		if (placement == 5) {
			x = 50;
			y = 280;
			w = setW;
			l = setL;
		}

	}
	//Overloading the constructor
	public EnemyPriest(int placement,String name, int hp, int atk, int matk) {
		super(placement, "priest.png", "priestDead.png", name, 0, 0, 0,
				0, 1000 + (int) (Math.random() * 50), atk, hp, hp, 0, 3, 100, 100, matk,
				"10000", "0", false, 0, 0, false, 0, false, false, 0, 0);

		if (placement == 0) {
			x = 250;
			y = 180;
			w = setW;
			l = setL;
		}
		if (placement == 1) {
			x = 200;
			y = 230;
			w = setW;
			l = setL;
		}
		if (placement == 2) {
			x = 150;
			y = 280;
			w = setW;
			l = setL;
		}
		if (placement == 3) {
			x = 150;
			y = 180;
			w = setW;
			l = setL;
		}
		if (placement == 4) {
			x = 100;
			y = 230;
			w = setW;
			l = setL;
		}
		if (placement == 5) {
			x = 50;
			y = 280;
			w = setW;
			l = setL;
		}

	}
}

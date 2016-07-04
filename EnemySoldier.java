
public class EnemySoldier extends BattleEnemy {

	int setW = 50;
	int setL = 90;

	//Regular Enemy
	public EnemySoldier(int placement) {
		super(placement, "soldier.gif", "soldierDead.png", "Soldier", 0, 0, 0,
				0, 1000 + (int) (Math.random() * 50), 1, 30, 30, 0, 3, 0, 0, 0,
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
	//Special Enemy Soldier
	//Overloading the constructor
	public EnemySoldier(int placement,String name, int hp, int atk) {
	
		super(placement, "soldier.gif", "soldierDead.png",name, 0, 0, 0,
				0, 1000 + (int) (Math.random() * 50), atk, hp, hp, 0, 3, 0, 0, 0,
				"10000", "0", false, 0, 0, false, 0, false, false, 0, 0);
		setW = 75;
		setL = 130;
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
			x = 50;
			y = 180;
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
	//Empty Enemy
	public EnemySoldier() {

		super(0, "blank.png", "blank.png", "", 0, 0, 0, 0, 2000 + (int) (Math
				.random() * 50), 1, 0, 0, 1, 3, 0, 0, 0, "10000", "0", false,
				0, 0, false, 0, false, false, 0, 0);

		x = 0;
		y = 0;
		w = 0;
		l = 0;

	}
	
}

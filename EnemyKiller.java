
public class EnemyKiller extends BattleEnemy{

	int setW = 62;
	int setL = 78;
	//Type of enemy
	//Regular Enemy
	public EnemyKiller(int placement) {
		super(placement, "killer.png", "killerDead.png", "Killer", 0, 0, 0,
				0, 500 + (int) (Math.random() * 50), 2, 40, 40, 0, 3, 0, 0, 0,
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

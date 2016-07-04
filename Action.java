public class Action {
	
	public BattleUnit c;
	public BattleUnit t;
	public static int number;

	public Action() {

	}
	//Organizes actions between targets.
	//Does all the calculations of how much to take and gain from each exchange
	//Changes numbers, thats about it
	public static void attack(BattleUnit c, BattleUnit t) {
		number = (c.atk * 10)+(int)(Math.random()*10)-5;
		t.damageTaken = number;
		t.hp -= number;
		t.hit = true;

	}
	
	public static void fire(BattleUnit c, BattleUnit t) {
		//calculate damage
		number = (c.matk * 20)+(int)(Math.random()*10)-5;
		t.damageTaken = number;
		//target loses hp
		t.hp -= number;
		//lose mana for caster
		c.mana -= 10;
		//set the target to hit, so they show damage number
		t.hit = true;

	}

	public static void ice(BattleUnit c, BattleUnit t) {
		number = (c.matk * 20)+(int)(Math.random()*10)-5;
		t.damageTaken = number;
		t.hp -= number;
		c.mana -= 10;
		t.hit = true;

	}

	public static void bolt(BattleUnit c, BattleUnit t) {
		number = (c.matk * 20)+(int)(Math.random()*10)-5;
		t.damageTaken = number;
		t.hp -= number;
		c.mana -= 10;
		t.hit = true;

	}

	public static void fireStorm(BattleUnit c, BattleUnit t) {
		number = (c.matk * 10)+(int)(Math.random()*10)-5;
		t.damageTaken = number;
		t.hp -= number;
		t.hit = true;

	}

	public static void blizzard(BattleUnit c, BattleUnit t) {
		number = (c.matk * 10)+(int)(Math.random()*10)-5;
		t.damageTaken = number;
		t.hp -= number;
		t.hit = true;

	}
	//Ms.S this one kills everything in one hit so use this one to cheat
	public static void storm(BattleUnit c, BattleUnit t) {
		number = (c.matk * 9999)+(int)(Math.random()*10)-5;
		t.damageTaken = number;
		t.hp -= number;
		t.hit = true;

	}

	public static void heal(BattleUnit c, BattleUnit t) {
		number = (c.matk * 40)+(int)(Math.random()*10)-5;
		t.damageTaken = number;
		if (t.KO == 0) {
			t.hp += number;
		}
		System.out.println(number);
		c.mana -= 10;
		t.healed = true;

	}

	public static void revive(BattleUnit c, BattleUnit t) {
		number = (t.maxHP / 5);
		t.damageTaken = number;
		if (t.KO == 1) {
			t.KO = 0;
			t.hp += number;
			t.healed = true;
		}
		c.mana -= 50;

	}
	public static void bossFireBreath(BattleUnit c, BattleUnit t) {
		number = (c.matk * 40)+(int)(Math.random()*10)-5;
		t.damageTaken = number;
		t.hp -= number;
		t.hit = true;

	}
	
	public static void loseMana(BattleUnit c) {
		c.mana -= 50;
	}

}

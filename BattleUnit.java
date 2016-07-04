import java.awt.Image;

public class BattleUnit {
	// basic stuff
	int hp;
	int mana;
	int atk;
	int matk;
	int spd;
	int KO = 0;
	private Cooldown cd;
	public String options, target;
	public boolean atking;
	public boolean move;
	public int animCount,animCount2;
	public int check;
	boolean hit;
	public int damageTaken;
	public int magicUsing;
	public Image sprite;
	public int x,y,l,w;
	public int maxHP;
	public int maxMP;
	boolean healed;
	
	//The parent class to all units in my game, the Battle unit takes care of most fields that I thought a unit would need
	//The Battle unit class also takes care of animation.
	public BattleUnit(int cooldown, int atk, int hp,int maxHP, int KO, int spd, int mana,int maxMP,
			int matk, String options, String target, boolean atking,
			int animCount,int animCount2, boolean move,int check,boolean hit,boolean healed,int damageTaken,int magicUsing) {
		this.check = check;
		this.options = options;
		this.atking = atking;
		this.atk = atk;
		this.hp = hp;
		this.KO = KO;
		this.hit = hit;
		this.spd = spd;
		this.animCount = animCount;
		cd = new Cooldown(cooldown);
	}
	public Cooldown getCd() {
		return cd;
	}
	//Animcount is a timer that counts until the deed is done
	//if it hits its limit then it will reset and tell the action
	//to stop animating
	public boolean wait(int time) {
		if (animCount <= time) {

			animCount++;
			return false;
		}
		animCount = 0;
		return true;

	}
	//animCount2 does the same thing except it allows for a sort of 
	//second layer of animation
	public boolean wait2(int time) {

		if (animCount2 <= time) {

			animCount2++;
			return false;
		}
		animCount2 = 0;
		return true;

	}
	
}

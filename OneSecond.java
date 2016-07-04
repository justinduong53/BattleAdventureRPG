

public class OneSecond {
	
	static int cnt = 0;
	
	public OneSecond(){
		
	}
	public static void update(){
		cnt++;
		if(cnt> 66){
			cnt = 0;
		}
	}
	public static boolean giveSecond(){
		if(cnt==65){

			return true;
		}
		return false;
	}
	public static boolean animation(){
		if(cnt==10){

			return true;
		}
		return false;
	}
	public static boolean animationSecond(){
		if(cnt>33){

			return true;
		}
		return false;
	}
}

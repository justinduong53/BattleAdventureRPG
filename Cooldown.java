public class Cooldown {
	int cnt, maxTime;
	boolean stop = false;
	boolean once = false;
	public int getCnt() {
		return cnt;
	}

	public Cooldown(int maxTime) {
		cnt = 0;
		
		this.maxTime = maxTime;
	}
	
	// Returns false if not done, true if complete
	public boolean updateAndCheck() {
		
		if (cnt > maxTime && once == false) {
			once = true;
			return true;
			
		}
		
		else{
			if(cnt > maxTime){
				cnt = maxTime;
			
			
			}
			else{
				if (stop == false){
					cnt++;
				}
			}
			return false;
		}
		
	}

	// Resets the cooldown
	public void reset() {
		once = false;
		cnt = 0;
	}

	// Returns percentage of cooldown progress
	public double getProgress() {
		return cnt / ((double) maxTime);
	}
}

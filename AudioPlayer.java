import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer {
	Clip clip;
	public AudioPlayer(String file){
		try{
			AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(file));
			clip = AudioSystem.getClip();
			clip.open(ais);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void play(){
		
		if(!clip.isRunning()){
			clip.setFramePosition(0);
			clip.start();
		}
		
	}
	public void stop(){
		if(clip.isRunning()){
			clip.stop();
		}
	}
	public void loop(){
		if(!clip.isRunning()){
			clip.setFramePosition(0);
			clip.loop(999);
		}
	}
}

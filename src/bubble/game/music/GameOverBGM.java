package bubble.game.music;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class GameOverBGM {
    public GameOverBGM(){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sound/GameOver.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            

            gainControl.setValue(-30.0f);
            
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
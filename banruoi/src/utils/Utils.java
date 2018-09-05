package utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Utils {

    static public Clip loadAudio(String path) {
        File soundFile = new File(path);
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            return null;
        }
    }
}

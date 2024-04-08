package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[5];

    public Sound(){
        soundURL[0] = getClass().getResource("/sound/game-music.wav");
        soundURL[1] = getClass().getResource("/sound/walk.wav");
        soundURL[2] = getClass().getResource("/sound/open-door.wav");
        soundURL[3] = getClass().getResource("/sound/key-grab.wav");
        soundURL[4] = getClass().getResource("/sound/drink.wav");
    }
    public void setFile(int i){

        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void play(){

        clip.start();
    }
    public void loop(){

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){

        clip.stop();
    }
    public void setVolume(float volume) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }
}

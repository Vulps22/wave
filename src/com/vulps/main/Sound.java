package com.vulps.main;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Sound {
    private Clip clip;
    private boolean looping;
    private FloatControl volumeControl;


    public Sound(String musicFilePath) {
        try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(musicFilePath).getAbsoluteFile());
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public Sound(String musicFilePath, float volume) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(musicFilePath).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            setVolume(volume);


        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null && !clip.isRunning()) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void loop() {
        System.out.println("Will start looping");
        if (clip != null && !looping) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            looping = true;
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.setFramePosition(0);
            looping = false;
        }
    }

    public boolean isLooping(){
        return looping;
    }

    public void setVolume(float volume) {
        if (volumeControl != null) {
            if (volume < volumeControl.getMinimum()) {
                volume = volumeControl.getMinimum();
            } else if (volume > volumeControl.getMaximum()) {
                volume = volumeControl.getMaximum();
            }
            volumeControl.setValue(volume);
        }
    }
}


package com.vulps.main;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Sound {
    private Clip clip;
    private boolean looping;
    private FloatControl volumeControl;
    private float volume;


    public Sound(String musicFilePath) {
        try {
            URL resourceUrl = Sound.class.getResource(musicFilePath);
            if (resourceUrl != null) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(resourceUrl);
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                setVolume(100);
            } else {
                System.err.println("Resource not found: " + musicFilePath);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public Sound(String musicFilePath, float volume) {
        try {
            URL resourceUrl = getClass().getClassLoader().getResource("resources/soundtrack_through_prism.wav") ;
            if (resourceUrl != null) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(resourceUrl);
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                setVolume(volume);
           } else {
                System.err.println("Resource not found: " + musicFilePath);
            }
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
        if (volumeControl == null) return;

        if (volume < volumeControl.getMinimum()) {
            this.volume = volumeControl.getMinimum();
        } else this.volume = Math.min(volume, volumeControl.getMaximum());

        volumeControl.setValue(this.volume);
    }
}


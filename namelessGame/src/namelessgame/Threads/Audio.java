/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package namelessgame.Threads;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Classe responsável pelo áudio do jogo.
 * @author Henrique Barcia Lang
 */
public class Audio extends Thread {
    
    private String audioName;
    private boolean isMusic;
    private Clip clip;
    
    public Audio(String audioName) {
        this(audioName, false);
    }

    public Audio(String audioName, boolean isMusic) {
        this.isMusic = isMusic;
        
        this.audioName = "/namelessgame/audio/" + (this.isMusic ? "musics" : "sounds") + "/" + audioName + ".wav";
    }
    
    public void stopAudio()
    {
        if(clip != null && clip.isRunning())
            clip.stop();
    }

    @Override
    public void run()
    {
        AudioInputStream inputStream = null;
        
        try {
            inputStream = AudioSystem.getAudioInputStream(getClass().getResource(audioName));
            
            clip = AudioSystem.getClip();
            clip.open(inputStream);
            
            if(isMusic)
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            else
                clip.start();
            
            Thread.sleep(10000);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
            System.out.println("Error when playing an audio... " + ex.getMessage());
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                System.out.println("Error when closing an audio... " + ex.getMessage());
            }
        }
    }
}


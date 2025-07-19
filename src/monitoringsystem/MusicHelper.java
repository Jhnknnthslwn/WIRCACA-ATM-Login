/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoringsystem;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author mel
 */
public class MusicHelper {
    
    static AudioInputStream ais;
    static Clip c;
    
    static AudioInputStream ad;
    static Clip cl;
    static long cliptiimePos;
    
  
    
    public static void main(String[] args) {
        
    }
    
    public static void popUpLoop(){
        c.loop(Clip.LOOP_CONTINUOUSLY);
        
    }
    public static void popUpMusic() throws IOException{
        if(c !=null && c.isRunning()){
            c.stop();
            ais.close();
        }
    }
    public static void resume(){
        cl.setMicrosecondPosition(cliptiimePos);
        cl.start();
    }
    public static void pause(){
        cliptiimePos = cl.getMicrosecondPosition();
        cl.stop();
    }

    public static void bgStop() throws IOException{
        
        // stop sa Background Music 1
        if(cl !=null && cl.isRunning()){
            cl.stop();
            
        }
        //stop sa Popout
        if(c !=null && c.isRunning()){
            c.stop();
        }
        
    }
    public static void playbg1(URL soundFile) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        
        ad = AudioSystem.getAudioInputStream(soundFile);
        cl = AudioSystem.getClip();
        cl.open(ad);

        cl.start();
        cl.loop(Clip.LOOP_CONTINUOUSLY);
        
    }
    public static void errorBg(URL soundFile) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        ais = AudioSystem.getAudioInputStream(soundFile);
        c = AudioSystem.getClip();
        c.open(ais);

        c.start();
    }
    public static void warningBg(URL soundFile) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        ais = AudioSystem.getAudioInputStream(soundFile);
        c = AudioSystem.getClip();
        c.open(ais);

        c.start();
    }
    public static void logoutBg(URL soundFile) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        ais = AudioSystem.getAudioInputStream(soundFile);
        c = AudioSystem.getClip();
        c.open(ais);

        c.start();
    }
    public static void intruderBg(URL soundFile) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        ais = AudioSystem.getAudioInputStream(soundFile);
        c = AudioSystem.getClip();
        c.open(ais);

        c.start();
        c.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public static void attmptBg(URL soundFile) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        ais = AudioSystem.getAudioInputStream(soundFile);
        c = AudioSystem.getClip();
        c.open(ais);

        c.start();
    }
    public static void successBg(URL soundFile) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        ais = AudioSystem.getAudioInputStream(soundFile);
        c = AudioSystem.getClip();
        c.open(ais);

        c.start();
    }
    public static void abouTbg(URL soundFile) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        ad = AudioSystem.getAudioInputStream(soundFile);
        cl = AudioSystem.getClip();
        cl.open(ad);

        cl.start();
    }
}

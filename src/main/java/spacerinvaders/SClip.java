package spacerinvaders;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SClip {

    public Clip audioClip;
    private AudioInputStream audioStream;

    // clip method
    public SClip(URL url) {
        // create an AudioInputStream from a given sound file
        try {
            File audioFile = new File(url.toURI());
            audioStream = AudioSystem.getAudioInputStream(audioFile);
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }catch(URISyntaxException e){
            
        }

        // acquire audio format and create a DataLine.Info object
        AudioFormat format = audioStream.getFormat();
        var info = new DataLine.Info(Clip.class, format);

        // obtain the Clip
        try {
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    

    public void play() {
        new Thread(() -> {
            audioClip.setFramePosition(0);
            audioClip.start();

        }) {
        }.start();
    }

    public void loop() {
        new Thread(() -> {
            audioClip.setFramePosition(0);
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        }) {
        }.start();
    }

    public void stop() {
        audioClip.stop();
    }

    public void volume(float volume) {
        FloatControl volumes = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
        volumes.setValue(volume);
    }
}
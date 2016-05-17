/**
 * Created by gustavbodestad on 2016-05-13.
 */
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.midi.SysexMessage;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 * The class is a controller class, which handles most of the applications logic.
 */
public class Controller {
        private GUIFrame frame;
        private JFileChooser chooser;
        private String currentSong;

        private Thread clockTh;
        private Thread displTh;

        private boolean clockSuspended;
        private boolean displaySuspended;
        private boolean playing;

        private AudioStream stream;

    /**
     * Constructor
     */
    public Controller() {
        playing = false;
        clockSuspended = false;
        displaySuspended = false;
    }

    /**
     * Sets a reference to the GUI.
     * @param inFrame
     */
    public void setFrame(GUIFrame inFrame) {
        frame = inFrame;
    }

    /**
     * Starts the music.
     */
    public void startMusic() {
        if (playing == false) {
            InputStream in = null;
            stream = null;

            try {
                in = new FileInputStream(currentSong);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                stream = new AudioStream(in);
            } catch (IOException e) {
                e.printStackTrace();
            }

            playing = true;
            AudioPlayer.player.start(stream);
        }
    }

    /**
     * Interrupts music.
     */
    public void stopMusic() {
        AudioPlayer.player.stop(stream);
        playing = false;
    }

    /**
     * Changes the position of the name in the DisplayPanel.
     */
    public void changeDisplay() {
        frame.changeNamePosition();
    }

    /**
     * Displays the time in the ClockPanel.
     */
    public void displayClock() {

        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        Date now = new Date();

        String s = df.format(now);
        System.out.print(s);
        frame.displayTime(s);

    }

    /**
     * Interrupts clockthread.
     */
    public void stopClockThread() {
            clockTh.suspend();
            clockSuspended = true;

    }

    /**
     * Starts clockthread.
     */
    public void startClockThread() {
        if(clockTh == null) {
            clockTh = new Thread(new ClockThread(this));
            clockTh.start();
        } else if (clockSuspended == true) {
            clockTh.resume();
            clockSuspended = false;
        }
    }

    /**
     * Starts displaythread.
     */
    public void startDisplayThread() {
        if(displTh == null) {
            displTh = new Thread(new DisplayThread(this));
            displTh.start();
        } else if (displaySuspended == true) {
            displTh.resume();
            displaySuspended = false;
        }
    }

    /**
     * Stops displaythread.
     */
    public void stopDisplayThread() {
        displTh.suspend();
        displaySuspended = true;
    }

    /**
     * Opens a filechooser to the music.
     * @return
     */
    public String openFile() {
        chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "mp3 & wav Images", "wav", "mp3");
        chooser.setFileFilter(filter);
        chooser.setCurrentDirectory(new File("/Musik"));

        int result = chooser.showOpenDialog(new JFrame());

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            currentSong = selectedFile.getAbsolutePath();
            return(selectedFile.getAbsolutePath());
        }
        return null;

    }
}

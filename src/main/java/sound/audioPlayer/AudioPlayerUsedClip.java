package sound.audioPlayer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * This is an example program that demonstrates how to play back an audio file
 * using the Clip in Java Sound API.
 *
 */

public class AudioPlayerUsedClip implements LineListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(AudioPlayerUsedClip.class);

    /**
     * this flag indicates whether the playback completes or not.
     */
    boolean playCompleted;

    /**
     * Play soundEffect given audio file.
     *
     * @param audioFilePath Path of the audio file.
     */
    void play(String audioFilePath) {
        File audioFile = new File(audioFilePath);

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            AudioFormat format = audioStream.getFormat();

            DataLine.Info info = new DataLine.Info(Clip.class, format);

            Clip audioClip = (Clip) AudioSystem.getLine(info);

            audioClip.addLineListener(this);

            audioClip.open(audioStream);

            int frameLength = audioClip.getFrameLength();
            LOGGER.info("Frame length = " + frameLength);

            long duration = audioClip.getMicrosecondLength();
            LOGGER.info("Duration = " + (duration / 1000000) + " sec");

            /**To specify the position to start playing back:*/
            audioClip.setMicrosecondPosition(1000000); // start playing from the 1st second
            audioClip.setFramePosition(80000); // start playing from the 80,000th frame
            /**To loop playing all the sound for 2 times:*/
            audioClip.loop(2);/**loop 2 times (total play 3 times)*/

            /**To stop playing back at the current position:
                audioClip.stop();
             */

            audioClip.start();

            LOGGER.info("Wait for playback completes");
            while (!playCompleted) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            /**
            To stop playing back at the current position:
            audioClip.stop();
            To resume playing, call start() method again.
            */

            audioClip.close();

        } catch (UnsupportedAudioFileException ex) {
            LOGGER.error("The specified audio file is not supported.", ex.getMessage(), ex);
        } catch (LineUnavailableException ex) {
            LOGGER.error("Audio line for playing back is unavailable.", ex.getMessage(), ex);
        } catch (IOException ex) {
            LOGGER.error("Error playing the audio file.", ex.getMessage(), ex);
        }

    }

    /**
     * Listens to the START and STOP events of the audio line.
     */
    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();

        if (type == LineEvent.Type.START) {
            LOGGER.info("Playback started.");

        } else if (type == LineEvent.Type.STOP) {
            playCompleted = true;
            LOGGER.info("Playback completed.");
        }

    }

    public static void main(String[] args) {
//        String audioFilePath = "E:/Test/Audio.wav";
//        String audioFilePath = "D:/Kalimba.mp3";
        String audioFilePath = "D:/nature.wav";
        AudioPlayerUsedClip player = new AudioPlayerUsedClip();
        player.play(audioFilePath);
    }
}

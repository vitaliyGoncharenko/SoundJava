package sound.soundEffect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Goncharenko on 30.09.2015.
 */
public class SoundEffectDemo extends JFrame {
    private static final Logger LOGGER = LoggerFactory.getLogger(SoundEffectDemo.class);

    // Constructor
    public SoundEffectDemo() {
        // Pre-load all the sound files
        SoundEffect.init();
        SoundEffect.volume = SoundEffect.Volume.LOW;  // un-mute

        // Set up UI components
        Container cp = this.getContentPane();
        cp.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnSound1 = new JButton("Cat");
        btnSound1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LOGGER.info("play sound Cat");
                SoundEffect.CAT.play();
            }
        });
        cp.add(btnSound1);
        JButton btnSound2 = new JButton("Nature");
        btnSound2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LOGGER.info("play sound Nature");
                SoundEffect.NATURE.play();
            }
        });
        cp.add(btnSound2);
        JButton btnSound3 = new JButton("Leopard");
        btnSound3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LOGGER.info("play sound Leopard");
                SoundEffect.LEOPARD.play();
            }
        });
        cp.add(btnSound3);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Test SoundEffect");
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new SoundEffectDemo();
    }
}

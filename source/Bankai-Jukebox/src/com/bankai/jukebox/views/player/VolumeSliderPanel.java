package com.bankai.jukebox.views.player;

import com.bankai.jukebox.controllers.AudioController;
import uk.co.caprica.vlcj.player.base.MediaPlayer;

import javax.swing.*;
import java.awt.*;

public class VolumeSliderPanel extends JPanel {

    private final JSlider slider;

    public VolumeSliderPanel(MediaPlayer mediaPlayer){

        // Initialize AudioController with the provided MediaPlayer
        AudioController audioController = new AudioController(mediaPlayer);

        // Set default volume
        int defaultVolume = 50;
        audioController.changeVolume(defaultVolume);

        // Initialize slider
        slider = createSlider();

        // Add change listener to the slider to change volume when slider value changes
        slider.addChangeListener(e -> audioController.changeVolume((float)slider.getValue()));
        add(slider);
    }

    // Method to create and configure the slider
    private JSlider createSlider() {
        JSlider slider = new JSlider();
        slider.setMinimum(0);
        slider.setMaximum(100);
        slider.setToolTipText("Volume");
        slider.setPreferredSize(new Dimension(130, 25));
        return slider;
    }
}

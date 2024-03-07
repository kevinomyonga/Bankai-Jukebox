package com.bankai.jukebox.views.player;

import com.bankai.jukebox.config.Icons;
import com.bankai.jukebox.controllers.AudioController;
import uk.co.caprica.vlcj.player.base.MediaPlayer;

import javax.swing.*;
import java.awt.*;

public class VolumeSliderPanel extends JPanel {

    private JButton lyricsBtn;
    private JLabel volumeIcon;
    private final JSlider slider;

    public VolumeSliderPanel(MediaPlayer mediaPlayer){

        this.setLayout(new FlowLayout());

        Icons icons = new Icons();

        lyricsBtn = new JButton(icons.getLyricsIcon());
        lyricsBtn.addActionListener(e -> showLyrics());
        add(lyricsBtn);

        volumeIcon = new JLabel();
        volumeIcon.setIcon(icons.getVolumeIcon());
        add(volumeIcon);

        // Initialize AudioController with the provided MediaPlayer
        AudioController audioController = new AudioController(mediaPlayer);

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
        slider.setMaximum(200);
        slider.setValue(100);
        slider.setToolTipText("Volume");
        slider.setPreferredSize(new Dimension(130, 25));
        return slider;
    }

    private void showLyrics() {
    }
}

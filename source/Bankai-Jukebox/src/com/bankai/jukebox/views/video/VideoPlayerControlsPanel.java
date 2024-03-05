package com.bankai.jukebox.views.video;

import com.bankai.jukebox.views.player.Icons;
import uk.co.caprica.vlcj.player.component.CallbackMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class VideoPlayerControlsPanel extends JPanel {

    private CallbackMediaPlayerComponent mediaPlayerComponent;

    public VideoPlayerControlsPanel(CallbackMediaPlayerComponent mediaPlayerComponent) {
        this.mediaPlayerComponent = mediaPlayerComponent;

        Icons icons = new Icons();

        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        setBackground(new Color(0, 0, 0, 100)); // Translucent background
        setVisible(false); // Initially hidden

        JButton rewindButton = new JButton("Rewind");
        rewindButton.addActionListener(e -> mediaPlayerComponent.mediaPlayer().controls().skipTime(-10000)); // Rewind 10 seconds
        add(rewindButton);

        // Add control buttons to the controls panel
        JButton playPauseButton = new JButton(!mediaPlayerComponent.mediaPlayer().status().isPlaying() ?
                icons.getPlayIcon() : icons.getPauseIcon());
        playPauseButton.addActionListener(e -> {
                if (!mediaPlayerComponent.mediaPlayer().status().isPlaying()) {
                    playPauseButton.setIcon(icons.getPauseIcon());
                    mediaPlayerComponent.mediaPlayer().controls().play();
                } else {
                    playPauseButton.setIcon(icons.getPlayIcon());
                    mediaPlayerComponent.mediaPlayer().controls().pause();
                }
        });
        add(playPauseButton);

        JButton forwardButton = new JButton("Forward");
        forwardButton.addActionListener(e -> mediaPlayerComponent.mediaPlayer().controls().skipTime(10000)); // Forward 10 seconds
        add(forwardButton);

        setVisible(true);
    }

//    private void updateProgressBarAndTimeLabels() {
//        long totalTime = mediaPlayerComponent.mediaPlayer().status().length();
//        long currentTime = mediaPlayerComponent.mediaPlayer().status().length();
//
//        progressBar.setMaximum((int) totalTime);
//        progressBar.setValue((int) currentTime);
//
//        currentTimeLabel.setText(formatTime(currentTime));
//        totalTimeLabel.setText(formatTime(totalTime));
//    }
}

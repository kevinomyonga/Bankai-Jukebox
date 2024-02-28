package com.bankai.jukebox.views.video;

import uk.co.caprica.vlcj.player.component.CallbackMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.bankai.jukebox.config.time.Time.formatTime;

public class VideoPlayerControlsPanel extends JPanel {

    private final CallbackMediaPlayerComponent mediaPlayerComponent;

    public VideoPlayerControlsPanel(CallbackMediaPlayerComponent mediaPlayerComponent) {
        this.mediaPlayerComponent = mediaPlayerComponent;

        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        setBackground(new Color(0, 0, 0, 100)); // Translucent background
        setVisible(false); // Initially hidden

        // Add control buttons to the controls panel
        JButton playButton = new JButton("Play");
        playButton.addActionListener(e -> mediaPlayerComponent.mediaPlayer().controls().play());
        add(playButton);

        JButton pauseButton = new JButton("Pause");
        pauseButton.addActionListener(e -> mediaPlayerComponent.mediaPlayer().controls().pause());
        add(pauseButton);

        JButton rewindButton = new JButton("Rewind");
        rewindButton.addActionListener(e -> mediaPlayerComponent.mediaPlayer().controls().skipTime(-10000)); // Rewind 10 seconds
        add(rewindButton);

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

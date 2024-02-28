package com.bankai.jukebox.views.video;

import uk.co.caprica.vlcj.player.component.CallbackMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

public class VideoPlayer extends JFrame {

    private final CallbackMediaPlayerComponent mediaPlayerComponent;
//    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;

    // An Arraylist of videos that are playable, switch between them by queueIndex;
    private ArrayList<File> videoQueue;

    private JPanel container;

    private VideoPlayerControlsPanel videoPlayerControlsPanel;

    public VideoPlayer(CallbackMediaPlayerComponent mediaPlayerComponent, ArrayList<File> videoQueue) {
        super();

        this.mediaPlayerComponent = mediaPlayerComponent;

        this.videoQueue = videoQueue;

        initializeWindow();
        addComponents();
        setWindowProperties();

        // Start the playback
        playVideo();
    }

    // Method to initialize window properties
    private void initializeWindow() {
        this.setBounds(100, 100, 600, 400);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Stop audio as soon as possible
                mediaPlayerComponent.mediaPlayer().controls().stop();
                // Release
//                mediaPlayerComponent.release();
            }
        });

        // Set preferred size for the JFrame
        Dimension appWindowDimension = new Dimension(
                600,
                400
        );
        this.setPreferredSize(appWindowDimension);
        this.setMinimumSize(appWindowDimension);
    }

    // Method to add components to the JFrame
    private void addComponents() {
        container = new JPanel();
        container.setLayout(new BorderLayout());
        container.add(mediaPlayerComponent, BorderLayout.CENTER);

        videoPlayerControlsPanel = new VideoPlayerControlsPanel(mediaPlayerComponent);

        container.add(videoPlayerControlsPanel, BorderLayout.SOUTH);

        this.setContentPane(container);
    }

    // Method to set JFrame properties
    private void setWindowProperties() {
        this.setLocationRelativeTo(null); // Center the JFrame on the screen
        this.pack(); // Pack components within the JFrame
        this.setVisible(true); // Make the JFrame visible
    }

    public void loadVideo(String path) {
        System.out.println("Video file path: " + path);
        mediaPlayerComponent.mediaPlayer().media().startPaused(path);
    }

    public void playVideo() {
        if (!videoQueue.isEmpty()) {
            setTitle(videoQueue.getFirst().getName());
            // Load video
            loadVideo(videoQueue.getFirst().getAbsolutePath());

            // Play
            mediaPlayerComponent.mediaPlayer().controls().play();
        }
    }
}

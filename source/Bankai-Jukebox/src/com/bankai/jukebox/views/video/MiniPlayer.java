package com.bankai.jukebox.views.video;

import com.bankai.jukebox.config.Animations;
import com.bankai.jukebox.config.Icons;
import com.bankai.jukebox.views.player.PlayerPanel;
import uk.co.caprica.vlcj.player.component.CallbackMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class MiniPlayer extends JFrame {

    private final PlayerPanel playerPanel;

    private JPanel container, videoContent;

    private JLabel lblAni;

    private MiniPlayerControlsPanel miniPlayerControlsPanel;

    public MiniPlayer(PlayerPanel playerPanel) {
        super();

        this.playerPanel = playerPanel;

        initializeWindow();
        addComponents();
        setWindowProperties();

        // Start the playback
        playMedia();
    }

    // Method to initialize window properties
    private void initializeWindow() {
        this.setBounds(100, 100, 600, 400);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Pause audio
                playerPanel.getMediaPlayerComponent().mediaPlayer().controls().pause();

                // Close/Hide window
                setVisible(false);
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

        videoContent = new JPanel();
        videoContent.setLayout(new BorderLayout());
        videoContent.add(playerPanel.getMediaPlayerComponent(), BorderLayout.CENTER);

        container.add(videoContent, BorderLayout.CENTER);
        videoContent.setVisible(true);

//        lblAni=new JLabel(new Icons().getAppLogoIcon());
//        container.add(lblAni, BorderLayout.CENTER);
//        lblAni.setVisible(false);
//        setVisual();

        miniPlayerControlsPanel = new MiniPlayerControlsPanel(playerPanel);

        container.add(miniPlayerControlsPanel, BorderLayout.SOUTH);

        this.setContentPane(container);
    }

    // Method to set JFrame properties
    private void setWindowProperties() {
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // Prevent default close operation
        this.setLocationRelativeTo(null); // Center the JFrame on the screen
        this.pack(); // Pack components within the JFrame
        this.setVisible(false); // Make the JFrame invisible
    }

    public void loadVideo(String path) {
        System.out.println("Video file path: " + path);
        playerPanel.getMediaPlayerComponent().mediaPlayer().media().startPaused(path);
    }

    public void playMedia() {
        if (!playerPanel.getMediaPlayerComponent().mediaPlayer().status().isPlaying()) {
            // Play
            playerPanel.getMediaPlayerComponent().mediaPlayer().controls().play();
        }
    }

    public void setWindowVisible(boolean isVisible) {
        this.setVisible(isVisible);
    }

    public void setWindowTitle(String title) {
        this.setTitle(title);
    }

    public MiniPlayerControlsPanel getMiniPlayerControlsPanel() {
        return miniPlayerControlsPanel;
    }

    public void setVisual()
    {
        // Create an instance of the Random class
        Random random = new Random();

        // Generate a random number between 0 and 6 (inclusive)
        int randomNumber = random.nextInt(7);

        Animations animations = new Animations();

        switch(randomNumber)
        {
            case 0:
                lblAni.setIcon(animations.getIconAni0());
                break;

            case 1:
                lblAni.setIcon(animations.getIconAni1());
                break;

            case 2:
                lblAni.setIcon(animations.getIconAni2());
                break;

            case 3:
                lblAni.setIcon(animations.getIconAni3());
                break;

            case 4:
                lblAni.setIcon(animations.getIconAni4());
                break;

            case 5:
                lblAni.setIcon(animations.getIconAni5());
                break;

            case 6:
                lblAni.setIcon(animations.getIconAni6());
                break;

            default:
                lblAni.setIcon(animations.getIconAni0());
                break;
        }
    }
}

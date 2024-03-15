package com.bankai.jukebox.pages;

import com.bankai.jukebox.config.Constants;
import com.bankai.jukebox.utils.database.DatabaseHandler;
import com.bankai.jukebox.views.forms.SignInPanel;
import com.bankai.jukebox.views.forms.SignUpPanel;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.component.CallbackMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;

public class LoginPage extends JFrame {

    public DatabaseHandler databaseHandler;

    private JPanel container;

    private CallbackMediaPlayerComponent mediaPlayerComponent;

    public LoginPage(DatabaseHandler databaseHandler) {
        super(Constants.APP_NAME); // Set the title of the JFrame

        this.databaseHandler = databaseHandler;

        initializeWindow(); // Initialize window properties
        addComponents(); // Add components to the JFrame
        setWindowProperties(); // Set JFrame properties

        play();
    }

    // Method to initialize window properties
    private void initializeWindow() {

        this.setLayout(new BorderLayout()); // Set layout manager for the JFrame

        // Set preferred size for the JFrame
        Dimension appWindowDimension = new Dimension(
                Constants.APP_WINDOW_PREFERRED_WIDTH,
                Constants.APP_WINDOW_PREFERRED_HEIGHT
        );
        this.setPreferredSize(appWindowDimension);
        this.setMinimumSize(appWindowDimension);
        this.setMaximumSize(appWindowDimension);

        mediaPlayerComponent = new CallbackMediaPlayerComponent();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stop();
            }
        });
    }

    // Method to add components to the JFrame
    private void addComponents() {
        container = new JPanel();
        container.setLayout(new BorderLayout());
        container.add(mediaPlayerComponent, BorderLayout.CENTER);

        this.setContentPane(container);

//        Canvas canvas = new Canvas();
//        mediaPlayerComponent.mediaPlayer().videoSurface().set(
//                mediaPlayerComponent.mediaPlayerFactory().videoSurfaces().newVideoSurface(canvas));
        mediaPlayerComponent.mediaPlayer().events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            @Override
            public void timeChanged(MediaPlayer mediaPlayer, long newTime) {
                if (newTime >= mediaPlayer.status().length() - 1000) {
                    mediaPlayer.controls().setPosition(0);
                }
            }
        });

        SignInPanel signInPanel = new SignInPanel(databaseHandler);
        this.add(signInPanel, BorderLayout.WEST);

        SignUpPanel signUpPanel = new SignUpPanel(databaseHandler);
        this.add(signUpPanel, BorderLayout.EAST);
    }

    // Method to set JFrame properties
    private void setWindowProperties() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent default close operation
        this.setLocationRelativeTo(null); // Center the JFrame on the screen
        this.setResizable(false); // Disable resizing of the JFrame
        this.pack(); // Pack components within the JFrame
        this.setVisible(true); // Make the JFrame visible
    }

    public void play() {
        if (mediaPlayerComponent.mediaPlayer().status().isPlaying()) {
            mediaPlayerComponent.mediaPlayer().controls().stop();
        }
//        mediaPlayerComponent.mediaPlayer().media().play(
//                new File(Objects.requireNonNull(LoginPage.class.getClassLoader()
//                        .getResource("videos/video-background.mp4")).getFile()).getAbsolutePath());

        URL resourceUrl = LoginPage.class.getClassLoader().getResource("videos/video-background.mp4");
        if (resourceUrl != null) {
            File file = new File(resourceUrl.getFile());
            String absolutePath = file.getAbsolutePath();
            System.out.println("Absolute path: " + absolutePath);

            // Load Video
            mediaPlayerComponent.mediaPlayer().media().startPaused(file.getAbsolutePath());
            // Play
            mediaPlayerComponent.mediaPlayer().controls().play();

        } else {
            System.out.println("Resource not found.");
        }
    }

    public void stop() {
        mediaPlayerComponent.mediaPlayer().controls().stop();
        mediaPlayerComponent.mediaPlayer().release();
        mediaPlayerComponent.mediaPlayerFactory().release();
    }
}

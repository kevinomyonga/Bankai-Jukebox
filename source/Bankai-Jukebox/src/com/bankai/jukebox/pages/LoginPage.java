package com.bankai.jukebox.pages;

import com.bankai.jukebox.config.Constants;
import com.bankai.jukebox.utils.database.DatabaseHandler;
import com.bankai.jukebox.views.forms.SignInPanel;
import com.bankai.jukebox.views.forms.SignUpPanel;
import com.bankai.jukebox.views.login.LoginOverlayPanel;
import com.bankai.jukebox.views.login.LoginVideoPanel;
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

//    private CallbackMediaPlayerComponent mediaPlayerComponent;
    private LoginVideoPanel loginVideoPanel;
    private LoginOverlayPanel loginOverlayPanel;

    public LoginPage(DatabaseHandler databaseHandler) {
        super(Constants.APP_NAME); // Set the title of the JFrame

        this.databaseHandler = databaseHandler;

        initializeWindow(); // Initialize window properties
        addComponents(); // Add components to the JFrame
        setWindowProperties(); // Set JFrame properties
    }

    // Method to initialize window properties
    private void initializeWindow() {

        this.setLayout(new OverlayLayout(getContentPane())); // Set layout manager for the JFrame

        // Set preferred size for the JFrame
        Dimension appWindowDimension = new Dimension(
                Constants.APP_WINDOW_PREFERRED_WIDTH,
                Constants.APP_WINDOW_PREFERRED_HEIGHT
        );
        this.setPreferredSize(appWindowDimension);
        this.setMinimumSize(appWindowDimension);
        this.setMaximumSize(appWindowDimension);
    }

    // Method to add components to the JFrame
    private void addComponents() {

        // Create a panel for the video background
        loginVideoPanel = new LoginVideoPanel();
        add(loginVideoPanel);

        // Create a panel for the login form
        loginOverlayPanel = new LoginOverlayPanel(databaseHandler, this);
        setGlassPane(loginOverlayPanel);
        loginOverlayPanel.setVisible(true);
//        add(loginOverlayPanel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                loginVideoPanel.play();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                loginVideoPanel.stop();
            }
        });
    }

    // Method to set JFrame properties
    private void setWindowProperties() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Prevent default close operation
        this.setLocationRelativeTo(null); // Center the JFrame on the screen
        this.setResizable(false); // Disable resizing of the JFrame
        this.pack(); // Pack components within the JFrame
        this.setVisible(true); // Make the JFrame visible
    }

    public void closeFrame(){
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}

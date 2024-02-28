package com.bankai.jukebox.pages;

import com.bankai.jukebox.config.Constants;
import com.bankai.jukebox.views.central.CentralPanel;
import com.bankai.jukebox.views.menu.MenuPanel;
import com.bankai.jukebox.views.player.PlayerPanel;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {

    public HomePage() {
        super(Constants.APP_NAME); // Set the title of the JFrame

        initializeWindow(); // Initialize window properties
        addComponents(); // Add components to the JFrame
        setWindowProperties(); // Set JFrame properties
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
    }

    // Method to add components to the JFrame
    private void addComponents() {
        // Initialize and add the PlayerPanel to the bottom of the JFrame
        PlayerPanel playerPanel = new PlayerPanel();
        this.add(playerPanel, BorderLayout.SOUTH);

        // Initialize and add the CentralPanel to the center of the JFrame
        CentralPanel centralPanel = new CentralPanel(playerPanel);
        this.add(centralPanel, BorderLayout.CENTER);

        // Initialize and add the MenuPanel to the left of the JFrame with a scroll pane
        MenuPanel menuPanel = new MenuPanel(centralPanel);
        JScrollPane jScrollPane = new JScrollPane(menuPanel);
        jScrollPane.setPreferredSize(new Dimension(250, 600));
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(jScrollPane, BorderLayout.WEST);
    }

    // Method to set JFrame properties
    private void setWindowProperties() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent default close operation
        this.setLocationRelativeTo(null); // Center the JFrame on the screen
        this.setResizable(false); // Disable resizing of the JFrame
        this.pack(); // Pack components within the JFrame
        this.setVisible(true); // Make the JFrame visible
    }

    public static void main(String[] args) {
        new HomePage();
    }
}

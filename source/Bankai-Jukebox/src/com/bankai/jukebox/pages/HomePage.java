package com.bankai.jukebox.pages;

import com.bankai.jukebox.config.Constants;
import com.bankai.jukebox.utils.database.DatabaseHandler;
import com.bankai.jukebox.views.central.CentralPanel;
import com.bankai.jukebox.views.friends.FriendsActivityPanelsManager;
import com.bankai.jukebox.views.menu.MenuPanel;
import com.bankai.jukebox.views.player.PlayerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class HomePage extends JFrame {
    public static DatabaseHandler databaseHandler;

    private JPanel rightMenuPanel;

    private int menuWidth = 200; // Adjust the width of the menu
    private boolean isMenuVisible = false;

    public HomePage(DatabaseHandler databaseHandler) {
        super(Constants.APP_NAME); // Set the title of the JFrame

        HomePage.databaseHandler = databaseHandler;

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
        centralPanel.showSongsPanel();
        this.add(centralPanel, BorderLayout.CENTER);

        // Initialize and add the MenuPanel to the left of the JFrame with a scroll pane
        MenuPanel menuPanel = new MenuPanel(centralPanel, this);
        JScrollPane jScrollPane = new JScrollPane(menuPanel);
        jScrollPane.setPreferredSize(new Dimension(250, 685));
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(jScrollPane, BorderLayout.WEST);

        // Initialize and add the CentralPanel to the right of the JFrame
        FriendsActivityPanelsManager friendsActivityPanelsManager = new FriendsActivityPanelsManager();
        friendsActivityPanelsManager.updateFriendsList();
        JScrollPane scrollPanel3 = new JScrollPane(friendsActivityPanelsManager, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPanel3.setPreferredSize(new Dimension(280, 700));
        this.add(scrollPanel3, BorderLayout.EAST);

//        rightMenuPanel = new JPanel();
//        rightMenuPanel.setBackground(Color.LIGHT_GRAY);
//        rightMenuPanel.setPreferredSize(new Dimension(menuWidth, getHeight()));
//        rightMenuPanel.setLayout(new BorderLayout());
//
//        JButton toggleButton = new JButton("Toggle Menu");
//        toggleButton.addActionListener(e -> toggleMenu());
//
//        rightMenuPanel.add(toggleButton, BorderLayout.NORTH);
//
//        add(rightMenuPanel, BorderLayout.EAST);
//
//        // Initial position of the menu
//        rightMenuPanel.setLocation(getWidth(), 0);
    }

    // Method to set JFrame properties
    private void setWindowProperties() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent default close operation
        this.setLocationRelativeTo(null); // Center the JFrame on the screen
        this.setResizable(false); // Disable resizing of the JFrame
        this.pack(); // Pack components within the JFrame
        this.setVisible(true); // Make the JFrame visible
    }

    public void toggleMenu() {
        int slideSpeed = 10; // Adjust the sliding speed
        int destinationX;

        if (isMenuVisible) {
            destinationX = getWidth();
            isMenuVisible = false;
        } else {
            destinationX = getWidth() - menuWidth;
            isMenuVisible = true;
        }

        Timer timer = new Timer(10, new ActionListener() {
            int currentX = rightMenuPanel.getX();

            public void actionPerformed(ActionEvent e) {
                if (isMenuVisible) {
                    if (currentX > destinationX) {
                        currentX -= slideSpeed;
                        rightMenuPanel.setLocation(currentX, 0);
                    } else {
                        ((Timer) e.getSource()).stop();
                    }
                } else {
                    if (currentX < destinationX) {
                        currentX += slideSpeed;
                        rightMenuPanel.setLocation(currentX, 0);
                    } else {
                        ((Timer) e.getSource()).stop();
                    }
                }
            }
        });
        timer.start();
    }

    public void closeFrame(){
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}

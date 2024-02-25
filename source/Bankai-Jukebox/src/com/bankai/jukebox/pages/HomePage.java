package com.bankai.jukebox.pages;

import com.bankai.jukebox.views.central.CentralPanel;
import com.bankai.jukebox.views.library.LibraryPanel;
import com.bankai.jukebox.views.player.PlayerPanel;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {

    private static final int WIDTH = 1280, HEIGHT = 800;

    private LibraryPanel libraryPanel;
    private PlayerPanel playerPanel;
    private CentralPanel centralPanel;

    public HomePage() {
        super();

        actionListeners();

        this.setTitle("Bankai Jukebox");
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        playerPanel = new PlayerPanel();
        this.add(playerPanel, BorderLayout.SOUTH);


        libraryPanel = new LibraryPanel();

        centralPanel = new CentralPanel();
        this.add(centralPanel, BorderLayout.CENTER);


//        library.setShowPanel(centralPanel.getShowPanel());
        JScrollPane jscrollPane = new JScrollPane(libraryPanel);
        jscrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(libraryPanel, BorderLayout.WEST);

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
        this.setResizable(false);
    }

    public void actionListeners() {}

    public static void main(String[] args) {
        new HomePage();
    }
}

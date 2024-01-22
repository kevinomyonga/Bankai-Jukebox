package com.bankai.jukebox.gui;

import javax.swing.*;

public class CreatePlaylist {
    private JPanel rootPanel;

    public CreatePlaylist() {
    }

    public void guiLaunch() {
        JFrame frame = new JFrame("Create Playlist");
        frame.setContentPane(new CreatePlaylist().rootPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public static void main(String[] args) {
        new CreatePlaylist().guiLaunch();
    }
}
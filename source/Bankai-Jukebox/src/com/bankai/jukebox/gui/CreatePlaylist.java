package com.bankai.jukebox.gui;

import javax.swing.*;

public class CreatePlaylist {
    private JPanel rootPanel;
    private JTextField txtTrackNumber;
    private JButton btnAddTrack;
    private JButton btnPlay;
    private JButton btnResetPlaylist;
    private JList list1;

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

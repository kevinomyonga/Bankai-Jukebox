package com.bankai.jukebox.gui;

import javax.swing.*;

public class Jukebox {

    private JPanel rootPanel;
    private JButton btnCheckLibrary;
    private JButton btnCreatePlaylist;
    private JButton btnUpdateLibrary;
    private JButton btnExit;
    private JLabel homeLabel;

    public Jukebox() {
        actionListeners();
    }

    public void actionListeners() {
        btnCheckLibrary.setFocusable(false);
        btnCheckLibrary.addActionListener(e -> {
            CheckLibrary checkLibrary = new CheckLibrary();
            checkLibrary.guiLaunch();
        });
        btnCreatePlaylist.setFocusable(false);
        btnCreatePlaylist.addActionListener(e -> {
            CreatePlaylist createPlaylist = new CreatePlaylist();
            createPlaylist.guiLaunch();
        });
        btnUpdateLibrary.setFocusable(false);
        btnUpdateLibrary.addActionListener(e -> {
            UpdateLibrary updateLibrary = new UpdateLibrary();
            updateLibrary.guiLaunch();
        });
        btnExit.setFocusable(false);
        btnExit.addActionListener(e -> {
            System.exit(0);
        });
    }

    public void guiLaunch() {
        JFrame frame = new JFrame("Jukebox");
        frame.setContentPane(new Jukebox().rootPanel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public static void main(String[] args) {
        new Jukebox().guiLaunch();
    }
}

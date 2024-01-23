package com.bankai.jukebox.gui;

import javax.swing.*;

public class UpdateLibrary {
    private JPanel rootPanel;
    private JTextField txtTrackNumber;
    private JTextField txtTrackRating;
    private JTable tableLibraryTracks;
    private JButton btnUpdate;

    public UpdateLibrary() {
    }

    public void guiLaunch() {
        JFrame frame = new JFrame("Update Library");
        frame.setContentPane(new UpdateLibrary().rootPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public static void main(String[] args) {
        new UpdateLibrary().guiLaunch();
    }
}

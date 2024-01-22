package com.bankai.jukebox.gui;

import javax.swing.*;

public class CheckLibrary {
    private JPanel rootPanel;

    public CheckLibrary() {
    }

    public void guiLaunch() {
        JFrame frame = new JFrame("Check Library");
        frame.setContentPane(new CheckLibrary().rootPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public static void main(String[] args) {
        new CheckLibrary().guiLaunch();
    }
}

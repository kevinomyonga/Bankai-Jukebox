package com.bankai.jukebox.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckLibrary {
    private JPanel rootPanel;
    private JTextField txtTrackNumber;
    private JButton btnCheckTrack;
    private JButton btnListAllTracks;
    private JList list1;

    public CheckLibrary() {
        txtTrackNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnCheckTrack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnListAllTracks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
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

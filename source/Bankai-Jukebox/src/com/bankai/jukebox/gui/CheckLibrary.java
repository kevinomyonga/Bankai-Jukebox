package com.bankai.jukebox.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CheckLibrary {
    private JPanel rootPanel; // Panel to hold all GUI components
    private JTextField txtTrackNumber; // Text field to input track number
    private JButton btnCheckTrack; // Button to check a specific track
    private JButton btnListAllTracks; // Button to list all tracks
    private JList list1; // List to display tracks

    public CheckLibrary() {
        // ActionListener for text field to handle user input
        txtTrackNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add functionality to handle user input in the text field
            }
        });

        // ActionListener for check track button
        btnCheckTrack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add functionality to check a specific track
            }
        });

        // ActionListener for list all tracks button
        btnListAllTracks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add functionality to list all tracks
            }
        });
    }

    // Method to launch the GUI
    public void guiLaunch() {
        JFrame frame = new JFrame("Check Library"); // Create a new frame with title
        frame.setContentPane(new CheckLibrary().rootPanel); // Set the content pane to the root panel
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set close operation
        frame.setLocationRelativeTo(null); // Set frame to appear in the center of the screen
        frame.pack(); // Pack components
        frame.setVisible(true); // Make frame visible
        frame.setResizable(false); // Disable frame resizing
    }

    public static void main(String[] args) {
        new CheckLibrary().guiLaunch(); // Create an instance of CheckLibrary and launch the GUI
    }
}

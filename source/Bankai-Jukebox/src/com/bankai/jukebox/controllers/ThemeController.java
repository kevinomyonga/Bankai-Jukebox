package com.bankai.jukebox.controllers;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;

public class ThemeController {

    public ThemeController() {}

    // Method to set the theme dynamically
    public void setTheme(String theme) {
        try {
            switch (theme) {
                case "light":
                    UIManager.setLookAndFeel(new FlatMacLightLaf());
                    break;
                case "dark":
                    UIManager.setLookAndFeel(new FlatMacDarkLaf());
                    break;
                case "system":
//                    UIManager.setLookAndFeel(new FlatSystemLaf());
                    break;
                default:
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    break;
            }
//            SwingUtilities.updateComponentTreeUI(frame);

            FlatLaf.updateUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

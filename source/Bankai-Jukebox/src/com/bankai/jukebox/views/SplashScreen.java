package com.bankai.jukebox.views;

import com.bankai.jukebox.config.Icons;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JWindow {

    private int duration;

    public SplashScreen(int duration) {
        this.duration = duration;
    }

    public void showSplash() {
        JPanel content = (JPanel) getContentPane();

        int width = 500;
        int height = 300;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);

        Icons icons = new Icons();
        JLabel label = new JLabel(icons.getAppLogoIcon());
        content.add(label, BorderLayout.CENTER);
        setVisible(true);

        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        setVisible(false);
    }
}

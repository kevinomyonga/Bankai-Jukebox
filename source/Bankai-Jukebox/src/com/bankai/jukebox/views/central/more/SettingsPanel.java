package com.bankai.jukebox.views.central.more;

import com.bankai.jukebox.config.Constants;
import com.bankai.jukebox.controllers.ThemeController;
import com.bankai.jukebox.views.TitleText;
import com.bankai.jukebox.views.central.library.VideosPanel;
import com.bankai.jukebox.views.player.PlayerPanel;
import com.bankai.jukebox.views.video.VideoPlayer;
import net.coobird.thumbnailator.Thumbnails;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class SettingsPanel extends JPanel {

    public SettingsPanel(PlayerPanel playerPanel) {
        super();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new TitleText("Settings"));

        add(new SettingsPanelContent(playerPanel));

        setVisible(true);
    }
}

class SettingsPanelContent extends JPanel {

    int WIDTH = 150, HEIGHT = 150;

    private final PlayerPanel playerPanel;

    private ArrayList<File> videoFiles;

    public SettingsPanelContent(PlayerPanel playerPanel) {
        super();

        this.setLayout(new GridLayout(0, 4, 20, 20));

        this.playerPanel = playerPanel;



        /*
          This is the theme change section
         */
        // Initialize AudioController with the provided MediaPlayer
        ThemeController themeController = new ThemeController();
        JButton lightButton = new JButton("Light Theme");
        lightButton.setFont(new Font("Arial", Font.BOLD, 12));
        lightButton.setHorizontalAlignment(SwingConstants.LEFT);
        JButton darkButton = new JButton("Dark Theme");
        lightButton.setFont(new Font("Arial", Font.BOLD, 12));
        lightButton.setHorizontalAlignment(SwingConstants.LEFT);
        JButton systemButton = new JButton("System Theme");
        lightButton.setFont(new Font("Arial", Font.BOLD, 12));
        lightButton.setHorizontalAlignment(SwingConstants.LEFT);

        // Add action listeners to buttons
        lightButton.addActionListener(e -> themeController.setTheme("light"));
        darkButton.addActionListener(e -> themeController.setTheme("dark"));
        systemButton.addActionListener(e -> themeController.setTheme("system"));

        // Add buttons to the panel
        add(lightButton);
        add(darkButton);
        add(systemButton);

        setVisible(true);
    }
}

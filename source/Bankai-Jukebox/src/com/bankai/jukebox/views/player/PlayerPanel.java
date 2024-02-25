package com.bankai.jukebox.views.player;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {

    private Color background;
    private  PlayerControlsPanel playerControlsPanel;
    private ProgressBarPanel progressBarPanel;
    private SongInfoPanel songInfoPanel;
    private VolumeSliderPanel volumeSliderPanel;

    public PlayerPanel() {
        super();

        background = new Color(40, 40, 40);
        this.setLayout(new BorderLayout());
        this.setBackground(background);

        playerControlsPanel = new PlayerControlsPanel();
        this.add(playerControlsPanel, BorderLayout.CENTER);

        progressBarPanel = new ProgressBarPanel();
        this.add(progressBarPanel, BorderLayout.SOUTH);
//        playerControlsPanel.setProgressBarPanel(progressBarPanel);

        songInfoPanel = new SongInfoPanel();
        this.add(songInfoPanel, BorderLayout.WEST);
//        playerControlsPanel.setSongInfoPanel(songInfoPanel);

        volumeSliderPanel = new VolumeSliderPanel();
        this.add(volumeSliderPanel, BorderLayout.EAST);
    }

    public PlayerControlsPanel getPlayerControlsPanel() {
        return playerControlsPanel;
    }
}

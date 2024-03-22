package com.bankai.jukebox.views.video;

import com.bankai.jukebox.config.Icons;
import com.bankai.jukebox.views.player.*;
import uk.co.caprica.vlcj.player.component.CallbackMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;

public class MiniPlayerControlsPanel extends JPanel {

    private PlayerPanel playerPanel;

    private PlayerControlsPanel playerControlsPanel;
    private PlayerRadioControlsPanel playerRadioControlsPanel;
    private ProgressBarPanel progressBarPanel;
    private VolumeSliderPanel volumeSliderPanel;
    private JPanel centerConsole;

    private boolean isRadio = false;

    public MiniPlayerControlsPanel(PlayerPanel playerPanel) {
        this.playerPanel = playerPanel;

        this.setPreferredSize(new Dimension(600, 80));

        Icons icons = new Icons();

        setLayout(new BorderLayout());

//        this.add(playerPanel.getCenterConsole(), BorderLayout.CENTER);
//
//        this.add(playerPanel.getVolumeSliderPanel(), BorderLayout.EAST);

        centerConsole = new JPanel();
        centerConsole.setPreferredSize(new Dimension(80,30));
        centerConsole.setLayout(new BorderLayout());

        playerControlsPanel = new PlayerControlsPanel(playerPanel.getPlayBackController());
        playerRadioControlsPanel = new PlayerRadioControlsPanel(playerPanel.getMediaPlayerComponent().mediaPlayer());

        progressBarPanel = new ProgressBarPanel(playerPanel.getPlayBackController());

        updateCenterConsole();

        this.add(centerConsole, BorderLayout.CENTER);

        this.add(centerConsole, BorderLayout.CENTER);

        volumeSliderPanel = new VolumeSliderPanel(playerPanel.getMediaPlayerComponent().mediaPlayer());
        this.add(volumeSliderPanel, BorderLayout.EAST);

//        JButton rewindButton = new JButton("Rewind");
//        rewindButton.addActionListener(e -> playerPanel.getMediaPlayerComponent().mediaPlayer().controls().skipTime(-10000)); // Rewind 10 seconds
//        add(rewindButton);
//
//        // Add control buttons to the controls panel
//        JButton playPauseButton = new JButton(!playerPanel.getMediaPlayerComponent().mediaPlayer().status().isPlaying() ?
//                icons.getPlayIcon() : icons.getPauseIcon());
//        playPauseButton.addActionListener(e -> {
//                if (!playerPanel.getMediaPlayerComponent().mediaPlayer().status().isPlaying()) {
//                    playPauseButton.setIcon(icons.getPauseIcon());
//                    playerPanel.getMediaPlayerComponent().mediaPlayer().controls().play();
//                } else {
//                    playPauseButton.setIcon(icons.getPlayIcon());
//                    playerPanel.getMediaPlayerComponent().mediaPlayer().controls().pause();
//                }
//        });
//        add(playPauseButton);
//
//        JButton forwardButton = new JButton("Forward");
//        forwardButton.addActionListener(e -> playerPanel.getMediaPlayerComponent().mediaPlayer().controls().skipTime(10000)); // Forward 10 seconds
//        add(forwardButton);

        setVisible(true);
    }

    public void updateCenterConsole() {
        if(!isRadio) {
            centerConsole.add(playerControlsPanel, BorderLayout.CENTER);
            centerConsole.add(progressBarPanel, BorderLayout.SOUTH);
            playerControlsPanel.setVisible(true);
            progressBarPanel.setVisible(true);
            playerRadioControlsPanel.setVisible(false);
        } else {
            centerConsole.add(playerRadioControlsPanel, BorderLayout.CENTER);
            playerControlsPanel.setVisible(false);
            progressBarPanel.setVisible(false);
            playerRadioControlsPanel.setVisible(true);
        }
    }

    public ProgressBarPanel getProgressBarPanel() {
        return progressBarPanel;
    }
}

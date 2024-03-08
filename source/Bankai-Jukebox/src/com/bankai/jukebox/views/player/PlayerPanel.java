package com.bankai.jukebox.views.player;

import com.bankai.jukebox.controllers.PlayBackController;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.component.CallbackMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {

    private CallbackMediaPlayerComponent mediaPlayerComponent;
    private PlayBackController playBackController;

    private Color background;
    private PlayerControlsPanel playerControlsPanel;
    private PlayerRadioControlsPanel playerRadioControlsPanel;
    private ProgressBarPanel progressBarPanel;
    private SongInfoPanel songInfoPanel;
    private VolumeSliderPanel volumeSliderPanel;
    private JPanel centerConsole;

    private boolean isRadio = false;

    public PlayerPanel() {
        super();

        mediaPlayerComponent = new CallbackMediaPlayerComponent();

        playBackController = new PlayBackController(mediaPlayerComponent.mediaPlayer(), null);

        background = new Color(40, 40, 40);
        this.setLayout(new BorderLayout());
        this.setBackground(background);

        centerConsole = new JPanel();
        centerConsole.setPreferredSize(new Dimension(80,30));
        centerConsole.setLayout(new BorderLayout());

        playerControlsPanel = new PlayerControlsPanel(playBackController);
        playerRadioControlsPanel = new PlayerRadioControlsPanel(mediaPlayerComponent.mediaPlayer());

        progressBarPanel = new ProgressBarPanel(mediaPlayerComponent.mediaPlayer());

        updateCenterConsole();

        this.add(centerConsole, BorderLayout.CENTER);

        songInfoPanel = new SongInfoPanel();
        this.add(songInfoPanel, BorderLayout.WEST);

        volumeSliderPanel = new VolumeSliderPanel(mediaPlayerComponent.mediaPlayer());
        this.add(volumeSliderPanel, BorderLayout.EAST);
    }

    public PlayBackController getPlayBackController() {
        return playBackController;
    }

    public PlayerControlsPanel getPlayerControlsPanel() {
        isRadio = false;
        updateCenterConsole();
        return playerControlsPanel;
    }

    public PlayerRadioControlsPanel getPlayerRadioControlsPanel() {
        isRadio = true;
        updateCenterConsole();
        return playerRadioControlsPanel;
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

    public MediaPlayerFactory getMediaPlayerFactory() {
        return mediaPlayerComponent.mediaPlayerFactory();
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayerComponent.mediaPlayer();
    }

    public CallbackMediaPlayerComponent getMediaPlayerComponent() {
        return mediaPlayerComponent;
    }
}

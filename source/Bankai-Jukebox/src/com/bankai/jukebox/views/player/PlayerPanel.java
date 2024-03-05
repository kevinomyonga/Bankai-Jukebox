package com.bankai.jukebox.views.player;

import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.component.CallbackMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {

    /**
     * Native media player factory.
     */
    private MediaPlayerFactory mediaPlayerFactory;

    /**
     * Native media player.
     */
    private MediaPlayer mediaPlayer;

    private CallbackMediaPlayerComponent mediaPlayerComponent;

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

        mediaPlayerFactory = new MediaPlayerFactory();
        mediaPlayer = mediaPlayerFactory.mediaPlayers().newMediaPlayer();
        mediaPlayerComponent = new CallbackMediaPlayerComponent();

        background = new Color(40, 40, 40);
        this.setLayout(new BorderLayout());
        this.setBackground(background);

        centerConsole = new JPanel();
        centerConsole.setPreferredSize(new Dimension(80,30));
        centerConsole.setLayout(new BorderLayout());

        playerControlsPanel = new PlayerControlsPanel(mediaPlayer);
        playerRadioControlsPanel = new PlayerRadioControlsPanel(mediaPlayer);

        progressBarPanel = new ProgressBarPanel(mediaPlayer);

        updateCenterConsole();

        this.add(centerConsole, BorderLayout.CENTER);

        songInfoPanel = new SongInfoPanel();
        this.add(songInfoPanel, BorderLayout.WEST);

        volumeSliderPanel = new VolumeSliderPanel(mediaPlayer);
        this.add(volumeSliderPanel, BorderLayout.EAST);
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
        return mediaPlayerFactory;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayerComponent.mediaPlayer();
    }

    public CallbackMediaPlayerComponent getMediaPlayerComponent() {
        return mediaPlayerComponent;
    }
}

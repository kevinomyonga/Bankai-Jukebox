package com.bankai.jukebox.views.player;

import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.component.CallbackMediaPlayerComponent;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.component.MediaPlayerComponent;

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
    private final PlayerControlsPanel playerControlsPanel;
    private final PlayerRadioControlsPanel playerRadioControlsPanel;
    private ProgressBarPanel progressBarPanel;
    private SongInfoPanel songInfoPanel;
    private VolumeSliderPanel volumeSliderPanel;

    public PlayerPanel() {
        super();

        mediaPlayerFactory = new MediaPlayerFactory();
        mediaPlayer = mediaPlayerFactory.mediaPlayers().newMediaPlayer();
        mediaPlayerComponent = new CallbackMediaPlayerComponent();

        background = new Color(40, 40, 40);
        this.setLayout(new BorderLayout());
        this.setBackground(background);

        playerControlsPanel = new PlayerControlsPanel();
        this.add(playerControlsPanel, BorderLayout.CENTER);

        playerRadioControlsPanel = new PlayerRadioControlsPanel(mediaPlayer);
        this.add(playerRadioControlsPanel, BorderLayout.CENTER);

        progressBarPanel = new ProgressBarPanel(mediaPlayer);
        this.add(progressBarPanel, BorderLayout.SOUTH);

        songInfoPanel = new SongInfoPanel();
        this.add(songInfoPanel, BorderLayout.WEST);

        volumeSliderPanel = new VolumeSliderPanel(mediaPlayer);
        this.add(volumeSliderPanel, BorderLayout.EAST);
    }

    public PlayerControlsPanel getPlayerControlsPanel() {
        return playerControlsPanel;
    }

    public PlayerRadioControlsPanel getPlayerRadioControlsPanel() {
        return playerRadioControlsPanel;
    }

    public MediaPlayerFactory getMediaPlayerFactory() {
        return mediaPlayerFactory;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public CallbackMediaPlayerComponent getMediaPlayerComponent() {
        return mediaPlayerComponent;
    }
}

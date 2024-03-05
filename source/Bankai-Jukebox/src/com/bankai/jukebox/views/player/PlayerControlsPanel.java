package com.bankai.jukebox.views.player;

import uk.co.caprica.vlcj.player.base.MediaPlayer;

import javax.swing.*;
import java.util.Objects;

public class PlayerControlsPanel extends JPanel {

    private final MediaPlayer mediaPlayer;
    private JButton playPauseStreamBtn;
    private JButton shuffleBtn;
    private JButton previousTrackBtn;
    private JButton nextTrackBtn;
    private JButton enableRepeatBtn;
    private String sourceMrl;

    public PlayerControlsPanel(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;

        Icons icons = new Icons();

        shuffleBtn = new JButton(icons.getShuffleIcon());
        shuffleBtn.addActionListener(e -> shuffleTracks());
        add(shuffleBtn);

        previousTrackBtn = new JButton(icons.getPreviousIcon());
        previousTrackBtn.addActionListener(e -> previousTrack());
        add(previousTrackBtn);

        playPauseStreamBtn = new JButton(!mediaPlayer.status().isPlaying() ?
                icons.getPlayIcon() : icons.getPauseIcon());
        playPauseStreamBtn.addActionListener(e -> {
            if(!Objects.equals(sourceMrl, "")) {
                if (!mediaPlayer.status().isPlaying()) {
                    playPauseStreamBtn.setIcon(icons.getPauseIcon());
                    playStream(sourceMrl);
                } else {
                    playPauseStreamBtn.setIcon(icons.getPauseIcon());
                    pauseStream();
                }
            }
        });
        add(playPauseStreamBtn);

        nextTrackBtn = new JButton(icons.getNextIcon());
        nextTrackBtn.addActionListener(e -> nextTrack());
        add(nextTrackBtn);

        enableRepeatBtn = new JButton(icons.getRepeatAllIcon());
        enableRepeatBtn.addActionListener(e -> nextTrack());
        add(enableRepeatBtn);

        setVisible(true);
    }

    public void playStream(String sourceMrl) {
        // Play the stream
        this.sourceMrl = sourceMrl;
        mediaPlayer.media().play(sourceMrl);
    }

    public void pauseStream() {
        // Pause the stream
        mediaPlayer.controls().pause();
    }

    private void previousTrack() {
    }

    private void nextTrack() {
    }

    private void shuffleTracks() {
    }
}

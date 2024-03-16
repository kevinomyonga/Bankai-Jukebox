package com.bankai.jukebox.views.player;

import com.bankai.jukebox.config.Icons;
import com.bankai.jukebox.controllers.PlayBackController;
import com.bankai.jukebox.utils.playback.SimplePlaybackListener;

import javax.swing.*;
import java.util.Objects;

public class PlayerControlsPanel extends JPanel {

    private final PlayBackController playBackController;
    private JButton playPauseBtn;
    private JButton shuffleBtn;
    private JButton previousTrackBtn;
    private JButton nextTrackBtn;
    private JButton enableRepeatBtn;
    private String sourceMrl;

    public PlayerControlsPanel(PlayBackController playBackController) {
        this.playBackController = playBackController;

        Icons icons = new Icons();

        shuffleBtn = new JButton(icons.getShuffleIcon());
        shuffleBtn.addActionListener(e -> playBackController.shuffle());
        add(shuffleBtn);

        previousTrackBtn = new JButton(icons.getPreviousIcon());
        previousTrackBtn.addActionListener(e -> playBackController.previous());
        add(previousTrackBtn);

        playPauseBtn = new JButton(!playBackController.getMediaPlayer().status().isPlaying() ?
                icons.getPlayIcon() : icons.getPauseIcon());
        playPauseBtn.addActionListener(e -> {
            sourceMrl = playBackController.getMediaPlayer().media().info() != null ?
                    playBackController.getMediaPlayer().media().info().mrl() : null;
            if(!Objects.equals(sourceMrl, "") && (sourceMrl != null)) {
                if (!playBackController.getMediaPlayer().status().isPlaying()) {
                    playPauseBtn.setIcon(icons.getPauseIcon());
                    playBackController.play(sourceMrl);
                } else {
                    playPauseBtn.setIcon(icons.getPlayIcon());
                    playBackController.pause();
                }
            }
        });
        add(playPauseBtn);

        nextTrackBtn = new JButton(icons.getNextIcon());
        nextTrackBtn.addActionListener(e -> playBackController.next());
        add(nextTrackBtn);

        enableRepeatBtn = new JButton(icons.getRepeatAllIcon());
        enableRepeatBtn.addActionListener(e -> playBackController.shouldRepeat(true));
        add(enableRepeatBtn);

        setVisible(true);
    }
}

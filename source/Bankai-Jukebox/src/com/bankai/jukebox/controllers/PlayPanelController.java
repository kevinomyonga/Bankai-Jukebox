package com.bankai.jukebox.controllers;

import com.bankai.jukebox.views.player.PlayerPanel;

public class PlayPanelController {

    private final PlayerPanel playerPanel;

    public PlayPanelController(PlayerPanel playerPanel) {
        this.playerPanel = playerPanel;
    }

    /**
     * pauses the media player
     */
    public void pause() {
        if (playerPanel.getMediaPlayer().status().canPause()){
            playerPanel.getMediaPlayer().controls().pause();
        }
    }

    /**
     * stops the media player and destroys the play thread
     */
    public void stop() {
        if (playerPanel.getMediaPlayer().status().isPlaying()){
            playerPanel.getMediaPlayer().controls().stop();
        }
    }
}

package com.bankai.jukebox.controllers;

import uk.co.caprica.vlcj.player.base.MediaPlayer;

public class PlayBackController {

    // The MediaPlayer instance associated with this controller
    private final MediaPlayer mediaPlayer;

    public PlayBackController(MediaPlayer mediaPlayer) {
        // Initialize the MediaPlayer
        this.mediaPlayer = mediaPlayer;
    }

    /**
     * pauses the media player
     */
    public void play(String sourceMrl) {
        mediaPlayer.media().play(sourceMrl);
    }

    /**
     * pauses the media player
     */
    public void pause() {
        if (mediaPlayer.status().canPause()){
            mediaPlayer.controls().pause();
        }
    }

    /**
     * stops the media player and destroys the play thread
     */
    public void stop() {
        if (mediaPlayer.status().isPlaying()){
            mediaPlayer.controls().stop();
        }
    }
}

package com.bankai.jukebox.controllers;

import uk.co.caprica.vlcj.player.base.MediaPlayer;

/**
 * The AudioController class manages audio-related operations for a MediaPlayer instance,
 * such as changing the volume.
 *
 * @author Kevin Omyonga
 */
public class AudioController {

    // The MediaPlayer instance associated with this controller
    private final MediaPlayer mediaPlayer;

    public AudioController(MediaPlayer mediaPlayer) {
        // Initialize the MediaPlayer
        this.mediaPlayer = mediaPlayer;
    }

    /**
     * Change the volume of the media player.
     *
     * @param volume The volume level to set (0 to 100)
     */
    public void changeVolume(float volume) {
        // Set the volume of the media player
        mediaPlayer.audio().setVolume((int) volume);
    }

    /**
     * Mute/Unmute the audio of the media player.
     */
    public void mute(boolean isMuted) {
        mediaPlayer.audio().setMute(isMuted);
    }
}

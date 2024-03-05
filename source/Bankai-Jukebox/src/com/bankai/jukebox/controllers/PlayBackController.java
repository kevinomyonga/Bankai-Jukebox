package com.bankai.jukebox.controllers;

import uk.co.caprica.vlcj.player.base.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class PlayBackController {

    // The MediaPlayer instance associated with this controller
    private final MediaPlayer mediaPlayer;

    private ArrayList<Object> songQueue = new ArrayList<>(); // an Arraylist of songs that are playable, switch between them by queueIndex;
    private int queueIndex = 0; // controller of which songs to play


    public PlayBackController(MediaPlayer mediaPlayer, ArrayList<Object> songsToAdd) {
        // Initialize the MediaPlayer
        this.mediaPlayer = mediaPlayer;

        if (songsToAdd != null && !songsToAdd.isEmpty()) {
            songQueue.addAll(songsToAdd);
        }
    }

    /**
     * Plays the media
     */
    public void play(String sourceMrl) {
        mediaPlayer.media().play(sourceMrl);
    }

    /**
     * calls updates Song queue and calls play()
     *
     * @param songInQueue the song about to be played
     */
//    public void play(Song songInQueue) {
//        if (songQueue.contains(songInQueue)){
//            queueIndex = songQueue.indexOf(songInQueue);
//            mediaPlayer.media().play(new File(songInQueue.getLocation()).getAbsolutePath());
//        }else{
//            songQueue.add(queueIndex, songInQueue);
//            mediaPlayer.media().play(new File(songInQueue.getLocation()).getAbsolutePath());
//        }
//    }

//    /**
//     * shuffles playlist by stopping the player and shuffling song queue and then playing the player
//     */
//    public void shuffle() {
//        if (!songQueue.isEmpty()) {
//            mediaPlayer.controls().stop();
//            Collections.shuffle(songQueue);
//            queueIndex = 0;
//            mediaPlayer.media().play(new File(songQueue.get(queueIndex).getLocation()).getAbsolutePath());
//        }
//    }

    /**
     * pauses the media player
     */
    public void pause() {
        if (mediaPlayer.status().canPause()){
            mediaPlayer.controls().pause();
        }
    }

    /**
     * Plays previous song if queue size is greater than 1
     */
    public void previous() {
        if (!songQueue.isEmpty()) {
            if (queueIndex >= 1) {
                queueIndex--;
            } else {
                queueIndex = 0;
            }
            mediaPlayer.media().play(new File(String.valueOf(songQueue.get(queueIndex))).getAbsolutePath());
        }
    }

    /**
     * Plays next song if queue size is greater than 1
     */
    public void next() {
        if (!songQueue.isEmpty()) {
            if (queueIndex <= (songQueue.size() - 1))
                queueIndex++;
            else {
                // Reset queue
                queueIndex = 0;
            }
            System.out.println(mediaPlayer.media().play(new File(String.valueOf(songQueue.get(queueIndex))).getAbsolutePath()));
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

    public void shouldRepeat(boolean repeat) {
        mediaPlayer.controls().setRepeat(repeat);
    }
}

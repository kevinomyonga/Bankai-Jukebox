package com.bankai.jukebox.controllers;

import com.bankai.jukebox.config.Constants;
import com.bankai.jukebox.experimental.StreamHttp;
import com.bankai.jukebox.experimental.StreamRTP;
import com.bankai.jukebox.models.Song;
import uk.co.caprica.vlcj.player.base.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class PlayBackController {

    // The MediaPlayer instance associated with this controller
    private final MediaPlayer mediaPlayer;

    private ArrayList<Song> songQueue = new ArrayList<>(); // an Arraylist of songs that are playable, switch between them by queueIndex;
    private int queueIndex = 0; // controller of which songs to play


    public PlayBackController(MediaPlayer mediaPlayer, ArrayList<Song> songsToAdd) {
        // Initialize the MediaPlayer
        this.mediaPlayer = mediaPlayer;

        if (songsToAdd != null && !songsToAdd.isEmpty()) {
            songQueue.addAll(songsToAdd);
        }
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    /**
     * Plays the media
     */
    public void play(String sourceMrl) {
        String currentSourceMrl = mediaPlayer.media().info() != null ? mediaPlayer.media().info().mrl() : null;

        if(currentSourceMrl == null || !Objects.equals(sourceMrl, currentSourceMrl)) {
            // Load media
            mediaPlayer.media().startPaused(sourceMrl);

            // Play
            mediaPlayer.controls().play();
        } else {
            mediaPlayer.controls().play();
        }
    }

    /**
     * calls updates Song queue and calls play()
     *
     * @param songInQueue the song about to be played
     */
    public void play(Song songInQueue) {
        if (songQueue.contains(songInQueue)){
            queueIndex = songQueue.indexOf(songInQueue);
            mediaPlayer.media().play(new File(songInQueue.getLocation()).getAbsolutePath());

            /**
             * Blocks the main thread inorder to stream currently playing audio.
             * Needs a fix before it can be uncommented and go main stream.
             */
//            try {
//                System.out.println("Begin Streaming APP");
//                StreamHttp http = new StreamHttp();
//                http.start(new File(songInQueue.getLocation()).getAbsolutePath());
//                System.out.println("End Streaming APP");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

        }else{
            songQueue.add(queueIndex, songInQueue);
            mediaPlayer.media().play(new File(songInQueue.getLocation()).getAbsolutePath());
        }
    }

    /**
     * shuffles playlist by stopping the player and shuffling song queue and then playing the player
     */
    public void shuffle() {
        if (!songQueue.isEmpty()) {
            mediaPlayer.controls().stop();
            Collections.shuffle(songQueue);
            queueIndex = 0;
            mediaPlayer.media().play(new File(songQueue.get(queueIndex).getLocation()).getAbsolutePath());
        }
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
     * Plays previous song if queue size is greater than 1
     */
    public void previous() {
        if (!songQueue.isEmpty()) {
            if (queueIndex >= 1) {
                queueIndex--;
            } else {
                queueIndex = 0;
            }
            mediaPlayer.media().play(new File(songQueue.get(queueIndex).getLocation()).getAbsolutePath());
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
            mediaPlayer.media().play(new File(songQueue.get(queueIndex).getLocation()).getAbsolutePath());
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

    public void resetQueue(ArrayList<Song> newQueue){
        songQueue = newQueue;
        queueIndex = 0;
    }

    public void setQueueIndex(int index){
        if (index >= songQueue.size()){
            queueIndex = 0;
        }else{
            queueIndex = index;
        }
    }

    public Song getCurrentSong() {
        if (!songQueue.isEmpty()) {
            return songQueue.get(queueIndex);
        } else {
            return null;
        }
    }

    public ArrayList<Song> getSongQueue() {
        return songQueue;
    }

    /**
     * method to get current playback time
     * @return time passed in milliseconds
     */
    public int getSec() {
//        return player.getCurrentFrame() * 26;
        return (int) (mediaPlayer.status().position()* mediaPlayer.status().length());
    }

    /**
     * jumps to the given milisecond of song
     *
     * @param milliseconds - remember that each frame lasts 26 mili seconds. so in order to jump to i'th second we must go to i*60000/26'th frame
     */

    public void move(int milliseconds) {
        if (!songQueue.isEmpty()) {
            if (mediaPlayer.status().isSeekable()){
                mediaPlayer.controls().setTime(milliseconds);
            }
//            if (player == null) {
//                initPlayer();
//            } else if (!this.player.isPaused() || player.isComplete() || player.isStopped()) {
//                stop();
//                initPlayer();
//            }
//            executorService.execute(() -> {
//                try {
//                    playbackListener.playbackStarted(null);
//                    player.play((milliseconds) / 26);
//                } catch (JavaLayerException e) {
//                    e.printStackTrace();
//                }
//            });
        }
    }
}

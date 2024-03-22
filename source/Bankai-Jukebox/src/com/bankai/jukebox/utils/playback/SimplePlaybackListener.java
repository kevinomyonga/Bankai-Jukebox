package com.bankai.jukebox.utils.playback;

import com.bankai.jukebox.Main;
import com.bankai.jukebox.models.Request;
import com.bankai.jukebox.models.Song;
import com.bankai.jukebox.pages.HomePage;
import com.bankai.jukebox.views.player.PlayerControlsPanel;
import com.bankai.jukebox.views.player.PlayerPanel;
import uk.co.caprica.vlcj.media.MediaRef;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SimplePlaybackListener extends MediaPlayerEventAdapter {

//    private boolean initLyrics(String songTitle, String artist){
//        boolean result = true;
//        org.jmusixmatch.MusixMatch instance = new MusixMatch(PlaybackControlPanel.API_KEY);
//        Track track = null;
//        try {
//            track = instance.getMatchingTrack(songTitle, artist);
//        } catch (MusixMatchException ex) {
//            result = false;
//        }
//        if (track != null) {
//            TrackData data = track.getTrack();
//
//            int trackID = data.getTrackId();
//
//            Lyrics lyrics = null;
//            try {
//                lyrics = instance.getLyrics(trackID);
//            } catch (MusixMatchException ex) {
//                result = false;
//            }
//            if (lyrics != null) {
//                JFrame lyricsFrame = new JFrame(songTitle + " - " + artist);
//                JPanel panel = new JPanel(new BorderLayout());
//                panel.setBackground(new Color(22, 22, 22));
//                JLabel label = new JLabel();
//                label.setText("<html>" + lyrics.getLyricsBody().replaceAll("\n", "<br/>") + "</html>");
//                System.out.println();
//                label.setBackground(new Color(22, 22, 22));
//                label.setFont(FontManager.getUbuntu(16f));
//                label.setForeground(Color.WHITE);
//                label.setBorder(new EmptyBorder(20, 20, 20, 20));
//                panel.add(label);
//                lyricsFrame.add(panel);
//                lyricsFrame.pack();
//                lyricsFrame.setVisible(true);
//            }
//        }
//        return result;
//    }

    private PlayerPanel playerPanel;

    public SimplePlaybackListener(PlayerPanel playerPanel) {
        this.playerPanel = playerPanel;
    }

    @Override
    public void mediaChanged(MediaPlayer mediaPlayer, MediaRef media) {
        playerPanel.getProgressBarPanel().resetProgress();
        playerPanel.getMiniPlayer().getMiniPlayerControlsPanel().getProgressBarPanel().resetProgress();
        playerPanel.getSongInfoPanel().updateInformation();
//        playerControlsPanel.logData();
    }

    @Override
    public void playing(MediaPlayer mediaPlayer) {
        System.out.println("started");
        playerPanel.getProgressBarPanel().startProgress();
        playerPanel.getMiniPlayer().getMiniPlayerControlsPanel().getProgressBarPanel().startProgress();
        playerPanel.getSongInfoPanel().updateInformation();

        if(playerPanel.getPlayBackController().getCurrentSong() != null) {
            Main.user.listened(playerPanel.getPlayBackController().getCurrentSong(), HomePage.databaseHandler);

            // Update play count
            Song currentSong = playerPanel.getPlayBackController().getCurrentSong();
            currentSong.setPlayCount(currentSong.getPlayCount() + 1);

            Main.databaseHandler.updateSong(currentSong);
//        new Thread(()-> initLyrics(playerPanel.getPlayBackController().getCurrentSong().getTitle(), playerPanel.getPlayBackController().getCurrentSong().getArtist())).start();
            Main.user.setCurrentSong(playerPanel.getPlayBackController().getCurrentSong());
            System.out.println(Main.user.getCurrentSong().getTitle());
//        MainFrame.userClient.sendRequest(new Request(0, Main.user));
        }
    }

    @Override
    public void paused(MediaPlayer mediaPlayer) {
        System.out.println("paused");
        playerPanel.getProgressBarPanel().stopProgress();
        playerPanel.getMiniPlayer().getMiniPlayerControlsPanel().getProgressBarPanel().stopProgress();
    }

    @Override
    public void stopped(MediaPlayer mediaPlayer) {
        playerPanel.getProgressBarPanel().resetProgress();
        playerPanel.getMiniPlayer().getMiniPlayerControlsPanel().getProgressBarPanel().resetProgress();
        Main.user.setCurrentSong(null);
//        MainFrame.userClient.sendRequest(new Request(0, Main.user));
    }

    @Override
    public void finished(MediaPlayer mediaPlayer) {
        System.out.println("finished");
        mediaPlayer.submit(() -> playerPanel.getPlayBackController().next());
    }

    @Override
    public void error(MediaPlayer mediaPlayer) {
        super.error(mediaPlayer);
    }
}

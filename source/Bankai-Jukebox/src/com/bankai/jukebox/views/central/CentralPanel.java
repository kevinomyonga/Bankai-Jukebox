package com.bankai.jukebox.views.central;

import com.bankai.jukebox.views.central.library.AlbumsPanel;
import com.bankai.jukebox.views.central.library.SongsPanel;
import com.bankai.jukebox.views.central.library.VideosPanel;
import com.bankai.jukebox.views.central.more.AboutPanel;
import com.bankai.jukebox.views.central.more.SettingsPanel;
import com.bankai.jukebox.views.central.playlists.FavouritesPanel;
import com.bankai.jukebox.views.central.playlists.PlaylistsPanel;
import com.bankai.jukebox.views.central.radio.RadioPanel;
import com.bankai.jukebox.views.central.radio.RecordingsPanel;
import com.bankai.jukebox.views.player.PlayerPanel;

import javax.swing.*;
import java.awt.*;

public class CentralPanel extends JPanel {

    private final PlayerPanel playerPanel;

    public CentralPanel(PlayerPanel playerPanel) {
        super();

        this.setLayout(new BorderLayout());

        this.playerPanel = playerPanel;
    }

    public void setContentPanel(Component component){
        // Clear existing components
        removeAll();

        // Add the new component to the center
        JScrollPane jscrollPane = new JScrollPane(component);
        jscrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jscrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(jscrollPane, BorderLayout.CENTER);

        // Repaint the panel to reflect the changes
        revalidate();
        repaint();
    }

    // Method to switch to the show panel
    public void showShowPanel() {
        ShowPanel showPanel = new ShowPanel();
        setContentPanel(showPanel);
    }

    // Method to switch to the songs panel
    public void showSongsPanel() {
        SongsPanel songsPanel = new SongsPanel(playerPanel);
        setContentPanel(songsPanel);
    }

    // Method to switch to the songs panel
    public void showAlbumsPanel() {
        AlbumsPanel albumsPanel = new AlbumsPanel(playerPanel);
        setContentPanel(albumsPanel);
    }

    // Method to switch to the videos panel
    public void showVideosPanel() {
        VideosPanel videosPanel = new VideosPanel(playerPanel);
        setContentPanel(videosPanel);
    }

    // Method to switch to the playlists panel
    public void showPlaylistsPanel() {
        PlaylistsPanel playlistsPanel = new PlaylistsPanel(playerPanel);
        setContentPanel(playlistsPanel);
    }

    // Method to switch to the favourites panel
    public void showFavouritesPanel() {
        FavouritesPanel favouritesPanel = new FavouritesPanel(playerPanel);
        setContentPanel(favouritesPanel);
    }

    // Method to switch to the radio panel
    public void showRadioPanel() {
        RadioPanel radioPanel = new RadioPanel(playerPanel);
        setContentPanel(radioPanel);
    }

    // Method to switch to the recordings panel
    public void showRecordingsPanel() {
        RecordingsPanel recordingsPanel = new RecordingsPanel(playerPanel);
        setContentPanel(recordingsPanel);
    }

    // Method to switch to the settings panel
    public void showSettingsPanel() {
        SettingsPanel settingsPanel = new SettingsPanel();
        setContentPanel(settingsPanel);
    }

    // Method to switch to the about panel
    public void showAboutPanel() {
        AboutPanel aboutPanel = new AboutPanel();
        setContentPanel(aboutPanel);
    }
}

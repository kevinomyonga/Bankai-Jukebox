package com.bankai.jukebox.views.menu;

import com.bankai.jukebox.views.central.CentralPanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class MenuPanel extends JPanel {

    private CentralPanel centralPanel;
    private JSeparator jSeparator;
    private JLabel libraryLabel;
    private JButton fileChooserBtn;
    private JButton songsBtn;
    private JButton albumsBtn;
    private JButton videosBtn;
    private JLabel playlistLabel;
    private JButton playlistBtn;
    private JButton newPlaylistBtn;
    private JButton sharedPlaylistBtn;
    private JButton favouriteBtn;
    private JLabel radioLabel;
    private JButton radioChannelsBtn;
    private JButton radioChannelRecordingsBtn;
    private JLabel moreLabel;
    private JButton settingsBtn;
    private JButton aboutBtn;

    public MenuPanel(CentralPanel centralPanel) {
        super();

        this.setPreferredSize(new Dimension(200, 690));
//        setSize(400, 400);
//        this.setBackground(new Color(24, 24, 24, 20));
        setLayout(new GridLayout(25, 1));

        this.centralPanel = centralPanel;

        jSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        jSeparator.setForeground(new Color(39, 39, 39, 20));
//        add(jSeparator);

        add(Box.createRigidArea(new Dimension(0, 1)));

        libraryLabel = new JLabel("    LIBRARY      ");
        libraryLabel.setFont(new Font("Arial", Font.BOLD, 12));
        libraryLabel.setBackground(this.getBackground());
        add(libraryLabel);

        /*
          This button shows all existed songs according to last time played
         */
        songsBtn = new JButton("Songs");
        songsBtn.setFont(new Font("Arial", Font.BOLD, 12));
        songsBtn.setHorizontalAlignment(SwingConstants.LEFT);
        songsBtn.addActionListener(e -> centralPanel.showSongsPanel());
        songsBtn.setPreferredSize(new Dimension(0, 1));
        add(songsBtn);

        /*
          This button shows all albums according to last time played
         */
        albumsBtn = new JButton("Albums");
        albumsBtn.setFont(new Font("Arial", Font.BOLD, 12));
        albumsBtn.setHorizontalAlignment(SwingConstants.LEFT);
        albumsBtn.addActionListener(e -> centralPanel.showAlbumsPanel());
        add(albumsBtn);

        /*
          This button shows all videos
         */
        videosBtn = new JButton("Videos");
        videosBtn.setFont(new Font("Arial", Font.BOLD, 12));
        videosBtn.setHorizontalAlignment(SwingConstants.LEFT);
        videosBtn.addActionListener(e -> centralPanel.showVideosPanel());
        add(videosBtn);

        /*
          This button is for adding a new song to the program
         */
        fileChooserBtn = new JButton("Add To Library");
        fileChooserBtn.setFont(new Font("Arial", Font.BOLD, 12));
        fileChooserBtn.setHorizontalAlignment(SwingConstants.LEFT);
        fileChooserBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter(".mp3 files", "mp3"));
            fileChooser.setCurrentDirectory(new File("E:/"));
            int result = fileChooser.showOpenDialog(fileChooser);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
//                    saveSong(addSong(selectedFile.getAbsolutePath()));
            }
        });
        add(fileChooserBtn);

        add(Box.createRigidArea(new Dimension(0, 1)));

        playlistLabel = new JLabel("    PLAYLISTS");
        playlistLabel.setFont(new Font("Arial", Font.BOLD, 12));
//        playlistLabel.setForeground(foreground);
        playlistLabel.setBackground(this.getBackground());
        add(playlistLabel);


        /*
          This button creates and adds a new playlist
         */
        newPlaylistBtn = new JButton("New Playlist");
        newPlaylistBtn.setFont(new Font("Arial", Font.BOLD, 12));
        newPlaylistBtn.setHorizontalAlignment(SwingConstants.LEFT);
        add(newPlaylistBtn);

        /*
          This button shows all existing playlists for user
         */
        playlistBtn = new JButton("My Playlists");
        playlistBtn.setFont(new Font("Arial", Font.BOLD, 12));
        playlistBtn.setHorizontalAlignment(SwingConstants.LEFT);
        playlistBtn.addActionListener(e -> centralPanel.showPlaylistsPanel());
        add(playlistBtn);

        /*
          This buttons shows user's shared playlist on network
         */
        sharedPlaylistBtn = new JButton("Shared Playlist");
        sharedPlaylistBtn.setFont(new Font("Arial", Font.BOLD, 12));
        sharedPlaylistBtn.setHorizontalAlignment(SwingConstants.LEFT);
        sharedPlaylistBtn.addActionListener(e -> centralPanel.showShowPanel());
        add(sharedPlaylistBtn);

        /*
          Every user has some favourite songs which is shown by this button
         */
        favouriteBtn = new JButton("Favourites");
        favouriteBtn.setFont(new Font("Arial", Font.BOLD, 12));
        favouriteBtn.setHorizontalAlignment(SwingConstants.LEFT);
        favouriteBtn.addActionListener(e -> centralPanel.showFavouritesPanel());
        add(favouriteBtn);

        add(Box.createRigidArea(new Dimension(0, 1)));

        radioLabel = new JLabel("    RADIO");
        radioLabel.setFont(new Font("Arial", Font.BOLD, 12));
        radioLabel.setBackground(this.getBackground());
        add(radioLabel);

        /*
          This button navigates to the radio channels panel
         */
        radioChannelsBtn = new JButton("Channels");
        radioChannelsBtn.setFont(new Font("Arial", Font.BOLD, 12));
        radioChannelsBtn.setHorizontalAlignment(SwingConstants.LEFT);
        radioChannelsBtn.addActionListener(e -> centralPanel.showRadioPanel());
        add(radioChannelsBtn);

        /*
          This button navigates to the radio channel recordings panel
         */
        radioChannelRecordingsBtn = new JButton("Recordings");
        radioChannelRecordingsBtn.setFont(new Font("Arial", Font.BOLD, 12));
        radioChannelRecordingsBtn.setHorizontalAlignment(SwingConstants.LEFT);
        radioChannelRecordingsBtn.addActionListener(e -> centralPanel.showRecordingsPanel());
        add(radioChannelRecordingsBtn);

        add(jSeparator);

        moreLabel = new JLabel("    MORE");
        moreLabel.setFont(new Font("Arial", Font.BOLD, 12));
        moreLabel.setBackground(this.getBackground());
        add(moreLabel);

        /*
          This button navigates to the settings panel
         */
        settingsBtn = new JButton("Settings");
        settingsBtn.setFont(new Font("Arial", Font.BOLD, 12));
        settingsBtn.setHorizontalAlignment(SwingConstants.LEFT);
        settingsBtn.addActionListener(e -> centralPanel.showSettingsPanel());
        add(settingsBtn);

        /*
          This button navigates to the about panel
         */
        aboutBtn = new JButton("About");
        aboutBtn.setFont(new Font("Arial", Font.BOLD, 12));
        aboutBtn.setHorizontalAlignment(SwingConstants.LEFT);
        aboutBtn.addActionListener(e -> centralPanel.showAboutPanel());
        add(aboutBtn);

        setVisible(true);
    }
}

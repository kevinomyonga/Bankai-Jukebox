package com.bankai.jukebox.views.player;

import com.bankai.jukebox.Main;
import com.bankai.jukebox.controllers.PlayBackController;
import com.bankai.jukebox.views.central.library.VideosPanel;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class SongInfoPanel extends JPanel {

    private PlayBackController playBackController;

    private JLabel songImageLabel;
    private JLabel songNameLabel;
    private JLabel songAlbumLabel;
    private JLabel songArtistLabel;
    private JPanel data;
    private Color background;

    private Font font1;
    private Font font2;

    private boolean isPlaying;


    private boolean isLiked = false;
    private ImageIcon likeImage;
    private ImageIcon disLikeImage;
    private JLabel dislikeLabel = new JLabel();

    public SongInfoPanel(PlayBackController playBackController) {
        super();
//        background = new Color(40, 40, 40);
//        foreground = Color.white;

        this.playBackController = playBackController;

        this.setLayout(new GridLayout(1, 2));
//        this.setBackground(background);
        this.font1 = new Font("Arial", Font.ITALIC, 14);
        this.font2 = new Font("Arial", Font.BOLD, 12);

        songImageLabel = new JLabel();
        songImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon songArtwork = new ImageIcon(Objects.requireNonNull(
                VideosPanel.class.getClassLoader().getResource("images/no-artwork.jpg")));
        Image image = songArtwork.getImage(); // transform it
        Image newImg = image.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        songArtwork = new ImageIcon(newImg);
        songImageLabel.setIcon(songArtwork);
//        icon.setForeground(foreground);
        this.add(songImageLabel);

        data = new JPanel();
        data.setPreferredSize(new Dimension(80,30));
        data.setLayout(new BorderLayout());
        data.setBackground(background);

        songArtistLabel = new JLabel("");
        songArtistLabel.setFont(font2);
        data.add(songArtistLabel, BorderLayout.NORTH);

        songNameLabel = new JLabel("");
        songNameLabel.setFont(font1);
        data.add(songNameLabel, BorderLayout.CENTER);

        songAlbumLabel = new JLabel("");
        songAlbumLabel.setFont(font2);
        data.add(songAlbumLabel, BorderLayout.SOUTH);

        this.add(data);
    }

    /**
     * Updates song information on player
     */
    public void updateInformation(){
        //Updating artWork
        URL songArtWorkUrl;
        Image songArtWorkImage;
        ImageIcon songArtWorkIcon = null;
        try{
            songArtWorkUrl = playBackController.getCurrentSong().getArtWork().toURL();
            songArtWorkIcon = new ImageIcon(songArtWorkUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        songArtWorkImage = Objects.requireNonNull(songArtWorkIcon).getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH);
        ImageIcon homeIconn = new ImageIcon(songArtWorkImage);
        songImageLabel.setIcon(homeIconn);
        if (Main.user.getLikedSongs().contains(playBackController.getCurrentSong())){
            System.out.println(playBackController.getCurrentSong().getTitle());
            isLiked = true;
            dislikeLabel.setIcon(likeImage);
            this.validate();
            this.repaint();
        }else{
            isLiked = false;
            dislikeLabel.setIcon(disLikeImage);
            this.validate();
            this.repaint();
        }

        //Updating name
        songNameLabel.setText(playBackController.getCurrentSong().getTitle());
        //Updating Artist
        songArtistLabel.setText(playBackController.getCurrentSong().getArtist());
        //Updating Album
        songAlbumLabel.setText(playBackController.getCurrentSong().getAlbum());

        isPlaying = true;
    }
}

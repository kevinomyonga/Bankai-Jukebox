package com.bankai.jukebox.views.player;

import javax.swing.*;
import java.awt.*;

public class SongInfoPanel extends JPanel {

    private JLabel icon;
    private JLabel title;
    private JLabel album;
    private JLabel artist;
    private JPanel data;
    private Color background;
    private Color foreground;
    private Font font1;
    private Font font2;

    public SongInfoPanel() {
        super();
    }

    /**
     * updates song information on player
     * @param artwork
     * @param title
     * @param artistName
     * @param albumName
     */
    public void refresh(byte[] artwork, String title, String artistName, String albumName) {

        icon.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon songArtwork;
        if (artwork != null) {
            songArtwork = new ImageIcon(artwork);
        } else {
            songArtwork = new ImageIcon("/Users/apple/Desktop/userIcon.png");
        }
        Image image = songArtwork.getImage(); // transform it
        Image newImg = image.getScaledInstance(43, 43, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        songArtwork = new ImageIcon(newImg);
        icon.setIcon(songArtwork);
        icon.setForeground(Color.white);

        if (title != null) {
            this.title.setText(title);
        } else {
            this.title.setText("Title");
        }

        if (artistName != null) {
            this.artist.setText(artistName);
        } else {
            this.artist.setText("Artist");
        }

        if (albumName != null) {
            this.album.setText(albumName);
        } else {
            this.album.setText("Album");
        }

    }
}

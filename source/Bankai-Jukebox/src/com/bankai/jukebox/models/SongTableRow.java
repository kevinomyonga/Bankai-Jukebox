package com.bankai.jukebox.models;

import com.bankai.jukebox.config.Icons;
import com.bankai.jukebox.utils.IO.FileIO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class SongTableRow {
    private ImageIcon addIcon;
    private ImageIcon artWork;
    private String title;
    private String album;
    private String artist;
    private Boolean checked;
    private int playCount;
    private long lastPlayed;
    private Song song;

    {
        addIcon = new ImageIcon(Objects.requireNonNull(
                SongTableRow.class.getClassLoader().getResource("icons/add.png")));
        Image addIconImage = addIcon.getImage(); // transform it
        Image newAddIconImage = addIconImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // scale it the smooth way
        addIcon = new ImageIcon(newAddIconImage);
    }

    public SongTableRow(Song song) {
        this.song = song;
        try {
//            this.artWork = new ImageIcon(new ImageIcon(song.getArtWork().toURL()).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));

            this.artWork = new ImageIcon(song.getArtWork().toURL());
            Image artWorkImage = this.artWork.getImage(); // transform it
            Image newArtWorkImage = artWorkImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH); // scale it the smooth way
            this.artWork = new ImageIcon(newArtWorkImage);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.title = song.getTitle();
        this.album = song.getAlbum();
        this.artist = song.getArtist();
        this.checked = false;
        this.playCount = song.getPlayCount();
        this.lastPlayed = song.getPlayDate();

    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public ImageIcon getAddIcon() {
        return addIcon;
    }

    public ImageIcon getArtWork() {
        return artWork;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public int getPlayCount() {
        return playCount;
    }

    public long getLastPlayed() {
        return lastPlayed;
    }

    public Song getSong() {
        return song;
    }
}

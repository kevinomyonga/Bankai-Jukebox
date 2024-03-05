package com.bankai.jukebox.config;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Icons {

    private ImageIcon appLogoIcon;

    private ImageIcon shareIcon;

    private ImageIcon playIcon;

    private ImageIcon pauseIcon;

    private ImageIcon previousIcon;

    private ImageIcon nextIcon;

    private ImageIcon shuffleIcon;

    private ImageIcon repeatOneIcon;

    private ImageIcon repeatAllIcon;

    private ImageIcon volumeIcon;

    private ImageIcon lyricsIcon;

    private ImageIcon likeIcon;

    private ImageIcon unLikeIcon;


    public Icons() {

        appLogoIcon = new ImageIcon(Objects.requireNonNull(
                Icons.class.getClassLoader().getResource("icons/app-logo.png")));
        Image appLogoImage = appLogoIcon.getImage(); // transform it
        Image newAppLogoImage = appLogoImage.getScaledInstance(144, 144, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        appLogoIcon = new ImageIcon(newAppLogoImage);

        playIcon = new ImageIcon(Objects.requireNonNull(
                Icons.class.getClassLoader().getResource("icons/play.png")));
        Image image = playIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        playIcon = new ImageIcon(newimg);

        pauseIcon = new ImageIcon(Objects.requireNonNull(
                Icons.class.getClassLoader().getResource("icons/pause.png")));
        Image image1 = pauseIcon.getImage(); // transform it
        Image newimg1 = image1.getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        pauseIcon = new ImageIcon(newimg1);

        previousIcon = new ImageIcon(Objects.requireNonNull(
                Icons.class.getClassLoader().getResource("icons/previous.png")));
        Image image9 = previousIcon.getImage(); // transform it
        Image newimg9 = image9.getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        previousIcon = new ImageIcon(newimg9);

        nextIcon = new ImageIcon(Objects.requireNonNull(
                Icons.class.getClassLoader().getResource("icons/next.png")));
        Image image2 = nextIcon.getImage(); // transform it
        Image newimg2 = image2.getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        nextIcon = new ImageIcon(newimg2);

        shuffleIcon = new ImageIcon(Objects.requireNonNull(
                Icons.class.getClassLoader().getResource("icons/shuffle.png")));
        Image image3 = shuffleIcon.getImage(); // transform it
        Image newimg3 = image3.getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        shuffleIcon = new ImageIcon(newimg3);
//
//        repeatOneIcon = new ImageIcon(Objects.requireNonNull(
//                VideosPanel.class.getClassLoader().getResource("icons/repeat1Icon.png")));
//        Image image4 = repeatOneIcon.getImage(); // transform it
//        Image newimg4 = image4.getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
//        repeatOneIcon = new ImageIcon(newimg4);
//
        repeatAllIcon = new ImageIcon(Objects.requireNonNull(
                Icons.class.getClassLoader().getResource("icons/repeat.png")));
        Image image5 = repeatAllIcon.getImage(); // transform it
        Image newimg5 = image5.getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        repeatAllIcon = new ImageIcon(newimg5);

        volumeIcon = new ImageIcon(Objects.requireNonNull(
                Icons.class.getClassLoader().getResource("icons/volume.png")));
        Image volumeIconImage = volumeIcon.getImage(); // transform it
        Image newVolumeIconImage = volumeIconImage.getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        volumeIcon = new ImageIcon(newVolumeIconImage);

        lyricsIcon = new ImageIcon(Objects.requireNonNull(
                Icons.class.getClassLoader().getResource("icons/volume.png")));
        Image lyricsIconImage = lyricsIcon.getImage(); // transform it
        Image newLyricsIconImage = lyricsIconImage.getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        lyricsIcon = new ImageIcon(newLyricsIconImage);
//
//        likeIcon = new ImageIcon(Objects.requireNonNull(
//                VideosPanel.class.getClassLoader().getResource("icons/likeIcon.png")));
//        Image image6 = likeIcon.getImage(); // transform it
//        Image newimg6 = image6.getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
//        likeIcon = new ImageIcon(newimg6);
//
//        unLikeIcon = new ImageIcon(Objects.requireNonNull(
//                VideosPanel.class.getClassLoader().getResource("icons/unlikeIcon.png")));
//        Image image7 = unLikeIcon.getImage(); // transform it
//        Image newimg7 = image7.getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
//        unLikeIcon = new ImageIcon(newimg7);
//
//        shareIcon = new ImageIcon(Objects.requireNonNull(
//                VideosPanel.class.getClassLoader().getResource("icons/shareIcon.png")));
//        Image image8 = shareIcon.getImage(); // transform it
//        Image newimg8 = image8.getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
//        shareIcon = new ImageIcon(newimg8);


    }

    public ImageIcon getAppLogoIcon() {
        return appLogoIcon;
    }

    public ImageIcon getLikeIcon() {
        return likeIcon;
    }

    public ImageIcon getNextIcon() {
        return nextIcon;
    }

    public ImageIcon getPauseIcon() {
        return pauseIcon;
    }

    public ImageIcon getPlayIcon() {
        return playIcon;
    }

    public ImageIcon getPreviousIcon() {
        return previousIcon;
    }

    public ImageIcon getRepeatAllIcon() {
        return repeatAllIcon;
    }
//
//    public ImageIcon getRepeatOneIcon() {
//        return repeatOneIcon;
//    }
//
//    public ImageIcon getShareIcon() {
//        return shareIcon;
//    }
//
    public ImageIcon getShuffleIcon() {
        return shuffleIcon;
    }

    public ImageIcon getVolumeIcon() {
        return volumeIcon;
    }

    public ImageIcon getLyricsIcon() {
        return lyricsIcon;
    }
//
//    public ImageIcon getUnLikeIcon() {
//        return unLikeIcon;
//    }
}

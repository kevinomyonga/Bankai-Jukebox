package com.bankai.jukebox.config;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Animations {

    ImageIcon iconAni0, iconAni1, iconAni2, iconAni3, iconAni4, iconAni5, iconAni6;
    Image imageAni0, imageAni1, imageAni2, imageAni3, imageAni4, imageAni5, imageAni6;


    public Animations() {

        iconAni0  = new ImageIcon(Objects.requireNonNull(
                Animations.class.getClassLoader().getResource("animations/animated0.gif")));
        imageAni0 = iconAni0.getImage();
        imageAni0 = imageAni0.getScaledInstance(350,260, Image.SCALE_DEFAULT);
        iconAni0.setImage(imageAni0);

        iconAni1  = new ImageIcon(Objects.requireNonNull(
                Animations.class.getClassLoader().getResource("animations/animated1.gif")));
        imageAni1 = iconAni1.getImage();
        imageAni1 = imageAni1.getScaledInstance(350,260, Image.SCALE_DEFAULT);
        iconAni1.setImage(imageAni1);

        iconAni2  = new ImageIcon(Objects.requireNonNull(
                Animations.class.getClassLoader().getResource("animations/animated2.gif")));
        imageAni2 = iconAni2.getImage();
        imageAni2 = imageAni2.getScaledInstance(260,260, Image.SCALE_DEFAULT);
        iconAni2.setImage(imageAni2);

        iconAni3  = new ImageIcon(Objects.requireNonNull(
                Animations.class.getClassLoader().getResource("animations/animated3.gif")));
        imageAni3 = iconAni3.getImage();
        imageAni3 = imageAni3.getScaledInstance(260,260, Image.SCALE_DEFAULT);
        iconAni3.setImage(imageAni3);

        iconAni4  = new ImageIcon(Objects.requireNonNull(
                Animations.class.getClassLoader().getResource("animations/animated4.gif")));
        imageAni4 = iconAni4.getImage();
        imageAni4 = imageAni4.getScaledInstance(260,260, Image.SCALE_DEFAULT);
        iconAni4.setImage(imageAni4);

        iconAni5  = new ImageIcon(Objects.requireNonNull(
                Animations.class.getClassLoader().getResource("animations/animated5.gif")));
        imageAni5 = iconAni5.getImage();
        imageAni5 = imageAni5.getScaledInstance(260,260, Image.SCALE_DEFAULT);
        iconAni5.setImage(imageAni5);

        iconAni6  = new ImageIcon(Objects.requireNonNull(
                Animations.class.getClassLoader().getResource("animations/animated6.gif")));
        imageAni6 = iconAni6.getImage();
        imageAni6 = imageAni6.getScaledInstance(260,260, Image.SCALE_DEFAULT);
        iconAni6.setImage(imageAni6);

    }

    public ImageIcon getIconAni0() {
        return iconAni0;
    }

    public ImageIcon getIconAni1() {
        return iconAni1;
    }

    public ImageIcon getIconAni2() {
        return iconAni2;
    }

    public ImageIcon getIconAni3() {
        return iconAni3;
    }

    public ImageIcon getIconAni4() {
        return iconAni4;
    }

    public ImageIcon getIconAni5() {
        return iconAni5;
    }

    public ImageIcon getIconAni6() {
        return iconAni6;
    }
}

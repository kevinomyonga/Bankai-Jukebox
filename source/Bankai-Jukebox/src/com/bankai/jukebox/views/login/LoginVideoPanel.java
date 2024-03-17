package com.bankai.jukebox.views.login;

import com.bankai.jukebox.pages.LoginPage;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.component.CallbackMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;

public class LoginVideoPanel extends JPanel {

    private final CallbackMediaPlayerComponent mediaPlayerComponent;

    public LoginVideoPanel() {
        this.setLayout(new BorderLayout());

        mediaPlayerComponent = new CallbackMediaPlayerComponent();

        add(mediaPlayerComponent);

        mediaPlayerComponent.mediaPlayer().events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            @Override
            public void timeChanged(MediaPlayer mediaPlayer, long newTime) {
                if (newTime >= mediaPlayer.status().length() - 500) {
                    mediaPlayer.controls().setPosition(0);
                }
            }
        });

//        Overlay overlay = new Overlay(this);
//        mediaPlayerComponent.mediaPlayer().overlay().set(overlay);
//        mediaPlayerComponent.mediaPlayer().overlay().enable(true);
    }

    public void play() {
        if (mediaPlayerComponent.mediaPlayer().status().isPlaying()) {
            mediaPlayerComponent.mediaPlayer().controls().stop();
        }

        URL resourceUrl = LoginPage.class.getClassLoader().getResource("videos/video-background.mp4");
        if (resourceUrl != null) {
            File file = new File(resourceUrl.getFile());
            String absolutePath = file.getAbsolutePath();
            System.out.println("Absolute path: " + absolutePath);

            // Load Video
            mediaPlayerComponent.mediaPlayer().media().startPaused(file.getAbsolutePath());
            // Play
            mediaPlayerComponent.mediaPlayer().controls().play();

        } else {
            System.out.println("Resource not found.");
        }
    }

    public void stop() {
        mediaPlayerComponent.mediaPlayer().controls().stop();
        mediaPlayerComponent.mediaPlayer().release();
        mediaPlayerComponent.mediaPlayerFactory().release();
    }
}

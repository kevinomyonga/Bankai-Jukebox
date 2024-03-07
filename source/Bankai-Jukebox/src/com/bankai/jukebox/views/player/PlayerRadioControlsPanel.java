package com.bankai.jukebox.views.player;

import com.bankai.jukebox.config.Constants;
import com.bankai.jukebox.models.RadioStation;
import uk.co.caprica.vlcj.player.base.MediaPlayer;

import javax.swing.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerRadioControlsPanel extends JPanel {

    private final MediaPlayer mediaPlayer;
    private JButton startStreamBtn;
    private JButton stopStreamBtn;
    private JButton recordStreamBtn;

    private boolean record = false;
    private RadioStation radioStation;

    public PlayerRadioControlsPanel(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;

        startStreamBtn = new JButton("Start Stream");
        startStreamBtn.addActionListener(e -> {
            if(radioStation != null) {
                recordRadioStream(radioStation.getSource1());
            }
        });
        add(startStreamBtn);

        stopStreamBtn = new JButton("Stop Stream");
        stopStreamBtn.addActionListener(e -> stopRadioStream());
        add(stopStreamBtn);

        recordStreamBtn = new JButton("Record Stream");
        recordStreamBtn.addActionListener(e -> {
            if(radioStation != null) {
                recordRadioStream(radioStation.getSource1());
            }
        });
        add(recordStreamBtn);

        setVisible(true);
    }

    public void playRadioStream(RadioStation radioStation) {
        // Play the station stream
        this.radioStation = radioStation;
        mediaPlayer.media().play(radioStation.getSource1());
    }


    public void stopRadioStream() {
        // Stop the station stream
        mediaPlayer.controls().stop();
    }


    public void recordRadioStream(String sourceMrl) {

        // Record the station stream
        String[] mediaOptions = record ? getRecordMediaOptions(sourceMrl) : new String[0];
        // Stop the stream
        mediaPlayer.controls().stop();
        // Start the stream with recording enabled
        mediaPlayer.media().play(sourceMrl, mediaOptions);

        // Enable recording if disabled and disable recording if enabled
        record = !record;
    }

    private String[] getRecordMediaOptions(String address) {
        File file = getFile(address);
        StringBuilder sb = new StringBuilder(200);
        sb.append("sout=#transcode{acodec=mp3,channels=2,ab=192,samplerate=44100}:duplicate{dst=display,dst=std{access=file,mux=raw,dst=");
        sb.append(file.getPath());
        sb.append("}}");
        return new String[] {sb.toString()};
    }

    private File getFile(String address) {
        StringBuilder sb = new StringBuilder(100);
        try {
            URL url = new URL(address);
            sb.append(new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date()));
            sb.append('-');
            sb.append(radioStation.getTitle().replace(' ', '_'));
            sb.append('-');
            sb.append(Constants.APP_NAME.replace(' ', '_'));
            sb.append('-');
            sb.append(url.getPort());
            sb.append(".mp3");

            File saveDirectory = new File(Constants.APP_RECORDINGS_DIRECTORY);
            if(!saveDirectory.exists()) {
                saveDirectory.mkdirs();
            }
            return new File(saveDirectory, sb.toString());
        }
        catch(MalformedURLException e) {
            throw new RuntimeException("Unable to create a URL for '" + address + "'");
        }
    }
}

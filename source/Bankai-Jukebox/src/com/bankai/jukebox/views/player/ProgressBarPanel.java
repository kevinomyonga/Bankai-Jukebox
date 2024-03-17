package com.bankai.jukebox.views.player;

import com.bankai.jukebox.controllers.PlayBackController;
import com.bankai.jukebox.models.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProgressBarPanel extends JPanel {

    private final PlayBackController playBackController;
    private final JTextField passedTime;
    private final JProgressBar progressBar;
    private final JTextField totalTime;
    private boolean isPlaying;

    private long duration;
    private int playState = 0;
    double pos;

    private Thread progressThread;

    public ProgressBarPanel(PlayBackController playBackController) {
        this.playBackController = playBackController;

        this.isPlaying = false;
//        background = new Color(40, 40, 40);
//        this.setForeground(new Color(179, 179, 179));
//        this.setBackground(background);

        passedTime = new JTextField("00:00");
        passedTime.setFont(new Font("Arial", Font.BOLD, 11));
        passedTime.setEditable(false);
        passedTime.setFocusable(false);
        passedTime.setHorizontalAlignment(JTextField.CENTER);
//        passed.setBackground(background);
        passedTime.setForeground(new Color(179, 179, 179));
        this.add(passedTime);

        progressBar = new JProgressBar(0, 0);
        progressBar.setStringPainted(false);
        progressBar.setBorderPainted(true);

        this.add(progressBar);


        totalTime = new JTextField("00:00");
        totalTime.setFont(new Font("Arial", Font.BOLD, 11));
        totalTime.setEditable(false);
        totalTime.setFocusable(false);
        totalTime.setHorizontalAlignment(JTextField.CENTER);
//        total.setBackground(background);
        totalTime.setForeground(new Color(179, 179, 179));
        this.add(totalTime);
    }

    public void startProgress(){
        progressThread = new Thread(() -> {
            if (!isPlaying){
                isPlaying = true;
            }

            int c = 1;
            while (playState <= duration && isPlaying) {
                if (Thread.interrupted()){
                    return;
                }

                if (c == 10){
//                    String secS = ((int) ((duration/1000)%60)) + "";
//                    if (((int) ((duration/1000)%60)) < 10){
//                        secS = 0 + "" + ((int) ((duration/1000)%60));
//                    }
//                    String len = ((int) ((duration/1000)/60)) + ":" + secS ;
                    int min = (playBackController.getSec()/1000)/60;
                    int sec = (playBackController.getSec()/1000)%60;
                    String secString = "" +sec;
                    if (sec < 10){
                        secString= 0 + "" + sec;
                    }
                    String passed = min+":"+secString;
                    passedTime.setText(passed);
                    c=0;
                } else {
                    c++;
                }

                progressBar.setValue(playState);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    return;
                }

                playState += 100;
            }
        });
        progressThread.start();
    }

    public void stopProgress(){
//        progressThread.stop();
        progressThread = null;
//        playLabel.setIcon(playIcon);
        isPlaying = false;
    }

    public void resetProgress(){
        playState = 0;

        progressBar.setValue(0);
//        progressBar.setString("0:0/0:0");
        if (progressThread != null) {
//            progressThread.stop();
            progressThread = null;
        }
        Song currentSong = playBackController.getCurrentSong();
        duration = currentSong.getLength();
        progressBar.setMaximum((int) duration);

        String seconds = ((int) ((duration/1000)%60)) + "";
        if (((int) ((duration/1000)%60)) < 10){
            seconds = 0 + "" + ((int) ((duration/1000)%60));
        }
        String len = ((int) ((duration/1000)/60)) + ":" + seconds ;
        totalTime.setText(len);

        setupMusicSlider();
    }

    public void setupMusicSlider() {

        progressBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pos = e.getX() / (double) progressBar.getWidth();
                playState = (int) (duration * pos);
                progressBar.setValue(playState);
                playBackController.move((int) (duration * pos));
                isPlaying = true;
            }
        });

//        progressBar.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//                int mouseX = e.getX();
//                int progressBarVal = (int) Math.round(((double) mouseX / (double) progressBar.getWidth()) * progressBar.getMaximum());
//                progressBar.setValue(progressBarVal);
//                num = progressBarVal;
//                passedTime.setText(progressBar.getValue() / 60 + ":" + progressBar.getValue() % 60);
//            }
//        });
    }
}

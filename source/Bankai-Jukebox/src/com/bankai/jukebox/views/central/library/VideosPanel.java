package com.bankai.jukebox.views.central.library;

import com.bankai.jukebox.config.Constants;
import com.bankai.jukebox.utils.ThumbnailGenerator;
import com.bankai.jukebox.views.TitleText;
import com.bankai.jukebox.views.player.PlayerPanel;
import com.bankai.jukebox.views.video.VideoPlayer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class VideosPanel extends JPanel {

    public VideosPanel(PlayerPanel playerPanel) {
        super();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new TitleText("Videos"));

        add(new VideosPanelContent(playerPanel));

        setVisible(true);
    }
}

class VideosPanelContent extends JPanel {

    int WIDTH = 200, HEIGHT = 200;

    private final PlayerPanel playerPanel;

    private ArrayList<File> videoFiles;

    public VideosPanelContent(PlayerPanel playerPanel) {
        super();

        this.setLayout(new GridLayout(0, 4, 20, 20));

        this.playerPanel = playerPanel;

        String folderPath = Constants.APP_VIDEOS_DIRECTORY;

        System.out.println("Folder path used: " + folderPath);

        videoFiles = getVideoFiles(folderPath);

        for (File file : videoFiles) {
            try {
                ImageIcon defaultImageIcon = new ImageIcon(Objects.requireNonNull(
                        VideosPanel.class.getClassLoader().getResource("images/no-artwork.jpg")));

                Image img = defaultImageIcon.getImage();
                Image resizedImg = img.getScaledInstance(WIDTH, HEIGHT-50, Image.SCALE_SMOOTH);
                defaultImageIcon = new ImageIcon(resizedImg);

                JButton button = new JButton(defaultImageIcon);
                button.setPreferredSize(new Dimension(150, 200));
                button.setText(file.getName());
                button.setToolTipText(file.getName());
                button.setVerticalTextPosition(AbstractButton.BOTTOM);
                button.setHorizontalTextPosition(AbstractButton.CENTER);
                button.addActionListener(e -> {
                    // Update Controls Panel
                    playerPanel.getPlayerControlsPanel();

                    ArrayList<File> videoQueue = new ArrayList<>();
                    videoQueue.add(file);
                    new VideoPlayer(playerPanel.getMediaPlayerComponent(), videoQueue);
                });

                // Load image asynchronously
                new Thread(() -> {
                    try {
                        ImageIcon thumbnail = new ImageIcon(new ThumbnailGenerator().generateThumbnail(file, WIDTH, HEIGHT-50, null));
                        button.setIcon(thumbnail);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();

                add(button);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        setVisible(true);
    }

    private ArrayList<File> getVideoFiles(String folderPath) {
        ArrayList<File> files = new ArrayList<>();
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {

                if (file.isFile() && isVideoFile(file.getName())) {
                    files.add(file);
                }
            }
        }
        return files;
    }

    private boolean isVideoFile(String fileName) {
        String[] videoExtensions = {".mp4", ".avi", ".mkv", ".mov"}; // Add more extensions if needed
        for (String extension : videoExtensions) {
            if (fileName.toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }
}

package com.bankai.jukebox.views.central.playlists;

import com.bankai.jukebox.config.Constants;
import com.bankai.jukebox.views.TitleText;
import com.bankai.jukebox.views.central.library.VideosPanel;
import com.bankai.jukebox.views.player.PlayerPanel;
import com.bankai.jukebox.views.video.VideoPlayer;
import net.coobird.thumbnailator.Thumbnails;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class PlaylistsPanel extends JPanel {

    public PlaylistsPanel(PlayerPanel playerPanel) {
        super();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new TitleText("My Playlists"));

        add(new PlaylistsPanelContent(playerPanel));

        setVisible(true);
    }
}

class PlaylistsPanelContent extends JPanel {

    int WIDTH = 150, HEIGHT = 150;

    private final PlayerPanel playerPanel;

    private ArrayList<File> videoFiles;

    public PlaylistsPanelContent(PlayerPanel playerPanel) {
        super();

        this.setLayout(new GridLayout(0, 4, 20, 20));

        this.playerPanel = playerPanel;

        String folderPath = Constants.APP_MUSIC_DIRECTORY; // Change this to your folder path

        System.out.println("Folder path used: " + folderPath);

        videoFiles = getVideoFiles(folderPath);

        for (File file : videoFiles) {
            try {
                ImageIcon defaultImageIcon = new ImageIcon(Objects.requireNonNull(
                        VideosPanel.class.getClassLoader().getResource("images/no-artwork.jpg")));

                JButton button = new JButton(defaultImageIcon);
                button.setPreferredSize(new Dimension(150, 150));
                button.setText(file.getName());
                button.setToolTipText(file.getName());
                button.setVerticalTextPosition(AbstractButton.BOTTOM);
                button.setHorizontalTextPosition(AbstractButton.CENTER);
                button.addActionListener(e -> {
                    ArrayList<File> videoQueue = new ArrayList<>();
                    videoQueue.add(file);
                    new VideoPlayer(playerPanel.getMediaPlayerComponent(), videoQueue);
                });

                add(button);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        setVisible(true);
    }

    private Image createThumbnail(File videoFile, int width, int height) throws IOException {
        return Thumbnails.of(videoFile)
                .size(width, height)
                .outputFormat("jpg")
                .asBufferedImage();
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
        String[] videoExtensions = {".mp3"}; // Add more extensions if needed
        for (String extension : videoExtensions) {
            if (fileName.toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }
}

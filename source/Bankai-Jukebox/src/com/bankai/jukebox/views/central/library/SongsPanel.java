package com.bankai.jukebox.views.central.library;

import com.bankai.jukebox.config.Constants;
import com.bankai.jukebox.views.TitleText;
import com.bankai.jukebox.views.player.PlayerPanel;
import com.bankai.jukebox.views.video.VideoPlayer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class SongsPanel extends JPanel {

    public SongsPanel(PlayerPanel playerPanel) {
        super();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new TitleText("Songs"));

        add(new SongsPanelContent(playerPanel));

        setVisible(true);
    }
}

class SongsPanelContent extends JPanel {

    private final PlayerPanel playerPanel;

    private ArrayList<File> audioFiles;

    public SongsPanelContent(PlayerPanel playerPanel) {
        super();

        this.setLayout(new GridLayout(0, 4, 20, 20));

        this.playerPanel = playerPanel;

        String folderPath = Constants.APP_MUSIC_DIRECTORY; // Change this to your folder path

        System.out.println("Folder path used: " + folderPath);

        audioFiles = getAudioFiles(folderPath);

        for (File file : audioFiles) {
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
                    // Update Controls Panel
                    playerPanel.getPlayerControlsPanel();
                    playerPanel.getPlayBackController().play(file.getAbsolutePath());
                });

                add(button);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        setVisible(true);
    }

    private ArrayList<File> getAudioFiles(String folderPath) {
        ArrayList<File> files = new ArrayList<>();
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {

                if (file.isFile() && isAudioFile(file.getName())) {
                    files.add(file);
                }
            }
        }
        return files;
    }

    private boolean isAudioFile(String fileName) {
        String[] audioExtensions = {".mp3"}; // Add more extensions if needed
        for (String extension : audioExtensions) {
            if (fileName.toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }
}
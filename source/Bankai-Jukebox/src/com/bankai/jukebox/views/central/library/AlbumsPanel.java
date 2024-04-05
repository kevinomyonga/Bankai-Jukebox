package com.bankai.jukebox.views.central.library;

import com.bankai.jukebox.config.Constants;
import com.bankai.jukebox.models.Album;
import com.bankai.jukebox.models.Song;
import com.bankai.jukebox.models.SongTableRow;
import com.bankai.jukebox.pages.HomePage;
import com.bankai.jukebox.views.SearchPanel;
import com.bankai.jukebox.views.TitleText;
import com.bankai.jukebox.views.player.PlayerPanel;
import com.bankai.jukebox.views.video.VideoPlayer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class AlbumsPanel extends JPanel {

    public AlbumsPanel(PlayerPanel playerPanel) {
        super();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new TitleText("Albums"));

        add(new AlbumsPanelContent(playerPanel));

        setVisible(true);
    }
}

class AlbumsPanelContent extends JPanel implements SearchPanel.SearchListener {

    int WIDTH = 200, HEIGHT = 200;

    private final PlayerPanel playerPanel;

    private ArrayList<File> videoFiles;

    private ArrayList<Album> albums;

    public AlbumsPanelContent(PlayerPanel playerPanel) {
        super();

        this.setLayout(new GridLayout(0, 4, 20, 20));

        this.playerPanel = playerPanel;

        String folderPath = Constants.APP_MUSIC_DIRECTORY;

        System.out.println("Folder path used: " + folderPath);

        filterAlbums("");

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
        String[] videoExtensions = {".mp3"}; // Add more extensions if needed
        for (String extension : videoExtensions) {
            if (fileName.toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    private void filterAlbums(String searchText) {

        // load all songs
        albums = HomePage.databaseHandler.searchAlbum(searchText);

        displayAlbums(albums);
    }

    private void displayAlbums(ArrayList<Album> albums) {
        for (Album album : albums) {
            try {
                ImageIcon defaultImageIcon = new ImageIcon(Objects.requireNonNull(
                        AlbumsPanel.class.getClassLoader().getResource("images/no-artwork.jpg")));

                Image img = album.getSong(0).getArtWork() != null
                        ? new ImageIcon(album.getSong(0).getArtWork().toURL()).getImage()
                        : defaultImageIcon.getImage();
                Image resizedImg = img.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
                defaultImageIcon = new ImageIcon(resizedImg);

                JButton button = new JButton(defaultImageIcon);
                button.setPreferredSize(new Dimension(WIDTH, HEIGHT + 50));
                button.setText(album.getTitle());
                button.setToolTipText(album.getTitle() + " - " + album.getArtist());
                button.setVerticalTextPosition(AbstractButton.BOTTOM);
                button.setHorizontalTextPosition(AbstractButton.CENTER);
                button.addActionListener(e -> {
                    playerPanel.getPlayBackController().resetQueue(album.getSongs());
                    playerPanel.getPlayBackController().setQueueIndex(0);
                    playerPanel.getPlayBackController().play(album.getSong(0));
                });

                add(button);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Implementation of SearchPanel.SearchListener
    @Override
    public void onSearch(String searchText) {
        filterAlbums(searchText);
    }
}

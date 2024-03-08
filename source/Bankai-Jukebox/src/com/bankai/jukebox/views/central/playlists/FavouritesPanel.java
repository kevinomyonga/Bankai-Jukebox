package com.bankai.jukebox.views.central.playlists;

import com.bankai.jukebox.config.Constants;
import com.bankai.jukebox.utils.AlternateRowColorRenderer;
import com.bankai.jukebox.views.SearchPanel;
import com.bankai.jukebox.views.TitleText;
import com.bankai.jukebox.views.player.PlayerPanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FavouritesPanel extends JPanel {

    public FavouritesPanel(PlayerPanel playerPanel) {
        super();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new TitleText("My Favourites"));

        add(new FavouritesPanelContent(playerPanel));

        setVisible(true);
    }
}

class FavouritesPanelContent extends JPanel implements SearchPanel.SearchListener {

    private final PlayerPanel playerPanel;

    private ArrayList<File> audioFiles;
    private ArrayList<File> filteredAudioFiles;

    private SearchPanel searchPanel;

    private DefaultTableModel tableModel;
    private JTable table;
    private String[] columnNames = {"#", "Title", "Time", "Artist", "Album", "Date Added"};

    public FavouritesPanelContent(PlayerPanel playerPanel) {
        super();

        this.setLayout(new BorderLayout());

        this.playerPanel = playerPanel;

        String folderPath = Constants.APP_MUSIC_DIRECTORY;

        System.out.println("Folder path used: " + folderPath);

        searchPanel = new SearchPanel(this); // Pass 'this' as the search listener
        add(searchPanel, BorderLayout.NORTH);

        JButton playAllButton = new JButton("Play All");
        playAllButton.addActionListener(e -> {});

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(e -> addSong());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(playAllButton);
        buttonPanel.add(removeButton);

        add(buttonPanel, BorderLayout.SOUTH);

        tableModel = new DefaultTableModel(null, columnNames);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setDefaultEditor(Object.class, null); // Prevent editing cell content
        table.setShowVerticalLines(false); // Remove grid lines

        // Set custom cell renderer for alternate row color
        table.setDefaultRenderer(Object.class, new AlternateRowColorRenderer());

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(table, BorderLayout.CENTER);
        tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);

        audioFiles = getAudioFiles(folderPath);
        filteredAudioFiles = new ArrayList<>(audioFiles);
        displaySongs(filteredAudioFiles);

        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                JTable table =(JTable) e.getSource();
                Point point = e.getPoint();

                if (e.getClickCount() == 2) {
                    int selectedRow = table.rowAtPoint(point);
                    if (selectedRow != -1) {
                        String songTitle = (String) table.getValueAt(selectedRow, 1);
                        playerPanel.getPlayBackController().play(filteredAudioFiles.get(selectedRow).getAbsolutePath());

                        System.out.println("Song selected: " + filteredAudioFiles.get(selectedRow).getName());
                    }
                }
            }
        });

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

    private void filterSongs(String searchText) {
        filteredAudioFiles.clear();
        for (File file : audioFiles) {
            if (file.getName().toLowerCase().contains(searchText.toLowerCase())) {
                filteredAudioFiles.add(file);
            }
        }
        displaySongs(filteredAudioFiles);
    }

    private void displaySongs(ArrayList<File> files) {
        tableModel.setRowCount(0);
        int fileNumber = 1;

        for (File file : files) {
            String title = file.getName().substring(0, file.getName().lastIndexOf('.'));
            long duration = 0; // You can add logic to get duration
            String artist = ""; // You can add logic to get artist
            String album = ""; // You can add logic to get album
            Date dateAdded = new Date(); // You can add logic to get last played date

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateAddedString = dateFormat.format(dateAdded);

            tableModel.addRow(new Object[]{fileNumber++, title, duration, artist, album, dateAddedString});
        }
    }

    // Implementation of SearchPanel.SearchListener
    @Override
    public void onSearch(String searchText) {
        filterSongs(searchText);
    }

    private void addSong() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter(".mp3 files", "mp3"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            File destFile = new File(Constants.APP_MUSIC_DIRECTORY + File.separator + selectedFile.getName());
            try {
                Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                if (isAudioFile(destFile.getName())) {
                    audioFiles.add(destFile);
                    filterSongs("");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}

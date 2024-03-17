package com.bankai.jukebox.views.central.library;

import com.bankai.jukebox.config.Constants;
import com.bankai.jukebox.models.Song;
import com.bankai.jukebox.models.SongTableRow;
import com.bankai.jukebox.pages.HomePage;
import com.bankai.jukebox.utils.AlternateRowColorRenderer;
import com.bankai.jukebox.utils.IO.BJFileChooser;
import com.bankai.jukebox.utils.TagReader;
import com.bankai.jukebox.utils.database.DatabaseHandler;
import com.bankai.jukebox.views.SearchPanel;
import com.bankai.jukebox.views.TitleText;
import com.bankai.jukebox.views.player.PlayerPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SongsPanel extends JPanel {

    public SongsPanel(PlayerPanel playerPanel) {
        super();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new TitleText("Songs"));

        add(new SongsPanelContent(playerPanel));

        setVisible(true);
    }
}

class SongsPanelContent extends JPanel implements SearchPanel.SearchListener {

    private final PlayerPanel playerPanel;

    private SearchPanel searchPanel;

    private JTable table;

    private String[] columnNames = {"#", "Title", "Time", "Artist", "Album", "Genre", "Plays", "Last Played"};

    private ArrayList<Song> songs;
    private ArrayList<SongTableRow> rows = new ArrayList<>();
//    private DatabaseHandler databaseHandler;
    private SongTableModel tableModel;

    public SongsPanelContent(PlayerPanel playerPanel) {
        super();

        this.setLayout(new BorderLayout());

        this.playerPanel = playerPanel;

        String folderPath = Constants.APP_MUSIC_DIRECTORY;

        System.out.println("Folder path used: " + folderPath);

        searchPanel = new SearchPanel(this); // Pass 'this' as the search listener
        add(searchPanel, BorderLayout.NORTH);

        tableModel = new SongTableModel();

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addSongToApp());

        JButton playAllButton = new JButton("Play All");
        playAllButton.addActionListener(e -> {
//                playAll();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(addButton);
        buttonPanel.add(playAllButton);

        add(buttonPanel, BorderLayout.SOUTH);


        TableColumn column0 = new TableColumn(0);
        column0.setHeaderValue("Add");

        TableColumn column1 = new TableColumn(1);
        column1.setHeaderValue("Artwork");

        TableColumn column2 = new TableColumn(2);
        column2.setHeaderValue("Title");

        TableColumn column3 = new TableColumn(3);
        column3.setHeaderValue("Album");

        TableColumn column4 = new TableColumn(4);
        column4.setHeaderValue("Artist");

        TableColumn column5 = new TableColumn(5);
        column5.setHeaderValue("Plays");

        TableColumn column6 = new TableColumn(6);
        column6.setHeaderValue("Last Played");

        TableColumn column7 = new TableColumn(7);
        column7.setHeaderValue("Select");

        tableModel.addColumn(column0.getHeaderValue());
        tableModel.addColumn(column1.getHeaderValue());
        tableModel.addColumn(column2.getHeaderValue());
        tableModel.addColumn(column3.getHeaderValue());
        tableModel.addColumn(column4.getHeaderValue());
        tableModel.addColumn(column5.getHeaderValue());
        tableModel.addColumn(column6.getHeaderValue());
        tableModel.addColumn(column7.getHeaderValue());

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setDefaultEditor(Object.class, null); // Prevent editing cell content
        table.setShowVerticalLines(false); // Remove grid lines

        // Set custom cell renderer for alternate row color
        table.setDefaultRenderer(Object.class, new AlternateRowColorRenderer());
        //Set up column sizes.
        initColumnSizes(table);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(table, BorderLayout.CENTER);
        tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);

        songs = new ArrayList<>();
        filterSongs("");

        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                JTable table =(JTable) e.getSource();
                Point point = e.getPoint();

                if (e.getClickCount() == 2) {
                    int selectedRow = table.rowAtPoint(point);
                    if (selectedRow != -1) {
                        playerPanel.getPlayerControlsPanel();

                        Song selectedSong = songs.get(selectedRow);
                        playerPanel.getPlayBackController().resetQueue(songs);
                        playerPanel.getPlayBackController().setQueueIndex(selectedRow);
                        playerPanel.getPlayBackController().play(selectedSong);

                        System.out.println("Song selected: " + selectedSong.getTitle());
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
        songs.clear();

        // load all songs
        songs = HomePage.databaseHandler.searchSong(searchText);

        displaySongs(songs);
    }

    private void displaySongs(ArrayList<Song> songs) {
        tableModel.setRowCount(0);
        rows.clear();

        // initialize table
        for (Song song : songs) {
            SongTableRow row = new SongTableRow(song);
            rows.add(row);
            tableModel.addRow(new Object[]{row.getAddIcon(), row.getArtWork(),
                    row.getTitle(),
                    row.getAlbum(), row.getArtist(), row.getLastPlayed(), row.getChecked()});
        }
    }

    // Implementation of SearchPanel.SearchListener
    @Override
    public void onSearch(String searchText) {
        filterSongs(searchText);
    }

    private void addSongToApp() {
        BJFileChooser fileChooser = new BJFileChooser(SongsPanelContent.this, null, true);

        File selectedFile = fileChooser.getMP3File();

        if (selectedFile != null) {
            File destFile = new File(Constants.APP_MUSIC_DIRECTORY + File.separator + selectedFile.getName());

            try {
                Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                if (isAudioFile(destFile.getName())) {
//                audioFiles.add(destFile);
//                filterSongs("");

                    TagReader songReader = new TagReader();
                    URI mp3 = destFile.toURI();
                    Song song = null;
                    try {
                        songReader.getAdvancedTags(mp3.toURL());
                        song = songReader.getSong();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }

                    // add song to the parent playlist
//                HomePage.databaseHandler.addSongToPlaylist(song, null);
                    addSong(song);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void addSong(Song song) {
//        SongTableRow row = new SongTableRow(song);
//        SwingUtilities.invokeLater(() -> tableModel.addRow(new Object[]{row.getAddIcon(), row.getArtWork(),
//                row.getTitle(),
//                row.getAlbum(), row.getArtist(), row.getLastPlayed(), row.getChecked()}));
//        ArrayList<SongTableRow> newRows = new ArrayList<>();
//        for (SongTableRow s : rows){
//            newRows.add(s);
//        }
//        rows = newRows;

        HomePage.databaseHandler.saveSong(song);
        filterSongs("");
    }

    /**
     * Method deletes song from all occurrences in tables. use this method inside another thread cause it may block the app for a couple of seconds
     *
     * @param song
     */
    public void removeSong(Song song) {
        int index = -1;
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i).getSong().equals(song)) {
                index = i;
            }
        }
        rows.remove(index);
        SongTableModel model = (SongTableModel) table.getModel();
        model.removeRow(index);
        HomePage.databaseHandler.removeSong(song);
        // tell to remove song from somewhere
    }

    /*
     * This method picks good column sizes.
     * If all column heads are wider than the column's cells'
     * contents, then you can just use column.sizeWidthToFit().
     */
    private void initColumnSizes(JTable table) {
        table.getColumnModel().getColumn(0).setMaxWidth(40);
        table.getColumnModel().getColumn(1).setMaxWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(120);
        table.getColumnModel().getColumn(6).setPreferredWidth(120);
        table.getColumnModel().getColumn(7).setMaxWidth(50);
    }

    /**
     * Table model for the data, nothing special here
     */
    class SongTableModel extends DefaultTableModel {

        public SongTableModel() {
            super(0, 0);
        }

        public int getColumnCount() {
            return 8;
        }

        public Object getValueAt(int row, int col) {
            return switch (col) {
                case 0 -> rows.get(row).getAddIcon();
                case 1 -> rows.get(row).getArtWork();
                case 2 -> rows.get(row).getTitle();
                case 3 -> rows.get(row).getAlbum();
                case 4 -> rows.get(row).getArtist();
                case 5 -> rows.get(row).getPlayCount();
                case 6 -> {
                    Date date = new Date(rows.get(row).getLastPlayed());
                    yield new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date);
                }
                case 7 -> rows.get(row).getChecked();
                default -> null;
            };
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            if (c != 5)
                return getValueAt(0, c).getClass();
            else
                return Long.class;
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            return col == 7;
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
            SongTableRow row1 = rows.get(row);
            row1.setChecked((Boolean) value);
            fireTableCellUpdated(row, col);
        }

    }
}

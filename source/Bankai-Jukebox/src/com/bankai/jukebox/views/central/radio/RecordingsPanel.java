package com.bankai.jukebox.views.central.radio;

import com.bankai.jukebox.config.Constants;
import com.bankai.jukebox.utils.AlternateRowColorRenderer;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class RecordingsPanel extends JPanel {

    public RecordingsPanel(PlayerPanel playerPanel) {
        super();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new TitleText("Recordings"));

        add(new RecordingsPanelContent(playerPanel));

        setVisible(true);
    }
}

class RecordingsPanelContent extends JPanel implements SearchPanel.SearchListener {

    private PlayerPanel playerPanel;

    private ArrayList<File> recordingFiles;
    private ArrayList<File> filteredRecordingFiles;

    private SearchPanel searchPanel;

    private JTable table;
    private DefaultTableModel tableModel;

    public RecordingsPanelContent(PlayerPanel playerPanel) {
        super();

        this.setLayout(new BorderLayout());

        this.playerPanel = playerPanel;

        String folderPath = Constants.APP_RECORDINGS_DIRECTORY;

        System.out.println("Folder path used: " + folderPath);

        searchPanel = new SearchPanel(this); // Pass 'this' as the search listener
        add(searchPanel, BorderLayout.NORTH);

        TableColumn column0 = new TableColumn(0);
        column0.setHeaderValue("#");

        TableColumn column1 = new TableColumn(1);
        column1.setHeaderValue("Date Created");

        TableColumn column2 = new TableColumn(2);
        column2.setHeaderValue("File Name");

        TableColumn column3 = new TableColumn(3);
        column3.setHeaderValue("Actions");

        tableModel = new DefaultTableModel();
        tableModel.addColumn(column0.getHeaderValue());
        tableModel.addColumn(column1.getHeaderValue());
        tableModel.addColumn(column2.getHeaderValue());
        tableModel.addColumn(column3.getHeaderValue());

        table = new JTable(tableModel);
        // Adjusting column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(400);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);

//        table.setRowHeight(50);
//        table.setRowMargin(10);
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Table width fill the available space
        table.getTableHeader().setReorderingAllowed(true); // Prevent column reordering
//        table.setRowSelectionAllowed(false); // Disable row selection
        table.setDefaultEditor(Object.class, null); // Prevent editing cell content

        // Set custom cell renderer for alternate row color
        table.setDefaultRenderer(Object.class, new AlternateRowColorRenderer());

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(table, BorderLayout.CENTER);
        tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);

        recordingFiles = getAudioFiles(folderPath);
        filteredRecordingFiles = new ArrayList<>(recordingFiles);
        displayRecordings(filteredRecordingFiles);

        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                JTable table =(JTable) e.getSource();
                Point point = e.getPoint();

                if (e.getClickCount() == 2) {
                    int selectedRow = table.rowAtPoint(point);
                    if (selectedRow != -1) {
                        playerPanel.getPlayerControlsPanel();
                        playerPanel.getPlayBackController().play(filteredRecordingFiles.get(selectedRow).getAbsolutePath());

                        System.out.println("Recording selected: " + filteredRecordingFiles.get(selectedRow).getName());
                    }
                }
            }
        });

//        for (File file : recordingFiles) {
//            try {
//                ImageIcon defaultImageIcon = new ImageIcon(Objects.requireNonNull(
//                        VideosPanel.class.getClassLoader().getResource("images/no-artwork.jpg")));
//
//                JButton button = new JButton(defaultImageIcon);
//                button.setPreferredSize(new Dimension(150, 150));
//                button.setText(file.getName());
//                button.setToolTipText(file.getName());
//                button.setVerticalTextPosition(AbstractButton.BOTTOM);
//                button.setHorizontalTextPosition(AbstractButton.CENTER);
//                button.addActionListener(e -> {
//                    ArrayList<File> videoQueue = new ArrayList<>();
//                    videoQueue.add(file);
//                    new VideoPlayer(playerPanel.getMediaPlayerComponent(), videoQueue);
//                });
//
//                add(button);
//
//                String fileName = file.getName();
//                String filePath = file.getAbsolutePath();
//                model.addRow(new Object[]{fileName, filePath});
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        setVisible(true);
    }

    private ArrayList<File> getAudioFiles(String folderPath) {
        ArrayList<File> files = new ArrayList<>();
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            // Convert array to ArrayList for sorting
            ArrayList<File> fileList = new ArrayList<>(listOfFiles.length);
            Collections.addAll(fileList, listOfFiles);

            // Sort files by date, latest first
            fileList.sort(Comparator.comparingLong(File::lastModified).reversed());

            for (File file : listOfFiles) {

                if (file.isFile() && isAudioFile(file.getName())) {
                    files.add(file);
                }
            }
        }
        return files;
    }

    private boolean isAudioFile(String fileName) {
        String[] videoExtensions = {".mp3"}; // Add more extensions if needed
        for (String extension : videoExtensions) {
            if (fileName.toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    private String extractDateCreated(String fileName) {
        // File name format: 20240226-130607-atunwadigital_streamguys1_com--1
        String dateTimeString = fileName.substring(0, 15); // Extract "20240226-130607"
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date date = inputFormat.parse(dateTimeString);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Unknown";
        }
    }

    private void deleteFile(File file) {
        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete " + file.getName() + "?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            boolean deleted = file.delete();
            if (deleted) {
                JOptionPane.showMessageDialog(this, "File deleted successfully!");
                // Refresh the table after deletion
                tableModel.removeRow(table.getSelectedRow());
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete the file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void filterRecordings(String searchText) {
        filteredRecordingFiles.clear();
        for (File file : recordingFiles) {
            if (file.getName().toLowerCase().contains(searchText.toLowerCase())) {
                filteredRecordingFiles.add(file);
            }
        }
        displayRecordings(filteredRecordingFiles);
    }

    private void displayRecordings(ArrayList<File> files) {
        tableModel.setRowCount(0);
        int fileNumber = 1;

        for (File file : files) {
            String dateCreated = extractDateCreated(file.getName());
            String fileName = file.getName();

            JButton playButton = new JButton("Play");
            playButton.addActionListener(e -> {});
            JButton deleteButton = new JButton("Delete");
            deleteButton.addActionListener(e -> deleteFile(file));

            JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 0));
            buttonPanel.add(playButton);
            buttonPanel.add(deleteButton);

            tableModel.addRow(new Object[]{fileNumber++, dateCreated, fileName, buttonPanel});
        }
    }

    // Implementation of SearchPanel.SearchListener
    @Override
    public void onSearch(String searchText) {
        filterRecordings(searchText);
    }
}

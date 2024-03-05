package com.bankai.jukebox.views.central.radio;

import com.bankai.jukebox.config.Constants;
import com.bankai.jukebox.views.TitleText;
import com.bankai.jukebox.views.player.PlayerPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
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

class RecordingsPanelContent extends JPanel {

    private PlayerPanel playerPanel;

    private ArrayList<File> recordingFiles;

    private JTable table;
    private DefaultTableModel model;

    public RecordingsPanelContent(PlayerPanel playerPanel) {
        super();

        this.setLayout(new FlowLayout());

        this.playerPanel = playerPanel;

        String folderPath = Constants.APP_RECORDINGS_DIRECTORY; // Change this to your folder path

        System.out.println("Folder path used: " + folderPath);

        TableColumn column0 = new TableColumn(0);
        column0.setHeaderValue("#");

        TableColumn column1 = new TableColumn(1);
        column1.setHeaderValue("Date Created");

        TableColumn column2 = new TableColumn(2);
        column2.setHeaderValue("File Name");

        TableColumn column3 = new TableColumn(3);
        column3.setHeaderValue("Actions");

        model = new DefaultTableModel();
        model.addColumn(column0.getHeaderValue());
        model.addColumn(column1.getHeaderValue());
        model.addColumn(column2.getHeaderValue());
        model.addColumn(column3.getHeaderValue());

        table = new JTable(model);
        // Adjusting column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(400);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);

        table.setRowHeight(50);
        table.setRowMargin(10);
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Table width fill the available space
        table.getTableHeader().setReorderingAllowed(true); // Prevent column reordering
//        table.setRowSelectionAllowed(false); // Disable row selection
        table.setDefaultEditor(Object.class, null); // Prevent editing cell content

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(table, BorderLayout.CENTER);
        tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
        add(tablePanel);

        recordingFiles = getVideoFiles(folderPath);

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

    private ArrayList<File> getVideoFiles(String folderPath) {
        ArrayList<File> files = new ArrayList<>();
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            // Convert array to ArrayList for sorting
            ArrayList<File> fileList = new ArrayList<>(listOfFiles.length);
            Collections.addAll(fileList, listOfFiles);

            // Sort files by date, latest first
            fileList.sort(Comparator.comparingLong(File::lastModified).reversed());

            int count = 1;
            for (File file : listOfFiles) {

                if (file.isFile() && isVideoFile(file.getName())) {
                    files.add(file);

                    String dateCreated = extractDateCreated(file.getName());
                    String fileName = file.getName();

                    JButton playButton = new JButton("Play");
                    playButton.addActionListener(e -> playFile(file));
                    JButton deleteButton = new JButton("Delete");
                    deleteButton.addActionListener(e -> deleteFile(file));

                    JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 0));
                    buttonPanel.add(playButton);
                    buttonPanel.add(deleteButton);

                    model.addRow(new Object[]{count++, dateCreated, fileName, buttonPanel});
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

    private void playFile(File file) {
        // Add code to play the file here
        System.out.println("Playing: " + file.getName());
    }

    private void deleteFile(File file) {
        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete " + file.getName() + "?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            boolean deleted = file.delete();
            if (deleted) {
                JOptionPane.showMessageDialog(this, "File deleted successfully!");
                // Refresh the table after deletion
                model.removeRow(table.getSelectedRow());
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete the file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

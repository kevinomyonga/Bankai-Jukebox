package com.bankai.jukebox.utils.IO;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.net.URI;

public class BJFileChooser {
    private JFileChooser fileChooser ;
    private String pickedFile;
    private Component parent;

    public BJFileChooser(Component parent, File directory, boolean isFile){
        this.parent = parent;
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(directory);
        fileChooser.setDialogTitle("Choose a file");
        if (isFile)
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        else
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    public URI getFolderURI(){
        if (fileChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION){
            return fileChooser.getSelectedFile().toURI();
        }else{
            return null;
        }
    }

    public File getImageFile(){
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image File", "jpg", "png");
        fileChooser.setFileFilter(filter);
        if (fileChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION){
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    public File getMP3File(){
        FileNameExtensionFilter filter = new FileNameExtensionFilter("MP3 music file", "mp3");
        fileChooser.setFileFilter(filter);
        if (fileChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION){
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    public File getVideoFile(){
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Video file", "mp4");
        fileChooser.setFileFilter(filter);
        if (fileChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION){
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    public void setTitle(String title){
        fileChooser.setDialogTitle(title);
    }
}

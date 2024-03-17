package com.bankai.jukebox.views.forms;

import com.bankai.jukebox.models.Album;
import com.bankai.jukebox.models.Song;
import com.bankai.jukebox.models.User;
import com.bankai.jukebox.pages.HomePage;
import com.bankai.jukebox.pages.LoginPage;
import com.bankai.jukebox.utils.IO.BJFileChooser;
import com.bankai.jukebox.utils.IO.FileIO;
import com.bankai.jukebox.utils.TagReader;
import com.bankai.jukebox.utils.database.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;

public class SignUpPanel  extends JPanel {

    public DatabaseHandler databaseHandler;

    private JLabel createAccountLabel = new JLabel();
    private JLabel userNameLabel = new JLabel();
    private JTextField userNameTextField = new JTextField();
    private JLabel passWordLabel = new JLabel();
    private JButton setProfImageButton = new JButton("Set Your Profile Image");
    private JButton signUpButton = new JButton("Sign Up");
    private JPasswordField passField = new JPasswordField();
    private JPanel passPanel = new JPanel();
    private JPanel userNamePanel = new JPanel();
    private URI profileImageURI = new File(FileIO.RESOURCES_RELATIVE + "icons" + File.separator + "noprofile.png").toURI();

    public SignUpPanel(DatabaseHandler databaseHandler, LoginPage loginPage) {
        this.databaseHandler = databaseHandler;

        this.setOpaque(false);

//        this.setBackground(new Color(22, 22, 22));
        userNamePanel.setLayout(new BorderLayout());
        passPanel.setLayout(new BorderLayout());
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);

        createAccountLabel.setText("Create New Account");
        createAccountLabel.setAlignmentX(CENTER_ALIGNMENT);
//        createAccountLabel.setFont(FontManager.getUbuntuBold(22f));
//        createAccountLabel.setForeground(Color.WHITE);
        this.add(createAccountLabel);

        add(Box.createRigidArea(new Dimension(0, 20)));
        // init username
        userNameLabel.setText("Username : ");
//        userNameLabel.setForeground(Color.WHITE);
        userNamePanel.add(userNameLabel, BorderLayout.WEST);
        userNamePanel.add(userNameTextField, BorderLayout.CENTER);
//        userNamePanel.setBackground(new Color(22, 22, 22));
//        userNameTextField.setFont(FontManager.getUbuntu(18f));
        this.setAlignmentX(LEFT_ALIGNMENT);
        this.add(userNamePanel);
        add(Box.createRigidArea(new Dimension(0, 20)));


        passWordLabel.setText("Password : ");
//        passWordLabel.setForeground(Color.WHITE);
        passPanel.add(passWordLabel, BorderLayout.WEST);
        passPanel.add(passField, BorderLayout.CENTER);
//        passPanel.setBackground(new Color(22, 22, 22));
        passPanel.add(passField, BorderLayout.CENTER);
        add(passPanel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        // profile selection

        setProfImageButton.setAlignmentX(CENTER_ALIGNMENT);
//        setProfImageButton.setBackground(new Color(97, 97, 97));
//        setProfImageButton.setForeground(Color.WHITE);
        setProfImageButton.addActionListener(actionEvent -> {
            BJFileChooser fileChooser = new BJFileChooser(null, null, true);
            profileImageURI = fileChooser.getImageFile().toURI();
        });
        add(setProfImageButton);
        add(Box.createRigidArea(new Dimension(0, 5)));

        // sign up button

        signUpButton.setAlignmentX(CENTER_ALIGNMENT);
//        signUpButton.setBackground(new Color(97, 97, 97));
//        signUpButton.setForeground(Color.WHITE);
        signUpButton.addActionListener(actionEvent -> {
            User user = new User(userNameTextField.getText(), FileIO.MD5(String.valueOf(passField.getPassword())));
            user.setProfileImage(profileImageURI);
            if (!userNameTextField.getText().isEmpty()) {
//                ArrayList<URI> usersDatabaseFiles = FileIO.findFilesRecursive(new File(FileIO.RESOURCES_RELATIVE));
                if (!databaseHandler.addUser(user)) {
                    System.out.println("failed");
                    createAccountLabel.setText("Username taken, choose another !");
                    createAccountLabel.setForeground(Color.RED);
                    this.validate();
                    this.repaint();
                } else {
                    createAccountLabel.setText("Account created successfully");
                    createAccountLabel.setForeground(Color.GREEN);
                    this.validate();
                    this.repaint();
//                    com.bankai.jukebox.Main.user = user;
                    user.setOnline(true);
                    user.setLastOnline(new Date().getTime());
                    user.setFriends("");
                    // start the main application
                    // setting the database handler
//                    DatabaseConnection connection = new DatabaseConnection(userNameTextField.getText());
//                    connection.initMusicsTable();
//                    databaseHandler = new DatabaseHelper(connection.getConnection());

                    // check if there isn't any song in database
                    ArrayList<Song> songs = databaseHandler.searchSong("");
                    if (songs.size() <= 0) {
                        BJFileChooser fileChooser = new BJFileChooser(this, null, false);
                        fileChooser.setTitle("Select musics folder");
                        URI dir = fileChooser.getFolderURI();
                        while (dir == null) {
                            fileChooser.setTitle("Mesle bache adam chiz entekhab kon dg");
                            dir = fileChooser.getFolderURI();
                        }
                        ArrayList<URI> mp3sInFolder = FileIO.findMP3Files(FileIO.findFilesRecursive(new File(dir)));
                        TagReader reader = new TagReader();
                        for (URI mp3File : mp3sInFolder) {
                            try {
                                reader.getAdvancedTags(mp3File.toURL());
                                songs.add(reader.getSong());
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                        }
                        databaseHandler.deepInsertSong(songs);
                        ArrayList<Album> insertedAlbums = databaseHandler.searchAlbum("");
                        for (Album album : insertedAlbums){
                            user.addAlbum(album);
                        }
                        databaseHandler.removeUser(user.getUsername());
                        databaseHandler.addUser(user);
                    }

                    new HomePage(databaseHandler);

                    loginPage.closeFrame();
                }
            } else {
                createAccountLabel.setText("Invalid input !");
                createAccountLabel.setForeground(Color.RED);
                this.validate();
                this.repaint();
            }
        });
        add(signUpButton);

        add(Box.createRigidArea(new Dimension(0, 20)));
    }
}

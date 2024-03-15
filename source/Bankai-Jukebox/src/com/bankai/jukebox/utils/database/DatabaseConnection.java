package com.bankai.jukebox.utils.database;

import com.bankai.jukebox.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

/**
 * Base class for database Reading / Writing
 * use init and insert and query methods inside a new thread to avoid main thread lock
 * <p>
 * <b>don't forget to involve close method after completing queries</b>
 */
public class DatabaseConnection {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConnection.class);


    private Connection connection = null;

    /**
     * constructor to create database file in resources folder
     * method uses sqlite jdbc driver
     * @param databaseName database file name
     */
    public DatabaseConnection(String databaseName) {
        try {
            Path path = Paths.get(Constants.APP_DIRECTORY);
            String url = "jdbc:sqlite:" + path.toAbsolutePath() + File.separator + databaseName + ".db";
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            LOGGER.error("Error establishing connection to the database", e);
        }
    }

    private void createTable(String sql) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL query: {}", sql, e);
        }
    }
    public void initUserTable(){
        String userQuery = """
                CREATE TABLE IF NOT EXISTS Users(username TEXT PRIMARY KEY,
                likedSongs TEXT,
                recentlyPlayed TEXT,
                password TEXT,
                profileImage TEXT,
                albums TEXT,
                playlists TEXT,
                friends TEXT,
                online INT,
                lastOnline LONG,
                unique(username)
                );""";
        createTable(userQuery);
    }
    public synchronized void initMusicsTable(){
        // create Song table
        String songQuery = """
                CREATE TABLE IF NOT EXISTS Songs(hash TEXT PRIMARY KEY,
                title TEXT,
                artist TEXT,
                album TEXT,
                length integer,
                playCount integer,
                playDate LONG,
                releaseDate integer,
                location TEXT,
                artwork TEXT
                );""";
        createTable(songQuery);
        // creating albums table
        String albumQuery = """
                CREATE TABLE IF NOT EXISTS Albums(id integer PRIMARY KEY AUTOINCREMENT,
                title TEXT,
                artist TEXT,
                artwork TEXT,
                public integer,
                songs TEXT,
                unique(title,artist)
                );""";
        createTable(albumQuery);
        String playlistQuery = """
                CREATE TABLE IF NOT EXISTS Playlists(id integer PRIMARY KEY AUTOINCREMENT,
                title TEXT,
                creator TEXT,
                artwork TEXT,
                public integer,
                editable integer,
                songs TEXT,
                unique(title)
                );""";
        createTable(playlistQuery);
    }

    public synchronized void close(){
        if (connection !=null){
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Error closing connection to the database", e);
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}

package com.bankai.jukebox.config;

/**
 * A class to hold the Constants values.
 *
 * @author Kevin Omyonga
 * @version 2024.2.0
 */
public class Constants {

    /**
     * A constant field to denote the value of APP_NAME.
     */
    public static final String APP_NAME = "Bankai Jukebox";

    /**
     * A constant field to denote the value of APP_VERSION.
     */
    public static final String APP_VERSION = "2024.3.0";

    /**
     * A constant field to denote the value of APP_DIRECTORY.
     */
    public static final String APP_DIRECTORY = System.getProperty("user.home") + "/" + APP_NAME.replace(" ", "");

    /**
     * A constant field to denote the value of APP_MUSIC_DIRECTORY.
     */
    public static final String APP_MUSIC_DIRECTORY = APP_DIRECTORY + "/Music";

    /**
     * A constant field to denote the value of APP_RECORDINGS_DIRECTORY.
     */
    public static final String APP_RECORDINGS_DIRECTORY = APP_DIRECTORY + "/Recordings";

    /**
     * A constant field to denote the value of APP_VIDEOS_DIRECTORY.
     */
    public static final String APP_VIDEOS_DIRECTORY = APP_DIRECTORY + "/Videos";

    /**
     * A constant field to denote the value of APP_WINDOW_PREFERRED_WIDTH and APP_WINDOW_PREFERRED_HEIGHT.
     */
    public static final int APP_WINDOW_PREFERRED_WIDTH = 1280, APP_WINDOW_PREFERRED_HEIGHT = 800;
}

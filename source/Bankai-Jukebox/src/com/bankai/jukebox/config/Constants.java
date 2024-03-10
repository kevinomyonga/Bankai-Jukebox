package com.bankai.jukebox.config;

import java.io.File;
import java.time.Year;

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
     * A constant field to denote the value of APP_DESCRIPTION.
     */
    public static final String APP_DESCRIPTION = "Virtual Jukebox Simulation Project By The Bankai Group For The MIS6030 Class (Spring 2024).";

    /**
     * A constant field to denote the value of APP_VERSION.
     */
    public static final String APP_VERSION = "2024.3.1";

    /**
     * A constant field to denote the value of APP_VERSION.
     */
    public static final String APP_VERSION_CODENAME = "Coup De Grâce";

    /**
     * A constant field to denote the value of APP_COPYRIGHT.
     */
    public static final String APP_COPYRIGHT = "The Bankai Group © " + Year.now().getValue();

    /**
     * A constant field to denote the value of APP_COPYRIGHT.
     */
    public static final String APP_DEV_TEAM = """
            Kevin Nyende Omyonga |
            Billy Nelson Omingo |
            Brian John Onsati |
            Leonard Ochieng Omondi |
            Wellingtone Wekesa Luvonga""";

    /**
     * A constant field to denote the value of APP_DIRECTORY.
     */
    public static final String APP_DIRECTORY = System.getProperty("user.home") + File.separator + APP_NAME.replace(" ", "");

    /**
     * A constant field to denote the value of APP_MUSIC_DIRECTORY.
     */
    public static final String APP_MUSIC_DIRECTORY = APP_DIRECTORY + File.separator + "Music";

    /**
     * A constant field to denote the value of APP_VIDEOS_DIRECTORY.
     */
    public static final String APP_VIDEOS_DIRECTORY = APP_DIRECTORY + File.separator + "Videos";

    /**
     * A constant field to denote the value of APP_VIDEO_THUMBNAILS_DIRECTORY.
     */
    public static final String APP_VIDEO_THUMBNAILS_DIRECTORY = APP_VIDEOS_DIRECTORY + File.separator + "Thumbnails";

    /**
     * A constant field to denote the value of APP_RADIO_DIRECTORY.
     */
    public static final String APP_RADIO_DIRECTORY = APP_DIRECTORY + File.separator + "Radio";

    /**
     * A constant field to denote the value of APP_RECORDINGS_DIRECTORY.
     */
    public static final String APP_RECORDINGS_DIRECTORY = APP_RADIO_DIRECTORY + File.separator + "Recordings";

    /**
     * A constant field to denote the value of APP_RADIO_THUMBNAILS_DIRECTORY.
     */
    public static final String APP_RADIO_THUMBNAILS_DIRECTORY = APP_RADIO_DIRECTORY + File.separator + "Thumbnails";

    /**
     * A constant field to denote the value of APP_WINDOW_PREFERRED_WIDTH and APP_WINDOW_PREFERRED_HEIGHT.
     */
    public static final int APP_WINDOW_PREFERRED_WIDTH = 1280, APP_WINDOW_PREFERRED_HEIGHT = 800;
}

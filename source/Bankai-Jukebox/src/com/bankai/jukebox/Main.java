package com.bankai.jukebox;

import com.bankai.jukebox.config.Constants;
import com.bankai.jukebox.models.User;
import com.bankai.jukebox.pages.HomePage;
import com.bankai.jukebox.pages.LoginPage;
import com.bankai.jukebox.utils.database.DatabaseConnection;
import com.bankai.jukebox.utils.database.DatabaseHandler;
import com.bankai.jukebox.utils.database.DatabaseHelper;
import com.bankai.jukebox.views.SplashScreen;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.util.SystemInfo;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static DatabaseHandler databaseHandler;
    public static User user;

    public static void main(String[] args) {
        // MacOS
        if (SystemInfo.isMacOS) {
            // Application name used in screen menu bar
            System.setProperty("apple.awt.application.name", Constants.APP_NAME);
        }

        FlatMacDarkLaf.registerCustomDefaultsSource("themes");
        FlatMacLightLaf.registerCustomDefaultsSource("themes");

        FlatMacDarkLaf.setup();
        FlatMacLightLaf.setup();

        SplashScreen splashScreen = new SplashScreen(3000);
        splashScreen.showSplash();

        // initializing databaseListeners
        DatabaseConnection connection = new DatabaseConnection(Constants.APP_DB_NAME);
        connection.initUserTable();
        connection.initMusicsTable();
        databaseHandler = new DatabaseHelper(connection.getConnection());

        // Check if user is already logged in
        ArrayList<User> users;
        if ((users = databaseHandler.getUserByOnlineStatus(true, databaseHandler)).isEmpty()) {
            user = users.getFirst();

            new HomePage(databaseHandler);
        } else {
            new LoginPage(databaseHandler);
        }
    }
}
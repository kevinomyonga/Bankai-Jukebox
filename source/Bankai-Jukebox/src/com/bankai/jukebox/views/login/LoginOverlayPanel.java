package com.bankai.jukebox.views.login;

import com.bankai.jukebox.pages.LoginPage;
import com.bankai.jukebox.utils.database.DatabaseHandler;
import com.bankai.jukebox.views.forms.SignInPanel;
import com.bankai.jukebox.views.forms.SignUpPanel;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.component.CallbackMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;

public class LoginOverlayPanel extends JPanel {

    public LoginOverlayPanel(DatabaseHandler databaseHandler, LoginPage loginPage) {
        this.setLayout(new BorderLayout());

        setOpaque(false); // Make the panel transparent

        SignInPanel signInPanel = new SignInPanel(databaseHandler, loginPage);
            this.add(signInPanel, BorderLayout.WEST);

            SignUpPanel signUpPanel = new SignUpPanel(databaseHandler, loginPage);
            this.add(signUpPanel, BorderLayout.EAST);
    }
}

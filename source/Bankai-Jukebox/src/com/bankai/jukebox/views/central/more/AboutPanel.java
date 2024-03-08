package com.bankai.jukebox.views.central.more;

import com.bankai.jukebox.config.Constants;
import com.bankai.jukebox.views.TitleText;
import com.bankai.jukebox.config.Icons;

import javax.swing.*;
import java.awt.*;

public class AboutPanel extends JPanel {

    public AboutPanel() {
        super();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new TitleText("About"));

        add(new AboutPanelContent());

        setVisible(true);
    }
}

class AboutPanelContent extends JPanel {

    public AboutPanelContent() {
        super();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Icons icons = new Icons();

        JLabel appLogoIconLabel = new JLabel();
        appLogoIconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        appLogoIconLabel.setIcon(icons.getAppLogoIcon());
        add(appLogoIconLabel);

        add(new TitleText(Constants.APP_NAME));
        add(new TitleText("v" + Constants.APP_VERSION));

        JLabel descriptionLabel = new JLabel();
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionLabel.setText(Constants.APP_DESCRIPTION);
        add(descriptionLabel);

        JLabel devTeamLabel = new JLabel();
        devTeamLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        devTeamLabel.setText(Constants.APP_DEV_TEAM);
        add(devTeamLabel);

        JLabel copyrightLabel = new JLabel();
        copyrightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        copyrightLabel.setText(Constants.APP_COPYRIGHT);
        add(copyrightLabel);

        setVisible(true);
    }
}

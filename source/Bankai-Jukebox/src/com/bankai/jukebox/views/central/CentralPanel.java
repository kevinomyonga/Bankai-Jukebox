package com.bankai.jukebox.views.central;

import com.bankai.jukebox.views.player.PlayerPanel;

import javax.swing.*;
import java.awt.*;

public class CentralPanel extends JPanel {

    private ShowPanel showPanel;
    private RadioPanel radioPanel;

    public CentralPanel(PlayerPanel playerPanel) {
        super();

        this.setLayout(new BorderLayout());

        showPanel = new ShowPanel();
        radioPanel = new RadioPanel(playerPanel);
        JScrollPane jscrollPane = new JScrollPane(radioPanel);
        jscrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(jscrollPane, BorderLayout.CENTER);
    }
}

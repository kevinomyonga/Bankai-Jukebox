package com.bankai.jukebox.views.central;

import javax.swing.*;
import java.awt.*;

public class CentralPanel extends JPanel {

    private ShowPanel showPanel;
    private RadioPanel radioPanel;

    public CentralPanel() {
        super();

        this.setLayout(new BorderLayout());
//        this.setBackground(new Color(33, 33, 33));

        showPanel = new ShowPanel();
        radioPanel = new RadioPanel();
        JScrollPane jscrollPane = new JScrollPane(radioPanel);
        jscrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        jscrollPane.setBackground(new Color(33, 33, 33));
        this.add(jscrollPane, BorderLayout.CENTER);
    }
}

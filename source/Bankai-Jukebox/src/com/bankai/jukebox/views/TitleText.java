package com.bankai.jukebox.views;

import javax.swing.*;
import java.awt.*;

public class TitleText extends JPanel {

    public TitleText(String title) {
        super();

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        add(titleLabel);

        setVisible(true);
    }
}

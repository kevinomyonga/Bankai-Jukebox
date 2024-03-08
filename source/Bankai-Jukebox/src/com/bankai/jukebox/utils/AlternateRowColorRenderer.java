package com.bankai.jukebox.utils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

// Custom cell renderer for alternate row color
public class AlternateRowColorRenderer extends DefaultTableCellRenderer {
    private Color lightColor;
    private Color darkColor;

    public AlternateRowColorRenderer() {
        // Define colors for light mode
        lightColor = new Color(240, 240, 240);
        // Define colors for dark mode
        darkColor = new Color(36, 36, 36); // Adjust color values based on your dark mode theme
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (!isSelected) {
            if (UIManager.getLookAndFeel().getName().toLowerCase().contains("dark")) {
                // Dark mode
                component.setBackground(row % 2 == 0 ? darkColor : table.getBackground());
            } else {
                // Light mode
                component.setBackground(row % 2 == 0 ? lightColor : table.getBackground());
            }
        }
        return component;
    }
}

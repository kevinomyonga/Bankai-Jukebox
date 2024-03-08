package com.bankai.jukebox.views;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class SearchPanel extends JPanel {

    private JTextField searchField;
    private SearchListener searchListener; // Callback interface

    public SearchPanel(SearchListener searchListener) {
        super();

        this.searchListener = searchListener;

        this.setLayout(new BorderLayout());

        searchField = new JTextField();
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filter();
            }
        });
        add(new JLabel("Search: "), BorderLayout.WEST);
        add(searchField, BorderLayout.CENTER);

        setVisible(true);
    }

    private void filter() {
        if (searchListener != null) {
            searchListener.onSearch(searchField.getText());
        }
    }

    // Callback interface
    public interface SearchListener {
        void onSearch(String searchText);
    }
}

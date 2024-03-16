package com.bankai.jukebox.views.central.more;

import com.bankai.jukebox.controllers.ThemeController;
import com.bankai.jukebox.views.TitleText;
import com.formdev.flatlaf.*;
import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.util.ColorFunctions;
import com.formdev.flatlaf.util.LoggingFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SettingsPanel extends JPanel {

    public SettingsPanel() {
        super();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new TitleText("Settings"));

        add(new SettingsPanelContent());

        setVisible(true);
    }
}

class SettingsPanelContent extends JPanel {

    public SettingsPanelContent() {
        super();

        this.setLayout(new GridLayout(0, 4, 20, 20));

        /*
          This is the theme change section
         */
        // Initialize AudioController with the provided MediaPlayer
        ThemeController themeController = new ThemeController();
        JButton lightButton = new JButton("Light Theme");
        lightButton.setFont(new Font("Arial", Font.BOLD, 12));
        lightButton.setHorizontalAlignment(SwingConstants.LEFT);
        JButton darkButton = new JButton("Dark Theme");
        lightButton.setFont(new Font("Arial", Font.BOLD, 12));
        lightButton.setHorizontalAlignment(SwingConstants.LEFT);
        JButton systemButton = new JButton("System Theme");
        lightButton.setFont(new Font("Arial", Font.BOLD, 12));
        lightButton.setHorizontalAlignment(SwingConstants.LEFT);

        // Add action listeners to buttons
        lightButton.addActionListener(e -> themeController.setTheme("light"));
        darkButton.addActionListener(e -> themeController.setTheme("dark"));
        systemButton.addActionListener(e -> themeController.setTheme("system"));

        // Add buttons to the panel
        add(lightButton);
        add(darkButton);
        add(systemButton);

        initAccentColors();

        setVisible(true);
    }

    // the real colors are defined in
    // flatlaf-demo/src/main/resources/com/formdev/flatlaf/demo/FlatLightLaf.properties and
    // flatlaf-demo/src/main/resources/com/formdev/flatlaf/demo/FlatDarkLaf.properties
    private static final String[] accentColorKeys = {
            "#2675BF",
            "#007AFF",
            "#BF5AF2",
            "#FF3B30",
            "#FF9500",
            "#FFCC00",
            "#28CD41",
    };
    private static final String[] accentColorNames = {
            "Default",
            "Blue",
            "Purple",
            "Red",
            "Orange",
            "Yellow",
            "Green",
    };
    private final JToggleButton[] accentColorButtons = new JToggleButton[accentColorKeys.length];
    private JLabel accentColorLabel;
    private Color accentColor;

    private void initAccentColors() {
        accentColorLabel = new JLabel( "Accent color: " );

        this.add( Box.createHorizontalGlue() );
        this.add( accentColorLabel );

        ButtonGroup group = new ButtonGroup();
        for( int i = 0; i < accentColorButtons.length; i++ ) {
            accentColorButtons[i] = new JToggleButton( new AccentColorIcon( accentColorKeys[i] ) );
            accentColorButtons[i].setToolTipText( accentColorNames[i] );
            accentColorButtons[i].addActionListener( this::accentColorChanged );
            this.add( accentColorButtons[i] );
            group.add( accentColorButtons[i] );
        }

        accentColorButtons[0].setSelected( true );

        FlatLaf.setSystemColorGetter( name -> {
            return name.equals( "accent" ) ? accentColor : null;
        } );

        UIManager.addPropertyChangeListener( e -> {
            if( "lookAndFeel".equals( e.getPropertyName() ) )
                updateAccentColorButtons();
        } );
        updateAccentColorButtons();
    }

    private void accentColorChanged( ActionEvent e ) {
        String accentColorKey = null;
        for( int i = 0; i < accentColorButtons.length; i++ ) {
            if( accentColorButtons[i].isSelected() ) {
                accentColorKey = accentColorKeys[i];
                break;
            }
        }

        accentColor = (accentColorKey != null && !accentColorKey.equals(accentColorKeys[0]))
                ? UIManager.getColor( accentColorKey )
                : null;

        Class<? extends LookAndFeel> lafClass = UIManager.getLookAndFeel().getClass();
        try {
            FlatLaf.setup( lafClass.getDeclaredConstructor().newInstance() );
            FlatLaf.updateUI();
        } catch( Exception ex ) {
            LoggingFacade.INSTANCE.logSevere( null, ex );
        }
    }

    private void updateAccentColorButtons() {
        Class<? extends LookAndFeel> lafClass = UIManager.getLookAndFeel().getClass();
        boolean isAccentColorSupported =
                lafClass == FlatLightLaf.class ||
                        lafClass == FlatDarkLaf.class ||
                        lafClass == FlatIntelliJLaf.class ||
                        lafClass == FlatDarculaLaf.class ||
                        lafClass == FlatMacLightLaf.class ||
                        lafClass == FlatMacDarkLaf.class;

        accentColorLabel.setVisible( isAccentColorSupported );

        for( int i = 0; i < accentColorButtons.length; i++ )
            accentColorButtons[i].setVisible( isAccentColorSupported );
    }

    //---- class AccentColorIcon ----------------------------------------------

    private static class AccentColorIcon
            extends FlatAbstractIcon
    {
        private final String colorKey;

        AccentColorIcon( String colorKey ) {
            super( 16, 16, null );
            this.colorKey = colorKey;
        }

        @Override
        protected void paintIcon( Component c, Graphics2D g ) {
            Color color = UIManager.getColor( colorKey );
            if( color == null )
                color = Color.lightGray;
            else if( !c.isEnabled() ) {
                color = FlatLaf.isLafDark()
                        ? ColorFunctions.shade( color, 0.5f )
                        : ColorFunctions.tint( color, 0.6f );
            }

            g.setColor( color );
            g.fillRoundRect( 1, 1, width - 2, height - 2, 5, 5 );
        }
    }
}

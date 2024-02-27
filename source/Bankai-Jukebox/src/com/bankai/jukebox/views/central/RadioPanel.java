package com.bankai.jukebox.views.central;

import com.bankai.jukebox.views.TitleText;
import com.bankai.jukebox.views.player.PlayerPanel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class RadioPanel extends JPanel {

    public RadioPanel(PlayerPanel playerPanel) {
        super();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new TitleText("Radio Channels"));

        add(new RadioPanelContent(playerPanel));

        setVisible(true);
    }

}

class RadioPanelContent extends JPanel {

    private JsonArray stations;

    int WIDTH = 150, HEIGHT = 150;

    private final PlayerPanel playerPanel;

    public RadioPanelContent(PlayerPanel playerPanel) {
        super();

        this.setLayout(new GridLayout(0, 4, 20, 20));

        this.playerPanel = playerPanel;

        loadStationsFromJSON();

        displayStations();

        setVisible(true);
    }

    private void loadStationsFromJSON() {
        try {
            Path path = Paths.get(Objects.requireNonNull(
                    RadioPanel.class.getClassLoader().getResource("data/stations/Kenya.json")).toURI());
            Object obj = JsonParser.parseReader(new FileReader(path.toString()));
            stations = (JsonArray) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayStations() {
        for (Object obj : stations) {
            JsonObject station = (JsonObject) obj;
            String title = station.get("Title").getAsString();
            String description = station.get("Description").getAsString();
            String logoUrl = station.get("Logo").getAsString();
            String source1 = station.get("Source1").getAsString();

            // Create station panel
            JPanel stationPanel = new JPanel(new BorderLayout());
            stationPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT + 50)); // Set panel size

            // Add image through lazy loading with fade-in effect
            // (assuming you have images corresponding to stations)
            ImageIcon defaultImageIcon = new ImageIcon(Objects.requireNonNull(
                    RadioPanel.class.getClassLoader().getResource("images/no-artwork.jpg")));

            JButton imageLabel = new JButton(defaultImageIcon);
            imageLabel.setText(title);
            imageLabel.setToolTipText(title);
            imageLabel.setVerticalTextPosition(AbstractButton.BOTTOM);
            imageLabel.setHorizontalTextPosition(AbstractButton.CENTER);
            imageLabel.addActionListener(e -> {
                // Play the station stream
                playerPanel.getPlayerRadioControlsPanel().playRadioStream(source1);
                // Display dialog with station info
//                    JOptionPane.showMessageDialog(null, "Title: " + title + "\nDescription: " + description);
            });

            stationPanel.add(imageLabel, BorderLayout.CENTER);

            // Load image asynchronously
            new Thread(() -> {
                try {
                    BufferedImage image = loadImage(logoUrl);
                    if (image != null) {
                        ImageIcon scaledImageIcon = new ImageIcon(scaleImage(image, WIDTH, HEIGHT)); // Specify desired width and height
                        fadeImageIn(imageLabel, scaledImageIcon);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Add station panel to grid panel
            add(stationPanel);
        }
    }

    private void fadeImageIn(JButton imageLabel, ImageIcon imageIcon) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            private float opacity = 0;

            @Override
            public void run() {
                opacity += 0.05f; // Adjust the fade-in speed here
                if (opacity >= 1f) {
                    opacity = 1f;
                    timer.cancel();
                }
                setOpacity(imageLabel, opacity);
                imageLabel.setIcon(imageIcon);
            }
        };
        timer.schedule(task, 0, 100); // Adjust the interval here
    }

    private void setOpacity(JButton label, float opacity) {
        label.setIcon(new ImageIcon(makeImageTranslucent(((ImageIcon) label.getIcon()).getImage(), opacity)));
    }

    private BufferedImage makeImageTranslucent(Image image, float opacity) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
        return bufferedImage;
    }

    private BufferedImage loadImage(String imageUrl) throws IOException {
        try (InputStream inputStream = new URL(imageUrl).openStream()) {
            return ImageIO.read(inputStream);
        } catch (IOException e) {
            System.err.println("Failed to load image: " + e.getMessage());
            return null;
        }
    }

    private Image scaleImage(Image image, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return scaledImage;
    }

}
